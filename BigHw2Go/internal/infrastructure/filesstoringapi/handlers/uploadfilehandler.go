package handlers

import (
	"BigHw2Go/internal/infrastructure/usecases"
	"encoding/json"
	"errors"
	"fmt"
	"mime/multipart"
	"net/http"

	"go.uber.org/zap"
)

type UploadFileHandler struct {
	logger             *zap.Logger
	filesStoringFacade *usecases.FilesStoringFacade
}

func NewUploadFileHandler(logger *zap.Logger, uploadFileUseCase *usecases.FilesStoringFacade) *UploadFileHandler {
	return &UploadFileHandler{
		logger:             logger,
		filesStoringFacade: uploadFileUseCase,
	}
}

func (ufh *UploadFileHandler) ServeHTTP(w http.ResponseWriter, r *http.Request) {
	file, handler, err := r.FormFile("file")
	if err != nil {
		http.Error(w, fmt.Sprintf("Error while getting file %v", err.Error()), http.StatusBadRequest)
		return
	}

	defer func(file multipart.File) {
		cErr := file.Close()
		if cErr != nil {
			if err == nil {
				err = cErr
			}
			err = errors.Join(err, cErr)
		}
	}(file)

	location := r.FormValue("location")
	// все загруженные файлы будут лежать в одной папке.
	location = "./uploaded/" + location

	id, err := ufh.filesStoringFacade.UploadFile(r.Context(), file, handler, location)
	if err != nil {
		http.Error(w, fmt.Sprintf("uploadFileUseCase: UploadFile: %v", err.Error()), http.StatusBadRequest)
		return
	}

	ufh.logger.Info("Successfully uploaded file, with id: ", zap.String("id", id.String()))

	encoder := json.NewEncoder(w)

	err = encoder.Encode(id)
	if err != nil {
		http.Error(w, fmt.Sprintf("encode id: %v", err.Error()), http.StatusBadRequest)
		return
	}
}

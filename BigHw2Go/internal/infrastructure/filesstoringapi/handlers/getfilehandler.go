package handlers

import (
	"BigHw2Go/internal/infrastructure/usecases"
	"errors"
	"fmt"
	"io"
	"net/http"

	"github.com/google/uuid"
	"go.uber.org/zap"
)

type GetFileHandler struct {
	logger             *zap.Logger
	filesStoringFacade *usecases.FilesStoringFacade
}

func NewGetFileHandler(logger *zap.Logger, filesStoringFacade *usecases.FilesStoringFacade) *GetFileHandler {
	return &GetFileHandler{
		logger:             logger,
		filesStoringFacade: filesStoringFacade,
	}
}

func (gfh *GetFileHandler) ServeHTTP(w http.ResponseWriter, r *http.Request) {
	fileStringID := r.PathValue("id")

	fileId, err := uuid.Parse(fileStringID)
	if err != nil {
		http.Error(w, fmt.Sprintf("uuid.Parse: %v", err.Error()), http.StatusBadRequest)
		return
	}

	reader, err := gfh.filesStoringFacade.GetFile(r.Context(), fileId)
	if err != nil {
		http.Error(w, fmt.Sprintf("filesStoringFacade: GetFile: %v", err.Error()), http.StatusInternalServerError)
		return
	}

	defer func(reader io.ReadCloser) {
		cErr := reader.Close()
		if cErr != nil {
			if err == nil {
				err = cErr
			}
			err = errors.Join(err, cErr)
		}
	}(reader)

	w.WriteHeader(http.StatusOK)

	_, err = io.Copy(w, reader)
	if err != nil {
		http.Error(w, fmt.Sprintf("io.Copy: %v", err.Error()), http.StatusInternalServerError)
		return
	}
}

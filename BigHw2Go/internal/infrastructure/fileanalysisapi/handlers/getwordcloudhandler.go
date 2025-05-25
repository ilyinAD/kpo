package handlers

import (
	"BigHw2Go/internal/infrastructure/usecases"
	"errors"
	"fmt"
	"io"
	"net/http"
)

type GetWordCloudHandler struct {
	fileAnalysisFacade *usecases.FileAnalysisFacade
}

func NewGetWordCloudHandler(f *usecases.FileAnalysisFacade) *GetWordCloudHandler {
	return &GetWordCloudHandler{fileAnalysisFacade: f}
}

func (gh *GetWordCloudHandler) ServeHTTP(w http.ResponseWriter, r *http.Request) {
	location := r.FormValue("location")

	reader, err := gh.fileAnalysisFacade.GetWordCloudImg(location)
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

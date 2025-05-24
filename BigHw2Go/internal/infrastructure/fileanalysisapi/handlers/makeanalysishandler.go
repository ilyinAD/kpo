package handlers

import (
	"BigHw2Go/internal/infrastructure/usecases"
	"encoding/json"
	"fmt"
	"net/http"

	"github.com/google/uuid"
)

type MakeAnalysisHandler struct {
	fileAnalysisFacade *usecases.FileAnalysisFacade
}

func NewMakeAnalysisHandler(fileAnalysisFacade *usecases.FileAnalysisFacade) *MakeAnalysisHandler {
	return &MakeAnalysisHandler{
		fileAnalysisFacade: fileAnalysisFacade,
	}
}

func (mh *MakeAnalysisHandler) ServeHTTP(w http.ResponseWriter, r *http.Request) {
	fileStringID := r.PathValue("id")

	fileId, err := uuid.Parse(fileStringID)
	if err != nil {
		http.Error(w, fmt.Sprintf("uuid.Parse: %v", err.Error()), http.StatusBadRequest)
		return
	}

	fileAnalysisModel, err := mh.fileAnalysisFacade.GetFileAnalysis(r.Context(), fileId)
	if err != nil {
		http.Error(w, fmt.Sprintf("fileAnalysisFacade:GetFileAnalysis: %v", err.Error()), http.StatusBadRequest)
		return
	}

	encoder := json.NewEncoder(w)

	err = encoder.Encode(fileAnalysisModel)
	if err != nil {
		http.Error(w, fmt.Sprintf("encoder.Encode: %v", err.Error()), http.StatusBadRequest)
		return
	}
}

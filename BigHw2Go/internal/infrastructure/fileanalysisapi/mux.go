package fileanalysisapi

import (
	"BigHw2Go/internal/infrastructure/fileanalysisapi/handlers"
	"net/http"
)

func NewMux(makeAnalysisHandler *handlers.MakeAnalysisHandler) *http.ServeMux {
	mux := http.NewServeMux()

	mux.Handle("GET /file/analysis/{id}", makeAnalysisHandler)
	return mux
}

package fileanalysisapi

import (
	"BigHw2Go/internal/infrastructure/fileanalysisapi/handlers"
	"net/http"
)

func NewMux(makeAnalysisHandler *handlers.MakeAnalysisHandler, getWordCloudHandler *handlers.GetWordCloudHandler) *http.ServeMux {
	mux := http.NewServeMux()

	mux.Handle("GET /file/analysis/{id}", makeAnalysisHandler)
	mux.Handle("GET /file/analysis/wordcloud", getWordCloudHandler)
	return mux
}

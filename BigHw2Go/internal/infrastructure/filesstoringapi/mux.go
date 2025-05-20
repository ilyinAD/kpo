package filesstoringapi

import (
	"BigHw2Go/internal/infrastructure/filesstoringapi/handlers"
	"net/http"
)

func NewMux(uploadHandler *handlers.UploadFileHandler, getFileHandler *handlers.GetFileHandler) *http.ServeMux {
	mux := http.NewServeMux()
	mux.Handle("POST /uploadfile", http.HandlerFunc(uploadHandler.ServeHTTP))
	mux.Handle("GET /getfile", http.HandlerFunc(getFileHandler.ServeHTTP))
	return mux
}

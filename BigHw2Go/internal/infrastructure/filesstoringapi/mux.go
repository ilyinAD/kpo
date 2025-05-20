package filesstoringapi

import (
	"BigHw2Go/internal/infrastructure/filesstoringapi/handlers"
	"net/http"
)

func NewMux(uploadHandler *handlers.UploadFileHandler, getFileHandler *handlers.GetFileHandler) *http.ServeMux {
	mux := http.NewServeMux()
	mux.Handle("POST /uploadfile", uploadHandler)
	mux.Handle("GET /getfile", getFileHandler)
	return mux
}

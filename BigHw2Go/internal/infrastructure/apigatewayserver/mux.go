package apigatewayserver

import (
	"BigHw2Go/internal/infrastructure/apigatewayserver/midlleware"
	"net/http"
	"net/http/httputil"
	"net/url"
)

func CreateProxy(uri *url.URL) http.Handler {
	return httputil.NewSingleHostReverseProxy(uri)
}

func NewMux(filesStoringServerURL *FilesStoringServerURL, loggerMiddleware *midlleware.LoggerMiddleware) *http.ServeMux {
	mux := http.NewServeMux()
	proxyFilesStoringServer := CreateProxy(filesStoringServerURL.URL)

	mux.Handle("POST /uploadfile", loggerMiddleware.Handle(proxyFilesStoringServer))
	mux.Handle("GET /getfile", loggerMiddleware.Handle(proxyFilesStoringServer))
	return mux
}

package apigatewayserver

import (
	"BigHw2Go/internal/infrastructure/apigatewayserver/midlleware"
	"fmt"
	"net/http"
	"net/http/httputil"
	"net/url"

	"github.com/gorilla/mux"
)

func CreateProxy(uri *url.URL) http.Handler {
	return httputil.NewSingleHostReverseProxy(uri)
}

func NewMux(filesStoringServerURL *FilesStoringServerURL, fileAnalysisServerURL *FileAnalysisServerURL, loggerMiddleware *midlleware.LoggerMiddleware) *mux.Router {
	r := mux.NewRouter()

	fmt.Println(filesStoringServerURL)

	proxyFilesStoringServer := CreateProxy(filesStoringServerURL.URL)
	proxyFileAnalysisServer := CreateProxy(fileAnalysisServerURL.URL)
	r.PathPrefix("/file/storage/").Handler(loggerMiddleware.Handle(proxyFilesStoringServer))
	r.PathPrefix("/file/analysis/").Handler(loggerMiddleware.Handle(proxyFileAnalysisServer))
	//r.Handle("/file/storage/", loggerMiddleware.Handle(proxyFilesStoringServer))
	//r.Handle("/file/analysis/", loggerMiddleware.Handle(proxyFileAnalysisServer))
	return r
}

package apigatewayserver

import (
	"log"
	"net/http"
	"time"

	"github.com/gorilla/mux"
)

func NewServer(cfg *Config, mux *mux.Router) *http.Server {
	log.Println(cfg.Address())
	return &http.Server{Addr: cfg.Address(), Handler: mux,
		ReadTimeout:       5 * time.Second,
		ReadHeaderTimeout: 2 * time.Second,
		WriteTimeout:      10 * time.Second,
		IdleTimeout:       60 * time.Second,
	}
}

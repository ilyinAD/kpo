package fileanalysisapi

import (
	"log"
	"net/http"
	"time"
)

func NewServer(cfg *Config, mux *http.ServeMux) *http.Server {
	log.Println(cfg.Address)
	return &http.Server{Addr: cfg.Address, Handler: mux,
		ReadTimeout:       5 * time.Second,
		ReadHeaderTimeout: 2 * time.Second,
		WriteTimeout:      10 * time.Second,
		IdleTimeout:       60 * time.Second,
	}
}

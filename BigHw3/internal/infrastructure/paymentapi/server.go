package paymentapi

import (
	"net/http"
	"time"
)

func NewServer(cfg *Config, mux *http.ServeMux) *http.Server {
	return &http.Server{
		Addr:              cfg.Port,
		Handler:           mux,
		ReadTimeout:       5 * time.Second,
		ReadHeaderTimeout: 2 * time.Second,
		WriteTimeout:      10 * time.Second,
		IdleTimeout:       60 * time.Second,
	}
}

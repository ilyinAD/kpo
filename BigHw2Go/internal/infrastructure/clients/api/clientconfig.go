package api

import (
	"fmt"

	"github.com/caarlos0/env/v11"
)

type ClientConfig interface {
	GetBaseURL() string
	GetPort() string
}

type FileAnalysisClientConfig struct {
	BaseURL string `env:"FILE_ANALYSIS_SERVICE_HOST"`
	Port    string `env:"FILE_ANALYSIS_SERVICE_PORT"`
}

func NewFileAnalysisClientConfig() (ClientConfig, error) {
	cfg := FileAnalysisClientConfig{}

	err := env.Parse(&cfg)
	if err != nil {
		return nil, fmt.Errorf("error parsing .env file %w", err)
	}

	return &cfg, nil
}

func (cfg *FileAnalysisClientConfig) GetBaseURL() string {
	return cfg.BaseURL
}

func (cfg *FileAnalysisClientConfig) GetPort() string {
	return cfg.Port
}

type FileStoringClientConfig struct {
	BaseURL string `env:"FILE_STORING_SERVICE_HOST"`
	Port    string `env:"FILE_STORING_SERVICE_PORT"`
}

func NewFileStoringClientConfig() (ClientConfig, error) {
	cfg := FileStoringClientConfig{}

	err := env.Parse(&cfg)
	if err != nil {
		return nil, fmt.Errorf("error parsing .env file %w", err)
	}

	return &cfg, nil
}

func (cfg *FileStoringClientConfig) GetBaseURL() string {
	return cfg.BaseURL
}

func (cfg *FileStoringClientConfig) GetPort() string {
	return cfg.Port
}

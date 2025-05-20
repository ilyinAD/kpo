package filesstoringapi

import (
	"fmt"

	"github.com/caarlos0/env/v11"
)

type Config struct {
	Address string `env:"FILE_STORING_SERVICE_PORT"`
}

func NewConfig() (*Config, error) {
	cfg := &Config{}
	err := env.Parse(cfg)
	if err != nil {
		return nil, fmt.Errorf("error parsing config from .env: %w", err)
	}

	return cfg, nil
}

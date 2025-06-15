package apigatewayserver

import (
	"fmt"

	"github.com/caarlos0/env/v11"
)

type Config struct {
	Port string `env:"API_GATEWAY_PORT"`
}

func NewConfig() (*Config, error) {
	cfg := &Config{}
	err := env.Parse(cfg)
	if err != nil {
		return nil, fmt.Errorf("error parsing config from .env: %w", err)
	}

	return cfg, nil
}

func (c *Config) Address() string {
	return fmt.Sprintf(":%s", c.Port)
}

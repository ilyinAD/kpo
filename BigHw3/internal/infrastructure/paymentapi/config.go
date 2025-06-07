package paymentapi

import (
	"fmt"

	"github.com/caarlos0/env/v11"
)

type Config struct {
	Port string `env:"PAYMENT_SERVICE_PORT"`
	Host string `env:"PAYMENT_SERVICE_HOST"`
}

func NewConfig() (*Config, error) {
	cfg := &Config{}

	err := env.Parse(cfg)
	if err != nil {
		return nil, fmt.Errorf("parse payment service config: %w", err)
	}

	return cfg, nil
}

func (cfg *Config) GetAddress() string {
	return fmt.Sprintf("%s%s", cfg.Host, cfg.Port)
}

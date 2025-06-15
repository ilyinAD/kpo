package ordersserviceconfig

import (
	"fmt"

	"github.com/caarlos0/env/v11"
)

type Config struct {
	Port string `env:"ORDERS_SERVICE_PORT"`
	Host string `env:"ORDERS_SERVICE_HOST"`
}

func NewConfig() (*Config, error) {
	cfg := &Config{}
	err := env.Parse(cfg)
	if err != nil {
		return nil, fmt.Errorf("error parsing config: %w", err)
	}

	return cfg, nil
}

func (cfg *Config) Address() string {
	return fmt.Sprintf("%s:%s", cfg.Host, cfg.Port)
}

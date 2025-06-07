package paymentconfig

import (
	"BigHw3/database/dbconfig"
	"fmt"

	"github.com/caarlos0/env/v11"
)

type DBConfig struct {
	Host     string `env:"DB_HOST"`
	Port     int    `env:"PAYMENT_DB_PORT"`
	Username string `env:"POSTGRES_USER"`
	Password string `env:"POSTGRES_PASSWORD"`
	Name     string `env:"PAYMENT_POSTGRES_DB"`
}

func (d *DBConfig) ToDSN() string {
	return fmt.Sprintf("postgresql://%s:%s@%s:%d/%s?target_session_attrs=read-write&sslmode=disable",
		d.Username,
		d.Password,
		d.Host,
		d.Port,
		d.Name,
	)
}

func NewDBConfigFromEnv() (dbconfig.DBConfig, error) {
	cfg := &DBConfig{}

	if err := env.Parse(cfg); err != nil {
		return nil, fmt.Errorf("parse config: %w", err)
	}

	return cfg, nil
}

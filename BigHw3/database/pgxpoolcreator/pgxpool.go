package pgxpoolcreator

import (
	"BigHw3/database/dbconfig"
	"context"
	"fmt"
	"log"
	"time"

	"github.com/jackc/pgx/v5/pgxpool"
	"go.uber.org/fx"
)

func NewPgxPoolConfig(cfg dbconfig.DBConfig) (*pgxpool.Config, error) {
	fmt.Printf("DSN: %s\n", cfg.ToDSN())

	connConfig, err := pgxpool.ParseConfig(cfg.ToDSN())
	if err != nil {
		return nil, fmt.Errorf("pgxpoolcreator.NewPgxPool: %w", err)
	}

	connConfig.MaxConns = 12
	connConfig.MinConns = 2
	connConfig.MaxConnIdleTime = time.Second

	return connConfig, nil
}

func NewPgxPool(cfg dbconfig.DBConfig, lc fx.Lifecycle) (*pgxpool.Pool, error) {
	connConfig, err := NewPgxPoolConfig(cfg)
	if err != nil {
		return nil, fmt.Errorf("NewPgxPoolConfig: %w", err)
	}

	ctx := context.Background()

	pool, err := pgxpool.NewWithConfig(ctx, connConfig)
	if err != nil {
		return nil, fmt.Errorf("pgxpoolcreator.NewWithConfig: %w", err)
	}

	lc.Append(fx.Hook{OnStop: func(_ context.Context) error {
		log.Println("Closing database pool")
		pool.Close()
		return nil
	}})

	return pool, nil
}

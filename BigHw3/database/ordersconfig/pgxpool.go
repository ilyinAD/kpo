package ordersconfig

import (
	"BigHw3/database/pgxpoolcreator"
	"fmt"

	"github.com/jackc/pgx/v5/pgxpool"
	"go.uber.org/fx"
)

type OrdersPgxPool struct {
	*pgxpool.Pool
}

func NewOrdersPgxPool(cfg *DBConfig, lc fx.Lifecycle) (*OrdersPgxPool, error) {
	pool, err := pgxpoolcreator.NewPgxPool(cfg, lc)
	if err != nil {
		return nil, fmt.Errorf("NewPgxPool: %w", err)
	}

	return &OrdersPgxPool{pool}, nil
}

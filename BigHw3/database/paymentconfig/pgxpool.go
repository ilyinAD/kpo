package paymentconfig

import (
	"BigHw3/database/pgxpoolcreator"
	"fmt"

	"github.com/jackc/pgx/v5/pgxpool"
	"go.uber.org/fx"
)

type PaymentPgxPool struct {
	*pgxpool.Pool
}

func NewPaymentPgxPool(cfg *DBConfig, lc fx.Lifecycle) (*PaymentPgxPool, error) {
	pool, err := pgxpoolcreator.NewPgxPool(cfg, lc)
	if err != nil {
		return nil, fmt.Errorf("NewPgxPool: %w", err)
	}

	return &PaymentPgxPool{pool}, nil
}

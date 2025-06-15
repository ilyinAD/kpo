package migrations

import (
	"BigHw3/database/ordersconfig"
	"BigHw3/database/pgxpoolcreator"
	"BigHw3/migrations/orders"
	"fmt"

	"go.uber.org/fx"
)

func FxModuleOrders() fx.Option {
	return fx.Options(
		fx.Invoke(func(cfg *ordersconfig.DBConfig) error {
			pgxConfig, err := pgxpoolcreator.NewPgxPoolConfig(cfg)
			if err != nil {
				return fmt.Errorf("NewPgxPoolConfig: %w", err)
			}

			return Migrate(pgxConfig, orders.EmbedOrdersMigrations)
		}),
	)
}

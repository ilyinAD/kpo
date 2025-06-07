package migrations

import (
	"BigHw3/migrations/orders"

	"github.com/jackc/pgx/v5/pgxpool"
	"go.uber.org/fx"
)

func FxModuleOrders() fx.Option {
	return fx.Options(
		fx.Invoke(func(cfg *pgxpool.Config) error {
			return Migrate(cfg, orders.EmbedOrdersMigrations)
		}),
	)
}

package migrations

import (
	"BigHw3/migrations/payment"

	"github.com/jackc/pgx/v5/pgxpool"
	"go.uber.org/fx"
)

func FxModulePayment() fx.Option {
	return fx.Options(
		fx.Invoke(func(cfg *pgxpool.Config) error {
			return Migrate(cfg, payment.EmbedPaymentMigrations)
		}),
	)
}

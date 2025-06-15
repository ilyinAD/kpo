package migrations

import (
	"BigHw3/database/paymentconfig"
	"BigHw3/database/pgxpoolcreator"
	"BigHw3/migrations/payment"
	"fmt"

	"go.uber.org/fx"
)

func FxModulePayment() fx.Option {
	return fx.Options(
		fx.Invoke(func(cfg *paymentconfig.DBConfig) error {
			pgxConfig, err := pgxpoolcreator.NewPgxPoolConfig(cfg)
			if err != nil {
				return fmt.Errorf("NewPgxPoolConfig: %w", err)
			}

			return Migrate(pgxConfig, payment.EmbedPaymentMigrations)
		}),
	)
}

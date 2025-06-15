package database

import (
	"BigHw3/database/paymentconfig"
	"BigHw3/migrations"

	"go.uber.org/fx"
)

func FxModulePayment() fx.Option {
	return fx.Options(
		fx.Provide(paymentconfig.NewDBConfigFromEnv),
		fx.Provide(paymentconfig.NewPaymentPgxPool),
		migrations.FxModulePayment(),
	)
}

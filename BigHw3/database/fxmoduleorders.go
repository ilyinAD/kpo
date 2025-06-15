package database

import (
	"BigHw3/database/ordersconfig"
	"BigHw3/migrations"

	"go.uber.org/fx"
)

func FxModuleOrders() fx.Option {
	return fx.Options(
		fx.Provide(ordersconfig.NewDBConfigFromEnv),
		fx.Provide(ordersconfig.NewOrdersPgxPool),
		migrations.FxModuleOrders(),
	)
}

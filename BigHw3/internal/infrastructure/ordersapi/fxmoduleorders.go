package ordersapi

import "go.uber.org/fx"

func FxModuleOrders() fx.Option {
	return fx.Options(
		fx.Provide(NewConfig),
		fx.Provide(NewMux),
		fx.Provide(NewServer),
		fx.Invoke(RunServer),
	)
}

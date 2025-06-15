package apigatewayserver

import (
	"BigHw3/internal/infrastructure/apigatewayserver/midlleware"
	"BigHw3/internal/infrastructure/apigatewayserver/ordersserviceconfig"
	"BigHw3/internal/infrastructure/apigatewayserver/paymentserviceconfig"

	"go.uber.org/fx"
)

func FxModuleAPIGateway() fx.Option {
	return fx.Options(
		fx.Provide(NewZapLogger),
		fx.Provide(ordersserviceconfig.NewConfig),
		fx.Provide(paymentserviceconfig.NewConfig),
		fx.Provide(NewPaymentServerURL),
		fx.Provide(NewOrdersServerURL),
		fx.Provide(NewMux),
		fx.Provide(NewConfig),
		fx.Provide(midlleware.NewLoggerMiddleware),
		fx.Provide(NewServer),
		fx.Invoke(RunServer),
	)
}

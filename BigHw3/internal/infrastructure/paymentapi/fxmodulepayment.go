package paymentapi

import (
	"BigHw3/internal/infrastructure/paymentapi/handlers"

	"go.uber.org/fx"
)

func FxModulePayment() fx.Option {
	return fx.Options(
		fx.Provide(handlers.NewCreatePaymentHandler),
		fx.Provide(handlers.NewGetPaymentHandler),
		fx.Provide(handlers.NewUpdatePaymentHandler),

		fx.Provide(NewConfig),
		fx.Provide(NewMux),
		fx.Provide(NewServer),
		fx.Invoke(RunServer),
	)
}

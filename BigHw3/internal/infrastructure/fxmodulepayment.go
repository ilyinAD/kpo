package infrastructure

import (
	"BigHw3/internal/infrastructure/logger"
	"BigHw3/internal/infrastructure/paymentapi"
	"BigHw3/internal/infrastructure/repository"
	"BigHw3/internal/infrastructure/usecases"

	"go.uber.org/fx"
)

func FxModulePayment() fx.Option {
	return fx.Options(
		paymentapi.FxModulePayment(),
		repository.FxModulePayment(),
		usecases.FxModulePayment(),
		fx.Provide(logger.NewLogger),
	)
}

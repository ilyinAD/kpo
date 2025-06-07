package usecases

import (
	"BigHw3/internal/infrastructure/usecases/paymentusecase"

	"go.uber.org/fx"
)

func FxModulePayment() fx.Option {
	return fx.Options(
		fx.Provide(paymentusecase.NewPaymentUseCase),
	)
}

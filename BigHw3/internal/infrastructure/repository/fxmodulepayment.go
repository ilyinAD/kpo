package repository

import (
	"BigHw3/internal/infrastructure/repository/paymentrepository"

	"go.uber.org/fx"
)

func FxModulePayment() fx.Option {
	return fx.Options(
		// fx.Provide(txs.NewTxBeginner),
		fx.Provide(paymentrepository.NewPaymentRepository),
	)
}

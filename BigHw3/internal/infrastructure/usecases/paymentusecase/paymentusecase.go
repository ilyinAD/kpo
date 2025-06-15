package paymentusecase

import (
	"BigHw3/internal/domain/paymentservice"
	"BigHw3/internal/infrastructure/repository/paymentrepository"
	"BigHw3/internal/infrastructure/repository/txs"
	"context"
	"fmt"
	"time"

	"github.com/jackc/pgx/v5"
)

type PaymentUseCase struct {
	paymentRepository *paymentrepository.PaymentRepository
	transactor        txs.Transactor
}

func NewPaymentUseCase(paymentRepository *paymentrepository.PaymentRepository) *PaymentUseCase {
	transactor := txs.NewTxBeginner(paymentRepository.Pool.Pool)
	return &PaymentUseCase{paymentRepository, transactor}
}

func (uc *PaymentUseCase) CreatePayment(ctx context.Context, paymentModel *paymentservice.PaymentModel) (*paymentservice.PaymentModel, error) {
	opCtx, cancel := context.WithTimeout(ctx, 5*time.Second)
	defer cancel()

	return uc.paymentRepository.CreatePayment(opCtx, paymentModel)
}

func (uc *PaymentUseCase) GetPayment(ctx context.Context, paymentID int64) (*paymentservice.PaymentModel, error) {
	opCtx, cancel := context.WithTimeout(ctx, 5*time.Second)
	defer cancel()

	return uc.paymentRepository.GetPayment(opCtx, paymentID)
}

func (uc *PaymentUseCase) DepositPayment(ctx context.Context, paymentID, addedBalance int64) (*paymentservice.PaymentModel, error) {
	if addedBalance < 0 {
		return nil, fmt.Errorf("addedBalance is negative")
	}

	opCtx, cancel := context.WithTimeout(ctx, 5*time.Second)
	defer cancel()

	var (
		updatedPaymentModel *paymentservice.PaymentModel
	)

	err := uc.transactor.WithTransaction(opCtx, func(ctx context.Context) error {
		payment, err := uc.paymentRepository.GetPayment(ctx, paymentID)
		if err != nil {
			return fmt.Errorf("paymentRepository: GetPayment: %w", err)
		}

		payment.Balance += addedBalance

		updatedPaymentModel, err = uc.paymentRepository.UpdatePayment(ctx, payment)
		if err != nil {
			return fmt.Errorf("paymentRepository: DepositPayment: %w", err)
		}

		return nil
	}, pgx.TxOptions{
		IsoLevel: pgx.RepeatableRead,
	})
	if err != nil {
		return nil, fmt.Errorf("paymentRepository: DepositPayment: %w", err)
	}

	return updatedPaymentModel, nil
}

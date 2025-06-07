package paymentrepository

import (
	"BigHw3/internal/domain/paymentservice"
	"BigHw3/internal/infrastructure/repository/txs"
	"context"
	"fmt"

	"github.com/jackc/pgx/v5/pgxpool"
)

type PaymentRepository struct {
	pool *pgxpool.Pool
}

func NewPaymentRepository(pool *pgxpool.Pool) *PaymentRepository {
	return &PaymentRepository{pool}
}

func (pr *PaymentRepository) CreatePayment(ctx context.Context, paymentModel *paymentservice.PaymentModel) (*paymentservice.PaymentModel, error) {
	var addedPaymentModel paymentservice.PaymentModel

	queryPerformer := txs.GetQueryPerformer(ctx, pr.pool)

	err := queryPerformer.QueryRow(
		ctx,
		"insert into payment (id, balance) values ($1, $2) returning id, balance",
		paymentModel.ID, paymentModel.Balance,
	).Scan(&addedPaymentModel.ID, &addedPaymentModel.Balance)
	if err != nil {
		return nil, fmt.Errorf("payment create: %w", err)
	}

	return &addedPaymentModel, nil
}

func (pr *PaymentRepository) GetPayment(ctx context.Context, id int64) (*paymentservice.PaymentModel, error) {
	var paymentModel paymentservice.PaymentModel

	queryPerformer := txs.GetQueryPerformer(ctx, pr.pool)

	err := queryPerformer.QueryRow(ctx, "select * from payment where id = $1 for update", id).Scan(&paymentModel.ID, &paymentModel.Balance)
	if err != nil {
		return nil, fmt.Errorf("payment get: %w", err)
	}

	return &paymentModel, nil
}

func (pr *PaymentRepository) UpdatePayment(ctx context.Context, paymentModel *paymentservice.PaymentModel) (*paymentservice.PaymentModel, error) {
	var updatedPaymentModel paymentservice.PaymentModel

	queryPerformer := txs.GetQueryPerformer(ctx, pr.pool)

	err := queryPerformer.QueryRow(ctx, "update payment set balance = $1 where id = $2  returning id, balance", paymentModel.Balance, paymentModel.ID).
		Scan(&updatedPaymentModel.ID, &updatedPaymentModel.Balance)
	if err != nil {
		return nil, fmt.Errorf("payment update: %w", err)
	}

	return &updatedPaymentModel, nil
}

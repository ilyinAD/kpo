package ordersusecase

import (
	"BigHw3/internal/domain/ordersservice"
	"BigHw3/internal/infrastructure/repository/ordersrepository"
	"BigHw3/internal/infrastructure/repository/txs"
	"context"
	"fmt"
	"time"
)

type OrdersUseCase struct {
	ordersRepository *ordersrepository.OrdersRepository
	transactor       txs.Transactor
}

func NewOrdersUseCase(repository *ordersrepository.OrdersRepository) *OrdersUseCase {
	transactor := txs.NewTxBeginner(repository.Pool.Pool)

	return &OrdersUseCase{
		ordersRepository: repository,
		transactor:       transactor,
	}
}

func (uc *OrdersUseCase) GetOrders(ctx context.Context) (ordersservice.OrdersModel, error) {
	opCtx, cancel := context.WithTimeout(ctx, 5*time.Second)
	defer cancel()

	orders, err := uc.ordersRepository.GetAllOrders(opCtx)
	if err != nil {
		return ordersservice.OrdersModel{}, fmt.Errorf("ordersRepository: GetAllOrders: %w", err)
	}

	return orders, nil
}

func (uc *OrdersUseCase) GetOrderStatus(ctx context.Context, id int64) (string, error) {
	opCtx, cancel := context.WithTimeout(ctx, 5*time.Second)
	defer cancel()

	order, err := uc.ordersRepository.GetOrder(opCtx, id)
	if err != nil {
		return "", fmt.Errorf("ordersRepository: GetOrder: %w", err)
	}

	return order.Status, nil
}

package ordersrepository

import (
	"BigHw3/database/ordersconfig"
	"BigHw3/internal/domain/ordersservice"
	"BigHw3/internal/infrastructure/repository/txs"
	"context"
	"fmt"
)

type OrdersRepository struct {
	Pool *ordersconfig.OrdersPgxPool
}

func NewOrdersRepository(pool *ordersconfig.OrdersPgxPool) *OrdersRepository {
	return &OrdersRepository{Pool: pool}
}

func (or *OrdersRepository) GetAllOrders(ctx context.Context) (ordersservice.OrdersModel, error) {
	performer := txs.GetQueryPerformer(ctx, or.Pool)

	var (
		emptyOrdersModel = ordersservice.OrdersModel{}
		ordersModel      ordersservice.OrdersModel
	)

	rows, err := performer.Query(ctx, "select id, user_id, amount, description, status from orders")
	if err != nil {
		return emptyOrdersModel, fmt.Errorf("query: %w", err)
	}

	defer rows.Close()

	for rows.Next() {
		var orderModel ordersservice.OrderModel

		err = rows.Scan(&orderModel.ID, &orderModel.UserID, &orderModel.Amount, &orderModel.Description, &orderModel.Status)
		if err != nil {
			return emptyOrdersModel, fmt.Errorf("rows.Scan: %w", err)
		}

		ordersModel.Orders = append(ordersModel.Orders, &orderModel)
	}

	return ordersModel, nil
}

func (or *OrdersRepository) GetOrder(ctx context.Context, orderID int64) (*ordersservice.OrderModel, error) {
	performer := txs.GetQueryPerformer(ctx, or.Pool)

	var orderModel ordersservice.OrderModel

	err := performer.QueryRow(
		ctx,
		"select id, user_id, amount, description, status from orders where id = $1",
		orderID).
		Scan(&orderModel.ID, &orderModel.UserID, &orderModel.Amount, &orderModel.Description, &orderModel.Status)
	if err != nil {
		return nil, fmt.Errorf("queryRow: %w", err)
	}

	return &orderModel, nil
}

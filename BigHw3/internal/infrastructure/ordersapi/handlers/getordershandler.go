package handlers

import (
	"BigHw3/internal/infrastructure/usecases/ordersusecase"
	"encoding/json"
	"fmt"
	"net/http"
)

type GetOrdersHandler struct {
	ordersUseCase *ordersusecase.OrdersUseCase
}

func NewGetOrdersHandler(orderUseCase *ordersusecase.OrdersUseCase) *GetOrdersHandler {
	return &GetOrdersHandler{
		ordersUseCase: orderUseCase,
	}
}

func (handler *GetOrdersHandler) ServeHTTP(w http.ResponseWriter, r *http.Request) {
	orders, err := handler.ordersUseCase.GetOrders(r.Context())
	if err != nil {
		http.Error(w, fmt.Sprintf("ordersUseCase: GetOrders: %v", err.Error()), http.StatusInternalServerError)
	}

	encoder := json.NewEncoder(w)
	if err := encoder.Encode(orders); err != nil {
		http.Error(w, fmt.Sprintf("encoder: encode orders: %v", err.Error()), http.StatusInternalServerError)
	}
}

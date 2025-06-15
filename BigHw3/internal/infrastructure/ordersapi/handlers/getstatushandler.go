package handlers

import (
	"BigHw3/internal/infrastructure/usecases/ordersusecase"
	"encoding/json"
	"fmt"
	"net/http"
	"strconv"
)

type GetStatusHandler struct {
	ordersUseCase *ordersusecase.OrdersUseCase
}

func NewGetStatusHandler(ordersUseCase *ordersusecase.OrdersUseCase) *GetStatusHandler {
	return &GetStatusHandler{ordersUseCase: ordersUseCase}
}

func (handler *GetStatusHandler) ServeHTTP(w http.ResponseWriter, r *http.Request) {
	id := r.PathValue("id")

	orderID, err := strconv.ParseInt(id, 10, 64)
	if err != nil {
		http.Error(w, fmt.Sprintf("strconv: id to int: %v", err.Error()), http.StatusBadRequest)
		return
	}

	status, err := handler.ordersUseCase.GetOrderStatus(r.Context(), orderID)
	if err != nil {
		http.Error(w, fmt.Sprintf("ordersUseCase: GetOrderStatus: %v", err.Error()), http.StatusInternalServerError)
	}

	encoder := json.NewEncoder(w)

	err = encoder.Encode(status)
	if err != nil {
		http.Error(w, fmt.Sprintf("encoder.Encode: %v", err.Error()), http.StatusInternalServerError)
	}
}

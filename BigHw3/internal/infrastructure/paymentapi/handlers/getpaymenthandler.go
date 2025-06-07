package handlers

import (
	"BigHw3/internal/infrastructure/usecases/paymentusecase"
	"encoding/json"
	"fmt"
	"net/http"
	"strconv"

	"go.uber.org/zap"
)

type GetPaymentHandler struct {
	paymentUseCase *paymentusecase.PaymentUseCase
	logger         *zap.Logger
}

func NewGetPaymentHandler(paymentUseCase *paymentusecase.PaymentUseCase, logger *zap.Logger) *GetPaymentHandler {
	return &GetPaymentHandler{
		paymentUseCase: paymentUseCase,
		logger:         logger,
	}
}

func (handler *GetPaymentHandler) ServeHTTP(w http.ResponseWriter, r *http.Request) {
	id := r.PathValue("id")

	paymentID, err := strconv.ParseInt(id, 10, 64)
	if err != nil {
		http.Error(w, fmt.Sprintf("strconv: id to int: %v", err.Error()), http.StatusBadRequest)
		return
	}

	paymentModel, err := handler.paymentUseCase.GetPayment(r.Context(), paymentID)
	if err != nil {
		http.Error(w, fmt.Sprintf("paymentUseCase: GetPayment: %v", err), http.StatusBadRequest)
		return
	}

	encoder := json.NewEncoder(w)

	if err = encoder.Encode(paymentModel); err != nil {
		http.Error(w, fmt.Sprintf("json encode: %v", err), http.StatusInternalServerError)
	}
}

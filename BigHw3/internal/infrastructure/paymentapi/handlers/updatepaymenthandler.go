package handlers

import (
	"BigHw3/internal/infrastructure/usecases/paymentusecase"
	"encoding/json"
	"fmt"
	"net/http"
	"strconv"

	"go.uber.org/zap"
)

type UpdatePaymentHandler struct {
	paymentUseCase *paymentusecase.PaymentUseCase
	logger         *zap.Logger
}

func NewUpdatePaymentHandler(paymentUseCase *paymentusecase.PaymentUseCase, logger *zap.Logger) *UpdatePaymentHandler {
	return &UpdatePaymentHandler{
		paymentUseCase: paymentUseCase,
		logger:         logger,
	}
}

func (h *UpdatePaymentHandler) ServeHTTP(w http.ResponseWriter, r *http.Request) {
	query := r.URL.Query()
	id := r.PathValue("id")
	balance := query.Get("balance")

	paymentID, err := strconv.ParseInt(id, 10, 64)
	if err != nil {
		http.Error(w, fmt.Sprintf("strconv: Parseint: %v", err.Error()), http.StatusBadRequest)
		return
	}

	paymentBalance, err := strconv.ParseInt(balance, 10, 64)
	if err != nil {
		http.Error(w, fmt.Sprintf("strconv: Parseint: %v", err.Error()), http.StatusBadRequest)
		return
	}

	paymentModel, err := h.paymentUseCase.DepositPayment(r.Context(), paymentID, paymentBalance)
	if err != nil {
		http.Error(w, fmt.Sprintf("paymentUseCase: DepositPayment: %v", err.Error()), http.StatusBadRequest)
		return
	}

	encoder := json.NewEncoder(w)

	if err := encoder.Encode(paymentModel); err != nil {
		http.Error(w, fmt.Sprintf("encoder.Encode: %v", err.Error()), http.StatusBadRequest)
		return
	}
}

package handlers

import (
	"BigHw3/internal/domain/paymentservice"
	"BigHw3/internal/infrastructure/usecases/paymentusecase"
	"encoding/json"
	"fmt"
	"net/http"

	"go.uber.org/zap"
)

type CreatePaymentHandler struct {
	paymentUseCase *paymentusecase.PaymentUseCase
	logger         *zap.Logger
}

func NewCreatePaymentHandler(paymentUseCase *paymentusecase.PaymentUseCase, logger *zap.Logger) *CreatePaymentHandler {
	return &CreatePaymentHandler{
		paymentUseCase: paymentUseCase,
		logger:         logger,
	}
}

func (h *CreatePaymentHandler) ServeHTTP(w http.ResponseWriter, r *http.Request) {
	h.logger.Info("CreatePaymentHandler start handling query")

	var paymentModel *paymentservice.PaymentModel
	err := json.NewDecoder(r.Body).Decode(&paymentModel)
	if err != nil {
		http.Error(w, fmt.Sprintf("unmarshal1 body: %v", err.Error()), http.StatusBadRequest)
		return
	}

	addedPaymentModel, err := h.paymentUseCase.CreatePayment(r.Context(), paymentModel)
	if err != nil {
		http.Error(w, fmt.Sprintf("paymentUseCase: CreatePaymnet: %v", err.Error()), http.StatusBadRequest)
		return
	}

	encoder := json.NewEncoder(w)
	if err := encoder.Encode(addedPaymentModel); err != nil {
		http.Error(w, fmt.Sprintf("encoder: encode added model: %v", err.Error()), http.StatusInternalServerError)
	}

	h.logger.Info("CreatePaymentHandler successfully end handling query")
}

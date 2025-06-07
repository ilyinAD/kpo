package paymentapi

import (
	"BigHw3/internal/infrastructure/paymentapi/handlers"
	"net/http"
)

func NewMux(createAccountHandler *handlers.CreatePaymentHandler, getAccountHandler *handlers.GetPaymentHandler, updatePaymentHandler *handlers.UpdatePaymentHandler) *http.ServeMux {
	mux := http.NewServeMux()

	mux.Handle("POST /payment/create", createAccountHandler)
	mux.Handle("GET /payment/get/{id}", getAccountHandler)
	mux.Handle("POST /payment/update", updatePaymentHandler)

	return mux
}

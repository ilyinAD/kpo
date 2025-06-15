package apigatewayserver

import (
	"BigHw3/internal/infrastructure/apigatewayserver/midlleware"
	"fmt"
	"net/http"
	"net/http/httputil"
	"net/url"

	"github.com/gorilla/mux"
)

func CreateProxy(uri *url.URL) http.Handler {
	return httputil.NewSingleHostReverseProxy(uri)
}

func NewMux(paymentServerURL *PaymentServerURL, ordersServerURL *OrdersServerURL, loggerMiddleware *midlleware.LoggerMiddleware) *mux.Router {
	r := mux.NewRouter()

	fmt.Println(paymentServerURL)

	proxyPaymentServer := CreateProxy(paymentServerURL.URL)
	proxyOrdersServer := CreateProxy(ordersServerURL.URL)
	r.PathPrefix("/payment/").Handler(loggerMiddleware.Handle(proxyPaymentServer))
	r.PathPrefix("/orders/").Handler(loggerMiddleware.Handle(proxyOrdersServer))
	return r
}

package apigatewayserver

import (
	"BigHw3/internal/infrastructure/apigatewayserver/paymentserviceconfig"
	"fmt"
	"net/url"
)

type PaymentServerURL struct {
	URL *url.URL
}

func NewPaymentServerURL(cfg *paymentserviceconfig.Config) (*PaymentServerURL, error) {
	uri, err := url.Parse(cfg.Address())
	if err != nil {
		return nil, fmt.Errorf("failed to parse url: %w", err)
	}

	return &PaymentServerURL{
		URL: uri,
	}, nil
}

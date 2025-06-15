package apigatewayserver

import (
	"BigHw3/internal/infrastructure/apigatewayserver/ordersserviceconfig"
	"fmt"
	"net/url"
)

type OrdersServerURL struct {
	URL *url.URL
}

func NewOrdersServerURL(cfg *ordersserviceconfig.Config) (*OrdersServerURL, error) {
	uri, err := url.Parse(cfg.Address())
	if err != nil {
		return nil, fmt.Errorf("failed to parse url: %w", err)
	}

	return &OrdersServerURL{
		URL: uri,
	}, nil
}

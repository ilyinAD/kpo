package wordcloudapi

import (
	"fmt"
	"time"

	"github.com/caarlos0/env/v11"
	"github.com/go-resty/resty/v2"
)

type APIConfig struct {
	URL string `env:"WORD_CLOUD_API_URL"`
}

func NewAPIConfig() (*APIConfig, error) {
	cfg := &APIConfig{}
	err := env.Parse(cfg)
	if err != nil {
		return nil, fmt.Errorf("error parsing env vars: %w", err)
	}

	return cfg, nil
}

type API struct {
	client *resty.Client
	cfg    *APIConfig
}

func NewAPI(cfg *APIConfig) *API {
	client := resty.New()
	client.SetTimeout(5 * time.Second)
	client.SetDoNotParseResponse(true)

	return &API{
		client: client,
		cfg:    cfg,
	}
}

func (api *API) GetWordCloud(text string) (*resty.Response, error) {
	header := map[string]string{
		"Content-Type": "application/json",
	}

	body := map[string]string{
		"format":     "png",
		"width":      "1000",
		"height":     "1000",
		"fontFamily": "sans-serif",
		"fontScale":  "15",
		"scale":      "linear",
		"text":       text,
	}

	resp, err := api.client.R().
		SetHeaders(header).
		SetBody(body).
		SetDoNotParseResponse(true).
		Post(api.cfg.URL)
	if err != nil {
		return nil, fmt.Errorf("client: POST: wordcloudapi: %w", err)
	}

	return resp, nil
}

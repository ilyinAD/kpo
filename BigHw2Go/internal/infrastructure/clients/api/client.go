package api

import (
	"fmt"
	"time"

	"github.com/go-resty/resty/v2"
)

type Client struct {
	client  *resty.Client
	baseURL string
	port    string
}

func NewClient(cfg ClientConfig) *Client {
	client := resty.New()
	client.SetDoNotParseResponse(true)
	client.SetTimeout(5 * time.Second)
	return &Client{client: client, baseURL: cfg.GetBaseURL(), port: cfg.GetPort()}
}

func (bc *Client) Get(path string, headers map[string]string) (*resty.Response, error) {
	url := fmt.Sprintf("%s%s%s", bc.baseURL, bc.port, path)
	req := bc.client.R()

	if headers != nil {
		req.SetHeaders(headers)
	}

	resp, err := req.Get(url)
	if err != nil {
		return nil, fmt.Errorf("GET %s failed: %s", url, err)
	}

	if resp.StatusCode() != 200 {
		return nil, fmt.Errorf("GET %s failed", url)
	}

	return resp, nil
}

func (bc *Client) Post(path string, headers map[string]string, request interface{}) (*resty.Response, error) {
	url := fmt.Sprintf("%s%s%s", bc.baseURL, bc.port, path)

	req := bc.client.R()

	if headers != nil {
		req.SetHeaders(headers)
	}

	if request != nil {
		req.SetBody(request)
	}

	resp, err := req.Post(url)
	if err != nil {
		return nil, fmt.Errorf("POST %s failed: %s", url, err)
	}

	if resp.StatusCode() != 200 {
		return nil, fmt.Errorf("POST %s failed", url)
	}

	return resp, nil
}

func (bc *Client) Delete(path string, headers map[string]string, request interface{}) (*resty.Response, error) {
	url := fmt.Sprintf("%s%s%s", bc.baseURL, bc.port, path)

	req := bc.client.R()

	if headers != nil {
		req.SetHeaders(headers)
	}

	if request != nil {
		req.SetBody(request)
	}

	resp, err := req.Delete(url)
	if err != nil {
		return nil, fmt.Errorf("DELETE %s failed: %s", url, err)
	}

	if resp.StatusCode() != 200 {
		return nil, fmt.Errorf("DELETE %s failed", url)
	}

	return resp, nil
}

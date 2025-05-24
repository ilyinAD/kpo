package apigatewayserver

import (
	"BigHw2Go/internal/infrastructure/apigatewayserver/fileanalysisserverconfig"
	"fmt"
	"net/url"
)

type FileAnalysisServerURL struct {
	URL *url.URL
}

func NewFileAnalysisServerURL(cfg *fileanalysisserverconfig.Config) (*FileAnalysisServerURL, error) {
	uri, err := url.Parse(fmt.Sprintf("%s%s", cfg.Host, cfg.Port))
	if err != nil {
		return nil, fmt.Errorf("failed to parse url: %w", err)
	}

	return &FileAnalysisServerURL{
		URL: uri,
	}, nil
}

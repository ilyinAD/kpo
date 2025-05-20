package apigatewayserver

import (
	"BigHw2Go/internal/infrastructure/apigatewayserver/filesstoringserverconfig"
	"fmt"
	"net/url"
)

type FilesStoringServerURL struct {
	URL *url.URL
}

func NewFilesStoringServerURL(cfg *filesstoringserverconfig.Config) (*FilesStoringServerURL, error) {
	uri, err := url.Parse(fmt.Sprintf("%s%s", cfg.Host, cfg.Port))
	if err != nil {
		return nil, fmt.Errorf("failed to parse url: %w", err)
	}

	return &FilesStoringServerURL{
		URL: uri,
	}, nil
}

package usecases

import (
	"BigHw2Go/internal/infrastructure/clients/wordcloudapi"
	"fmt"
	"io"
)

type WordCloudAPIUseCase struct {
	api                 *wordcloudapi.API
	filesStorageUseCase *FilesStorageUseCase
}

func NewWordCloudAPIUseCase(api *wordcloudapi.API, filesStorageUseCase *FilesStorageUseCase) *WordCloudAPIUseCase {
	return &WordCloudAPIUseCase{
		api:                 api,
		filesStorageUseCase: filesStorageUseCase,
	}
}

func (uc *WordCloudAPIUseCase) GetImage(text string) (io.ReadCloser, error) {
	resp, err := uc.api.GetWordCloud(text)
	if err != nil {
		return nil, fmt.Errorf("uc.api.GetWordCloud: %w", err)
	}

	return resp.RawBody(), nil
}

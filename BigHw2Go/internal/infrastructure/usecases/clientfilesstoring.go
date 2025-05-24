package usecases

import (
	"BigHw2Go/internal/infrastructure/clients/api"
	"fmt"
	"io"

	"github.com/google/uuid"
)

type ClientFilesStoring struct {
	client *api.Client
}

func NewClientFilesStoring(client *api.Client) *ClientFilesStoring {
	return &ClientFilesStoring{client: client}
}

func (cf *ClientFilesStoring) GetFile(fileID uuid.UUID) (io.ReadCloser, error) {
	resp, err := cf.client.Get(fmt.Sprintf("/file/storage/getfile/%v", fileID), map[string]string{})
	if err != nil {
		return nil, fmt.Errorf("error client GET: %v", err)
	}

	return resp.RawResponse.Body, nil
}

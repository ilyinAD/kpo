package usecases

import (
	"errors"
	"fmt"
	"io"
	"os"
	"path/filepath"
)

type FilesStorageUseCase struct {
}

func NewFilesStorageUseCase() *FilesStorageUseCase {
	return &FilesStorageUseCase{}
}

func (fu *FilesStorageUseCase) UploadFileToStorage(file io.Reader, location string) error {
	dir := filepath.Dir(location)

	err := os.MkdirAll(dir, 0755)
	if err != nil {
		return fmt.Errorf("error creating directory %s: %s", dir, err)
	}

	dst, err := os.Create(location)
	if err != nil {
		return fmt.Errorf("create file: %w", err)
	}

	defer func(dst *os.File) {
		cErr := dst.Close()
		if cErr != nil {
			if err == nil {
				err = cErr
			}
			err = errors.Join(err, cErr)
		}
	}(dst)

	_, err = io.Copy(dst, file)
	if err != nil {
		return fmt.Errorf("copy file: %w", err)
	}

	return nil
}

func (fu *FilesStorageUseCase) GetFileByLocation(location string) (io.ReadCloser, error) {
	return os.Open(location)
}

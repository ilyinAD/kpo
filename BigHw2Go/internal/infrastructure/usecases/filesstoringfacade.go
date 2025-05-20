package usecases

import (
	"BigHw2Go/internal/domain/domainfilesstoringserver"
	"context"
	"fmt"
	"io"
	"mime/multipart"

	"github.com/google/uuid"
)

type FilesStoringFacade struct {
	filesStorageUseCase *FilesStorageUseCase
	filesUseCase        *FileUseCase
}

func NewFilesStoringFacade(filesStorageUseCase *FilesStorageUseCase, filesUseCase *FileUseCase) *FilesStoringFacade {
	return &FilesStoringFacade{
		filesStorageUseCase: filesStorageUseCase,
		filesUseCase:        filesUseCase,
	}
}

func (fu *FilesStoringFacade) UploadFile(ctx context.Context, file multipart.File, handler *multipart.FileHeader, location string) (uuid.UUID, error) {
	fileModel := domainfilesstoringserver.NewFileModel(handler.Filename, location, uuid.New)

	id, err := fu.filesUseCase.AddFile(ctx, fileModel)
	if err != nil {
		return uuid.Nil, fmt.Errorf("filesUseCase: AddFile: %w", err)
	}

	err = fu.filesStorageUseCase.UploadFileToStorage(file, location)
	if err != nil {
		return uuid.Nil, fmt.Errorf("filesStorageUseCase: UploadFileToStorage: %w", err)
	}

	return id, nil
}

func (fu *FilesStoringFacade) GetFile(ctx context.Context, fileID uuid.UUID) (io.ReadCloser, error) {
	fileModel, err := fu.filesUseCase.GetFile(ctx, fileID)
	if err != nil {
		return nil, fmt.Errorf("filesUseCase: GetFile: %w", err)
	}

	return fu.filesStorageUseCase.GetFileByLocation(fileModel.Location)
}

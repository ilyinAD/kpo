package usecases

import (
	"BigHw2Go/internal/domain/domainfilesstoringserver"
	"BigHw2Go/internal/infrastructure/repository"
	"context"
	"fmt"
	"time"

	"github.com/google/uuid"
)

type FileUseCase struct {
	fileRepository *repository.FilesRepository
}

func NewFileUseCase(fileRepository *repository.FilesRepository) *FileUseCase {
	return &FileUseCase{fileRepository: fileRepository}
}

func (fu *FileUseCase) AddFile(ctx context.Context, fileModel *domainfilesstoringserver.FileModel) (uuid.UUID, error) {
	opCtx, cancel := context.WithTimeout(ctx, 5*time.Second)
	defer cancel()

	addedFileModel, err := fu.fileRepository.AddFile(opCtx, fileModel)
	if err != nil {
		return uuid.Nil, fmt.Errorf("fileRepository: AddFile: %w", err)
	}

	return addedFileModel.ID, nil
}

func (fu *FileUseCase) GetFile(ctx context.Context, fileID uuid.UUID) (*domainfilesstoringserver.FileModel, error) {
	opCtx, cancel := context.WithTimeout(ctx, 5*time.Second)
	defer cancel()

	return fu.fileRepository.GetFileByID(opCtx, fileID)
}

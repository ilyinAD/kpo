package repository

import (
	"BigHw2Go/internal/domain/domainfilesstoringserver"
	"context"
	"database/sql"
	"errors"
	"fmt"

	"github.com/google/uuid"
	"github.com/jackc/pgx/v5/pgxpool"
)

type FilesRepository struct {
	pool *pgxpool.Pool
}

func NewFilesRepository(pool *pgxpool.Pool) *FilesRepository {
	return &FilesRepository{pool}
}

func (fr *FilesRepository) AddFile(ctx context.Context,
	fileModel *domainfilesstoringserver.FileModel) (*domainfilesstoringserver.FileModel, error) {
	var returnedFileModel domainfilesstoringserver.FileModel

	err := fr.pool.QueryRow(
		ctx,
		"insert into files (id, name, location) values($1, $2, $3) returning id, name, location",
		fileModel.ID, fileModel.Name, fileModel.Location).
		Scan(&returnedFileModel.ID, &fileModel.Name, &fileModel.Location)
	if err != nil {
		return nil, fmt.Errorf("queryRow: insert file: %w", err)
	}

	return &returnedFileModel, nil
}

func (fr *FilesRepository) GetFileByLocation(ctx context.Context, location string) (*domainfilesstoringserver.FileModel, error) {
	var returnedFileModel domainfilesstoringserver.FileModel

	err := fr.pool.QueryRow(
		ctx,
		"select id, name, location from files where location = $1", location).
		Scan(&returnedFileModel.ID, &returnedFileModel.Name, &returnedFileModel.Location)
	if err != nil {
		if errors.Is(err, sql.ErrNoRows) {
			return nil, nil
		}

		return nil, fmt.Errorf("queryRow: get file: %w", err)
	}

	return &returnedFileModel, nil
}

func (fr *FilesRepository) GetFileByID(ctx context.Context, fileID uuid.UUID) (*domainfilesstoringserver.FileModel, error) {
	var returnedFileModel domainfilesstoringserver.FileModel

	err := fr.pool.QueryRow(
		ctx,
		"select id, name, location from files where id = $1", fileID).
		Scan(&returnedFileModel.ID, &returnedFileModel.Name, &returnedFileModel.Location)
	if err != nil {
		return nil, fmt.Errorf("queryRow: get file: %w", err)
	}

	return &returnedFileModel, nil
}

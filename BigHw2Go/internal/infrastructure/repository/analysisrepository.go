package repository

import (
	"BigHw2Go/internal/domain/domainfileanalysisserver"
	"context"
	"errors"
	"fmt"

	"github.com/google/uuid"
	"github.com/jackc/pgx/v5"
	"github.com/jackc/pgx/v5/pgxpool"
)

type AnalysisRepository struct {
	pool *pgxpool.Pool
}

func NewAnalysisRepository(pool *pgxpool.Pool) *AnalysisRepository {
	return &AnalysisRepository{pool}
}

func (ar *AnalysisRepository) GetByID(ctx context.Context, id uuid.UUID) (*domainfileanalysisserver.FileAnalysisModel, error) {
	var returnedFileAnalysisModel domainfileanalysisserver.FileAnalysisModel

	err := ar.pool.QueryRow(ctx, "select id, sym_cnt, word_cnt, pr_cnt, img_location from analysis where analysis.id = $1", id).
		Scan(&returnedFileAnalysisModel.ID, &returnedFileAnalysisModel.SymbolCnt, &returnedFileAnalysisModel.WordCnt,
			&returnedFileAnalysisModel.ParagraphCnt, &returnedFileAnalysisModel.ImgLocation)
	if err != nil {
		if errors.Is(err, pgx.ErrNoRows) {
			return nil, nil
		}

		return nil, fmt.Errorf("get analysis by id failed: %v", err)
	}

	return &returnedFileAnalysisModel, nil
}

func (ar *AnalysisRepository) AddAnalysis(ctx context.Context, analysisModel *domainfileanalysisserver.FileAnalysisModel) (
	*domainfileanalysisserver.FileAnalysisModel, error) {
	var addedAnalysisModel domainfileanalysisserver.FileAnalysisModel

	err := ar.pool.QueryRow(ctx, "insert into analysis values ($1, $2, $3, $4, $5) returning id, sym_cnt, word_cnt, pr_cnt, img_location",
		analysisModel.ID, analysisModel.SymbolCnt, analysisModel.WordCnt, analysisModel.ParagraphCnt, addedAnalysisModel.ImgLocation).
		Scan(&addedAnalysisModel.ID, &addedAnalysisModel.SymbolCnt, &addedAnalysisModel.WordCnt,
			&addedAnalysisModel.ParagraphCnt, &addedAnalysisModel.ImgLocation)
	if err != nil {
		return nil, fmt.Errorf("add analysis failed: %v", err)
	}

	return &addedAnalysisModel, nil
}

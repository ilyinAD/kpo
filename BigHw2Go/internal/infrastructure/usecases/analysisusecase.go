package usecases

import (
	"BigHw2Go/internal/domain/domainfileanalysisserver"
	"BigHw2Go/internal/infrastructure/repository"
	"context"
	"time"

	"github.com/google/uuid"
)

type AnalysisUseCase struct {
	analysisRepository *repository.AnalysisRepository
}

func NewAnalysisUseCase(analysisRepository *repository.AnalysisRepository) *AnalysisUseCase {
	return &AnalysisUseCase{analysisRepository}
}

func (auc *AnalysisUseCase) GetByID(ctx context.Context, analysisID uuid.UUID) (*domainfileanalysisserver.FileAnalysisModel, error) {
	opCtx, cancel := context.WithTimeout(ctx, 5*time.Second)
	defer cancel()

	return auc.analysisRepository.GetByID(opCtx, analysisID)
}

func (auc *AnalysisUseCase) AddAnalysis(ctx context.Context, analysisModel *domainfileanalysisserver.FileAnalysisModel) (
	*domainfileanalysisserver.FileAnalysisModel, error) {
	opCtx, cancel := context.WithTimeout(ctx, 5*time.Second)
	defer cancel()

	return auc.analysisRepository.AddAnalysis(opCtx, analysisModel)
}

package infrastructure

import (
	"BigHw2Go/internal/infrastructure/clients"
	"BigHw2Go/internal/infrastructure/fileanalysisapi"
	"BigHw2Go/internal/infrastructure/repository"
	"BigHw2Go/internal/infrastructure/usecases"

	"go.uber.org/fx"
)

func FxModuleFileAnalysis() fx.Option {
	return fx.Options(
		clients.FxModuleFileAnalysis(),
		usecases.FxModuleFileAnalysis(),
		fileanalysisapi.FxModuleFileAnalysis(),
		repository.FxModuleFileAnalysis(),
	)
}

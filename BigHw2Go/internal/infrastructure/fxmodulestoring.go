package infrastructure

import (
	"BigHw2Go/internal/infrastructure/filesstoringapi"
	"BigHw2Go/internal/infrastructure/repository"
	"BigHw2Go/internal/infrastructure/usecases"

	"go.uber.org/fx"
)

func FxModuleStoring() fx.Option {
	return fx.Options(
		filesstoringapi.FxModuleStoring(),
		repository.FxModuleFilesStoring(),
		usecases.FxModuleFilesStoring(),
	)
}

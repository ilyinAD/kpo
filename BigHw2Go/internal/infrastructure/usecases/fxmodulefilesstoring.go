package usecases

import "go.uber.org/fx"

func FxModuleFilesStoring() fx.Option {
	return fx.Options(
		fx.Provide(NewFileUseCase),
		fx.Provide(NewFilesStorageUseCase),
		fx.Provide(NewFilesStoringFacade),
	)
}

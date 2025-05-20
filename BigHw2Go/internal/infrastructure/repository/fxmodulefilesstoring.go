package repository

import "go.uber.org/fx"

func FxModuleFilesStoring() fx.Option {
	return fx.Options(
		fx.Provide(NewFilesRepository),
	)
}

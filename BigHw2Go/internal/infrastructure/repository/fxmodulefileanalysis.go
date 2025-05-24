package repository

import "go.uber.org/fx"

func FxModuleFileAnalysis() fx.Option {
	return fx.Options(
		fx.Provide(NewAnalysisRepository),
	)
}

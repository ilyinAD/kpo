package usecases

import "go.uber.org/fx"

func FxModuleFileAnalysis() fx.Option {
	return fx.Options(
		fx.Provide(NewClientFilesStoring),
		fx.Provide(NewFileAnalysisFacade),
		fx.Provide(NewAnalysisUseCase),
	)
}

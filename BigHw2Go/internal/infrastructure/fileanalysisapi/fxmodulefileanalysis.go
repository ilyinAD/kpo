package fileanalysisapi

import (
	"BigHw2Go/internal/infrastructure/fileanalysisapi/handlers"

	"go.uber.org/fx"
)

func FxModuleFileAnalysis() fx.Option {
	return fx.Options(
		fx.Provide(NewConfig),
		fx.Provide(NewZapLogger),
		fx.Provide(NewMux),
		fx.Provide(NewServer),
		fx.Provide(handlers.NewMakeAnalysisHandler),
		fx.Invoke(RunServer),
	)
}

package database

import (
	"BigHw2Go/database/fileanalysisconfig"
	"BigHw2Go/migrations"

	"go.uber.org/fx"
)

func FxModuleFileAnalysis() fx.Option {
	return fx.Options(
		fx.Provide(fileanalysisconfig.NewDBConfigFromEnv),
		fx.Provide(NewPgxPoolConfig),
		fx.Provide(NewPgxPool),
		migrations.FxModuleFileAnalysis(),
	)
}

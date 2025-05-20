package database

import (
	"BigHw2Go/database/filesstoringconfig"
	"BigHw2Go/migrations"

	"go.uber.org/fx"
)

func FxModuleFilesStoring() fx.Option {
	return fx.Options(
		fx.Provide(filesstoringconfig.NewDBConfigFromEnv),
		fx.Provide(NewPgxPoolConfig),
		fx.Provide(NewPgxPool),
		migrations.FxModuleFilesString(),
	)
}

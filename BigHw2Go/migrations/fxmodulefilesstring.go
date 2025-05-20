package migrations

import (
	"BigHw2Go/migrations/filesstoring"

	"go.uber.org/fx"
)

func FxModuleFilesString() fx.Option {
	return fx.Options(
		fx.Invoke(filesstoring.Migrate),
	)
}

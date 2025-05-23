package migrations

import (
	"BigHw2Go/migrations/filesstoring"

	"github.com/jackc/pgx/v5/pgxpool"
	"go.uber.org/fx"
)

func FxModuleFilesString() fx.Option {
	return fx.Options(
		fx.Invoke(func(cfg *pgxpool.Config) error {
			return Migrate(cfg, filesstoring.EmbedStoringMigrations)
		}),
	)
}

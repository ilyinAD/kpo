package migrations

import (
	"BigHw2Go/migrations/fileanalysis"

	"github.com/jackc/pgx/v5/pgxpool"
	"go.uber.org/fx"
)

func FxModuleFileAnalysis() fx.Option {
	return fx.Options(
		fx.Invoke(func(cfg *pgxpool.Config) error {
			return Migrate(cfg, fileanalysis.EmbedAnalysisMigrations)
		}),
	)
}

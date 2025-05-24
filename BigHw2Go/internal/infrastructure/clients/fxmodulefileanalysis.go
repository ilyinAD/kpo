package clients

import (
	"BigHw2Go/internal/infrastructure/clients/api"

	"go.uber.org/fx"
)

func FxModuleFileAnalysis() fx.Option {
	return fx.Options(
		fx.Provide(api.NewFileStoringClientConfig),
		fx.Provide(api.NewClient),
	)
}

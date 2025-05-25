package clients

import (
	"BigHw2Go/internal/infrastructure/clients/api"
	"BigHw2Go/internal/infrastructure/clients/wordcloudapi"

	"go.uber.org/fx"
)

func FxModuleFileAnalysis() fx.Option {
	return fx.Options(
		fx.Provide(api.NewFileStoringClientConfig),
		fx.Provide(api.NewClient),
		fx.Provide(wordcloudapi.NewAPIConfig),
		fx.Provide(wordcloudapi.NewAPI),
	)
}

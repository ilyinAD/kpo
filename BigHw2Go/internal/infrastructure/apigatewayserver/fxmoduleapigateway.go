package apigatewayserver

import (
	"BigHw2Go/internal/infrastructure/apigatewayserver/fileanalysisserverconfig"
	"BigHw2Go/internal/infrastructure/apigatewayserver/filesstoringserverconfig"
	"BigHw2Go/internal/infrastructure/apigatewayserver/midlleware"

	"go.uber.org/fx"
)

func FxModuleAPIGateway() fx.Option {
	return fx.Options(
		fx.Provide(NewZapLogger),
		fx.Provide(filesstoringserverconfig.NewConfig),
		fx.Provide(fileanalysisserverconfig.NewConfig),
		fx.Provide(NewFilesStoringServerURL),
		fx.Provide(NewFileAnalysisServerURL),
		fx.Provide(NewMux),
		fx.Provide(NewConfig),
		fx.Provide(midlleware.NewLoggerMiddleware),
		fx.Provide(NewServer),
		fx.Invoke(RunServer),
	)
}

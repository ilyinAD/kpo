package apigatewayserver

import (
	"BigHw2Go/internal/infrastructure/apigatewayserver/filesstoringserverconfig"
	"BigHw2Go/internal/infrastructure/apigatewayserver/midlleware"

	"go.uber.org/fx"
)

func FxModuleAPIGateway() fx.Option {
	return fx.Options(
		fx.Provide(filesstoringserverconfig.NewConfig),
		fx.Provide(NewFilesStoringServerURL),
		fx.Provide(NewMux),
		fx.Provide(NewConfig),
		fx.Provide(NewZapLogger),
		fx.Provide(midlleware.NewLoggerMiddleware),
		fx.Provide(NewServer),
		fx.Invoke(RunServer),
	)
}

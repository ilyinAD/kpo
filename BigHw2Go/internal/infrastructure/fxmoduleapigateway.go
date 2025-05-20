package infrastructure

import (
	"BigHw2Go/internal/infrastructure/apigatewayserver"

	"go.uber.org/fx"
)

func FxModuleAPIGateway() fx.Option {
	return fx.Options(
		apigatewayserver.FxModuleAPIGateway(),
	)
}

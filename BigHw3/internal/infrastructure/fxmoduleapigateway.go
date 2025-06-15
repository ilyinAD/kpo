package infrastructure

import (
	"BigHw3/internal/infrastructure/apigatewayserver"

	"go.uber.org/fx"
)

func FxModuleAPIGateway() fx.Option {
	return fx.Options(
		apigatewayserver.FxModuleAPIGateway(),
	)
}

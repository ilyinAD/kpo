package main

import (
	"BigHw3/internal/infrastructure"

	"go.uber.org/fx"
	"go.uber.org/fx/fxevent"
	"go.uber.org/zap"
)

func BuildApp() fx.Option {
	return fx.Options(
		infrastructure.FxModuleAPIGateway(),
		fx.WithLogger(func(logger *zap.Logger) fxevent.Logger {
			return &fxevent.ZapLogger{Logger: logger}
		}),
	)
}

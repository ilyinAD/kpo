package main

import (
	"BigHw2Go/internal/infrastructure"

	"go.uber.org/fx"
	"go.uber.org/fx/fxevent"
	"go.uber.org/zap"
)

func BuildApp() fx.Option {
	return fx.Options(
		infrastructure.FxModuleAPIGateway(),
		fx.WithLogger(func(log *zap.Logger) fxevent.Logger {
			return &fxevent.ZapLogger{Logger: log}
		}),
	)
}

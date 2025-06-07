package main

import (
	"BigHw3/database"
	"BigHw3/internal/infrastructure"

	"go.uber.org/fx"
	"go.uber.org/fx/fxevent"
	"go.uber.org/zap"
)

func BuildApp() fx.Option {
	return fx.Options(
		database.FxModulePayment(),
		infrastructure.FxModulePayment(),
		fx.WithLogger(func(logger *zap.Logger) fxevent.Logger {
			return &fxevent.ZapLogger{Logger: logger}
		}),
	)
}

package filesstoringapi

import (
	"BigHw2Go/internal/infrastructure/filesstoringapi/handlers"

	"go.uber.org/fx"
)

func FxModuleStoring() fx.Option {
	return fx.Options(
		fx.Provide(NewZapLogger),
		fx.Provide(NewConfig),
		fx.Provide(NewMux),
		fx.Provide(NewServer),
		fx.Provide(handlers.NewUploadFileHandler),
		fx.Provide(handlers.NewGetFileHandler),
		fx.Invoke(RunServer),
	)
}

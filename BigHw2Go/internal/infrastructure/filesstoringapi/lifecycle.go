package filesstoringapi

import (
	"context"
	"log"
	"net/http"

	"go.uber.org/fx"
)

func RunServer(lc fx.Lifecycle, server *http.Server) {
	lc.Append(fx.Hook{
		OnStart: func(_ context.Context) error {
			go func() {
				if err := server.ListenAndServe(); err != nil {
					log.Fatalf("server failed to start or finished with error: %v\n", err.Error())
				}
			}()
			log.Printf("Files Storing Server started successfully")

			return nil
		},
		OnStop: func(ctx context.Context) error {
			log.Println("Shutting down HTTP server...")
			return server.Shutdown(ctx)
		},
	})
}

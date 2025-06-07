package paymentapi

import (
	"context"
	"log"
	"net/http"

	"go.uber.org/fx"
)

func RunServer(lc fx.Lifecycle, server *http.Server) {
	lc.Append(fx.Hook{
		OnStart: func(context.Context) error {
			go func() {
				if err := server.ListenAndServe(); err != nil {
					log.Fatalf("Payment server failed to start or finished with error: %v\n", err.Error())
				}
			}()
			log.Printf("Payment Server started successfully")

			return nil
		},
		OnStop: func(ctx context.Context) error {
			log.Println("Shutting down HTTP server...")
			return server.Shutdown(ctx)
		},
	})
}

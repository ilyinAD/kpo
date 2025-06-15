package apigatewayserver

import "go.uber.org/zap"

func NewZapLogger() *zap.Logger {
	return zap.NewExample()
}

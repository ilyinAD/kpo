package main

import (
	"BigHw3/internal/infrastructure/utils"

	"go.uber.org/fx"
)

func main() {
	utils.LoadEnv()
	fx.New(BuildApp()).Run()
}

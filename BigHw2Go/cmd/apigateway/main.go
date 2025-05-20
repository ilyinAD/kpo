package main

import (
	"BigHw2Go/internal/utils"

	"go.uber.org/fx"
)

func main() {
	utils.LoadEnv()
	fx.New(BuildApp()).Run()
}

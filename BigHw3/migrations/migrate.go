package migrations

import (
	"embed"
	"fmt"
	"log"
	"os"

	"github.com/jackc/pgx/v5/pgxpool"
	"github.com/jackc/pgx/v5/stdlib"
	"github.com/pressly/goose/v3"
)

func Migrate(cfg *pgxpool.Config, embedMigrations embed.FS) (err error) {
	log.Println("start migrations")

	isMigrate := os.Getenv("IS_DOCKER_COMPOSE_MIGRATION")
	if isMigrate == "true" {
		log.Println("Migrations was ran by docker container")
		return nil
	}

	log.Println("migrations ok")

	goose.SetBaseFS(embedMigrations)

	log.Println("migrations ok")

	if err := goose.SetDialect("postgres"); err != nil {
		log.Fatal("goose.SetDialect", err)
	}

	log.Println("migrations ok")

	db := stdlib.OpenDB(*(cfg.ConnConfig))
	defer func() {
		err = db.Close()
	}()

	log.Println("migrations ok")

	if err := goose.Up(db, "."); err != nil {
		return fmt.Errorf("goose.Up %w", err)
	}

	log.Println("migrations ok")

	log.Println("migrations done")

	return nil
}

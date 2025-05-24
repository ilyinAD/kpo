package filesstoring

import "embed"

//go:embed 0001_init_db.sql
var EmbedStoringMigrations embed.FS

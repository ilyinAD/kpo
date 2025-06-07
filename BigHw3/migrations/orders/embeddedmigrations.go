package orders

import "embed"

//go:embed 0001_init_db.sql
var EmbedOrdersMigrations embed.FS

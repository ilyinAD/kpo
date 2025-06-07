package payment

import "embed"

//go:embed 0001_init_db.sql
var EmbedPaymentMigrations embed.FS

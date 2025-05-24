package fileanalysis

import "embed"

//go:embed 0001_init_db.sql
var EmbedAnalysisMigrations embed.FS

package dbconfig

type DBConfig interface {
	ToDSN() string
}

package txs

import (
	"context"
	"errors"
	"fmt"

	"github.com/jackc/pgx/v5"
	"github.com/jackc/pgx/v5/pgconn"
	"github.com/jackc/pgx/v5/pgxpool"
)

type QueryPerformer interface {
	Exec(ctx context.Context, sql string, arguments ...any) (commandTag pgconn.CommandTag, err error)
	Query(ctx context.Context, sql string, args ...any) (pgx.Rows, error)
	QueryRow(ctx context.Context, sql string, args ...any) pgx.Row
}

type txKey struct{}

func GetQueryPerformer(ctx context.Context, defaultPerformer QueryPerformer) QueryPerformer {
	if tx, ok := ctx.Value(txKey{}).(pgx.Tx); ok {
		return tx
	}

	return defaultPerformer
}

type Transactor interface {
	WithTransaction(context.Context, func(context.Context) error, pgx.TxOptions) error
}

type TxBeginner struct {
	pool *pgxpool.Pool
}

func NewTxBeginner(pool *pgxpool.Pool) Transactor {
	return &TxBeginner{pool: pool}
}

func injectCtx(ctx context.Context, tx pgx.Tx) context.Context {
	return context.WithValue(ctx, txKey{}, tx)
}

func (tb *TxBeginner) WithTransaction(ctx context.Context, fn func(context.Context) error, opts pgx.TxOptions) error {
	tx, err := tb.pool.BeginTx(
		ctx,
		opts,
	)
	if err != nil {
		return fmt.Errorf("pool start transaction failed: %w", err)
	}

	defer func() {
		if err != nil {
			err = errors.Join(err, tx.Rollback(ctx))
		}
	}()

	err = fn(injectCtx(ctx, tx))
	if err != nil {
		return fmt.Errorf("transaction failed: %w", err)
	}

	return tx.Commit(ctx)
}

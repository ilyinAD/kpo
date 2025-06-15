-- +goose Up
-- +goose StatementBegin
begin;

create table if not exists payment (
     id bigint primary key,
     balance bigint check (balance >= 0)
);

end;

-- +goose StatementEnd

-- +goose Down
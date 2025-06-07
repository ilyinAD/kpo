-- +goose Up
-- +goose StatementBegin
begin;

create table if not exists payment (
     id bigint primary key,
     balance bigint
);

end;

-- +goose StatementEnd

-- +goose Down
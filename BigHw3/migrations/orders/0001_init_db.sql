-- +goose Up
-- +goose StatementBegin
begin;

create table if not exists files (
    id bigint primary key,
    user_id bigint,
    amount bigint,
    description text,
    status text
);

create table if not exists orderstasks (

)

end;

-- +goose StatementEnd

-- +goose Down
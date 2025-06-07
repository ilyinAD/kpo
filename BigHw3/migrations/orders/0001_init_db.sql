-- +goose Up
-- +goose StatementBegin
begin;

create table if not exists files (
    id bigint primary key,
    user_id uuid,
    amount bigint,
    description text,
    status text
);

end;

-- +goose StatementEnd

-- +goose Down
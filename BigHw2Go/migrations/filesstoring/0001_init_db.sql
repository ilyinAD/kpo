-- +goose Up
-- +goose StatementBegin
begin;

create table if not exists files (
                                     id uuid primary key,
                                     name text,
                                     location text unique
);

end;

-- +goose StatementEnd

-- +goose Down
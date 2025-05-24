-- +goose Up
-- +goose StatementBegin
begin;

create table if not exists analysis (
                                     id uuid primary key,
                                     sym_cnt bigint,
                                     word_cnt bigint,
                                     pr_cnt bigint,
                                     img_location text
);

end;

-- +goose StatementEnd

-- +goose Down
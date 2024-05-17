drop table flipcards;
create table if not exists flipcards (
    id            int generated always as identity primary key,
    native_word   varchar(30),
    translation   varchar(30)
)
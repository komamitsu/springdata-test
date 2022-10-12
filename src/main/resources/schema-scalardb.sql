create table if not exists "group" (id bigserial primary key, name text not null);
-- create table if not exists "user" (id bigserial primary key, name text not null, group_id bigint references "group" (id) not null, point int not null);
create table if not exists "user" (id bigserial primary key, name text not null, group_id bigint references "group" (id), point int not null);

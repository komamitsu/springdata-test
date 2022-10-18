create table if not exists "group" (id bigint primary key, name text not null);
create table if not exists "user" (id bigint primary key, name text not null, group_id bigint references "group" (id), point int not null);

create table if not exists public.group (id bigint, name text, primary key (id));
create table if not exists public.user (id bigint, name text, group_id bigint, point int, primary key (id));
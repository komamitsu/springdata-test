create coordinator tables if not exist;
create table if not exists public.group (id bigint, name text, primary key (id));
create index if not exists on public.group (name);
create table if not exists public.user (id bigint, name text, group_id bigint, point int, primary key (id));
create index if not exists on public.user (group_id);
create index if not exists on public.user (name);
truncate coordinator tables;
truncate table public.user;
truncate table public.group;
CREATE USER beauty_owner WITH PASSWORD '!@beauty';
GRANT ALL PRIVILEGES ON DATABASE "beauty_radar" to beauty_owner;
GRANT ALL ON SCHEMA beauty TO beauty_owner;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA beauty TO "beauty_owner";

create schema beauty;

drop table if exists beauty.master;
create table beauty.master (
	master_id bigserial primary key not null,
	address text,
	rating real,
	created_at timestamp not null default current_timestamp,
	updated_at timestamp not null default current_timestamp
);

drop table if exists beauty.user;
create table beauty.user (
	user_id bigserial primary key not null,
	upn text not null unique,
	login text not null unique,
	master_id bigint references master(master_id),
	name text,
	phone text,
	email text,
	img bytea,
	rating real,
	created_at timestamp not null default current_timestamp,
	updated_at timestamp not null default current_timestamp
);

insert into beauty."user" (upn, login) values ('uvx86v1jCrPaRi0P3EUvrs4JX1P2', '+79999999999');
select * from beauty.user;

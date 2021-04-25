--drop database if exists beauty_radar;
--create database beauty_radar;

--drop schema if exists beauty;
--create schema beauty;

drop table if exists beauty.master cascade;
create table beauty.master (
	master_id bigserial primary key,
	address text not null,
	coordinates varchar not null,
	rating real,
	created_at timestamp not null default current_timestamp,
	updated_at timestamp not null default current_timestamp
);

drop table if exists beauty.master_category cascade;
create table beauty.master_category (
	master_category_id serial primary key,
	name varchar not null unique,
	description text
);

drop table if exists beauty.master_categories cascade;
create table beauty.master_categories (
	master_id bigint not null references beauty.master(master_id),
	master_category_id int not null references beauty.master_category(master_category_id),
	created_at timestamp not null default current_timestamp
);

drop table if exists beauty.user cascade;
create table beauty.user (
	user_id bigserial primary key,
	upn text not null unique,
	login text not null unique,
	master_id bigint references beauty.master(master_id),
	name varchar,
	phone varchar,
	email varchar,
	img bytea,
	rating real,
	created_at timestamp not null default current_timestamp,
	updated_at timestamp not null default current_timestamp
);

drop table if exists beauty.favorite cascade;
create table beauty.favorite (
	user_id bigint not null references beauty.user(user_id),
	master_id bigint not null references beauty.master(master_id)
);

drop table if exists beauty.client_review cascade;
create table beauty.client_review (
	client_review_id bigserial primary key,
	user_id bigint not null references beauty.user(user_id),
	master_id bigint not null references beauty.master(master_id),
	rating real not null,
	description text,
	created_at timestamp not null default current_timestamp,
	updated_at timestamp not null default current_timestamp
);

drop table if exists beauty.master_review cascade;
create table beauty.master_review (
	master_review_id bigserial primary key,
	master_id bigint not null references beauty.master(master_id),
	user_id bigint not null references beauty.user(user_id),
	rating real not null,
	description text,
	created_at timestamp not null default current_timestamp,
	updated_at timestamp not null default current_timestamp
);

drop table if exists beauty.gallery cascade;
create table beauty.gallery (
	gallery_id bigserial primary key,
	user_id bigint not null references beauty.user(user_id),
	img bytea not null,
	preview_img bytea,
	created_at timestamp not null default current_timestamp
);

drop table if exists beauty.role cascade;
create table beauty.role (
	role_id serial primary key,
	name varchar not null unique
);

drop table if exists beauty.user_roles cascade;
create table beauty.user_roles (
	user_id bigint not null references beauty.user(user_id),
	role_id int not null references beauty.role(role_id),
	created_at timestamp not null default current_timestamp
);

drop table if exists beauty.master_workday cascade;
create table beauty.master_workday (
	master_workday_id bigserial primary key,
	master_id bigint not null references beauty.master(master_id),
	date timestamp not null,
	start_time timestamp not null,
	end_time timestamp not null
);

drop table if exists beauty.service_category cascade;
create table beauty.service_category (
	service_category_id serial primary key,
	master_category_id int references beauty.master_category(master_category_id), 
	name varchar not null unique,
	description text
);

drop table if exists beauty.service_description cascade;
create table beauty.service_description (
	service_description_id bigserial primary key,
	master_id bigint not null references beauty.master(master_id),
	service_category_id int not null references beauty.service_category(service_category_id),
	duration int not null,
	price double precision
);

drop table if exists beauty.job_status cascade;
create table beauty.job_status (
	job_status_id serial primary key,
	name varchar not null unique
);

drop table if exists beauty.job cascade;
create table beauty.job (
	job_id bigserial primary key,
	master_workday_id bigint not null references beauty.master_workday(master_workday_id),
	user_id bigint not null references beauty.user(user_id),
	service_description_id bigint not null references beauty.service_description(service_description_id),
	job_status_id int not null references beauty.job_status(job_status_id),
	start_time timestamp not null,
	end_time timestamp not null,
	created_at timestamp not null default current_timestamp,
	updated_at timestamp not null default current_timestamp
);

insert into beauty."user" (upn, login) values ('uvx86v1jCrPaRi0P3EUvrs4JX1P2', '+79999999999');
--select * from beauty.user;

--drop user if exists beauty_owner;

-- ПАРОЛЬ
--CREATE USER beauty_owner WITH PASSWORD '';
-- ПАРОЛЬ

--GRANT ALL PRIVILEGES ON DATABASE "beauty_radar" to beauty_owner;
--GRANT ALL ON SCHEMA beauty TO beauty_owner;
--GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA beauty TO "beauty_owner";
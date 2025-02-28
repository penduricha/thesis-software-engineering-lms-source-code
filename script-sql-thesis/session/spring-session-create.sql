use project_thesis_final;

create table if not exists spring_session (
    primary_id varchar(255) not null,
    session_id varchar(255) not null,
    session_data blob not null,
    last_access_time bigint not null,
    expiry_time bigint not null,
    principal_name varchar(255),
    constraint session_pk primary key (session_id)
);

create index if not exists idx_last_access_time on spring_session (last_access_time);


DROP INDEX IF EXISTS idx_last_access_time ON spring_session;
drop table spring_session;

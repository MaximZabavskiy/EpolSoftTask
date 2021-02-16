create table m_tasks
(
    id  bigserial not null
        constraint m_tasks_pkey
            primary key,
    name varchar(100) not null,
    description varchar(100) not null,
    start_date date,
    end_date date,
    status bigint not null
);

alter table m_tasks owner to maxim;

create table m_elements
(
    id bigserial not null
        constraint m_elements_pk
            primary key,
    task_id bigint not null
        constraint m_elements_m_tasks_id_fk
            references m_tasks,
    name varchar(100) not null,
    description varchar(100) not null,
    value varchar(100) not null
);

alter table m_elements owner to maxim;



create table holiday_history
(
    holiday_history_id BIGINT   not null auto_increment,
    person_id          BIGINT   not null,
    history            LONGTEXT null,
    constraint holiday_history_pk
        primary key (holiday_history_id)
);


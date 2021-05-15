create table holiday_because_death
(
    id          BIGINT    not null auto_increment,
    type        char(13)  null DEFAULT 'DEATH_HOLIDAY',
    description char(255) null,
    day         int       null,
    constraint holiday_because_death_pk
        primary key (id)
);


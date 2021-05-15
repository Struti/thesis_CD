create table basic_holiday
(
    id          BIGINT    not null auto_increment,
    type        char(13)  null DEFAULT 'BASIC_HOLIDAY',
    description char(255) null,
    day         int       null,
    constraint basic_holiday_pk
        primary key (id)
);

create table dad_holiday
(
    id           BIGINT    not null auto_increment,
    type         char(11)  null DEFAULT 'DAD_HOLIDAY',
    description  char(255) null,
    limit_months int       null,
    day          int       null,
    constraint dad_holiday_pk
        primary key (id)
);

create table national_holiday
(
    id               BIGINT                not null auto_increment,
    national_holiday date                  not null,
    fix              boolean default false not null,
    constraint national_holiday_pk
        primary key (id)
);


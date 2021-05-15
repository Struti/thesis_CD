create table user_disability_based_holiday
(
    id          BIGINT    not null auto_increment,
    type        char(23)  null DEFAULT 'USER_DISABILITY_HOLIDAY',
    description char(255) null,
    day         int       null,
    constraint user_disability_based_holiday_pk
        primary key (id)
);

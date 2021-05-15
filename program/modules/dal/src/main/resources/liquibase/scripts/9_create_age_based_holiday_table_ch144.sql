create table age_based_holiday
(
    id          BIGINT    not null auto_increment,
    type        char(11)  null DEFAULT 'AGE_HOLIDAY',
    description char(255) null,
    age         int       null,
    day         int       null,
    constraint age_based_holiday_pk
        primary key (id)
);

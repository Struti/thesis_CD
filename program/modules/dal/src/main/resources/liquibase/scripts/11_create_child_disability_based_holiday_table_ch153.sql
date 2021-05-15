create table child_disability_based_holiday
(
    id              BIGINT    not null auto_increment,
    type            char(24)  null DEFAULT 'CHILD_DISABILITY_HOLIDAY',
    description     char(255) null,
    children_number int       null,
    day             int       null,
    constraint child_disability_based_holiday_pk
        primary key (id)
);

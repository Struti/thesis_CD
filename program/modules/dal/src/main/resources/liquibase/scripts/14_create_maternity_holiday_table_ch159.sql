create table maternity_holiday
(
    id          BIGINT    not null auto_increment,
    type        char(22)  null DEFAULT 'MATERNITY_HOLIDAY',
    description char(255) null,
    before_day  int       null,
    after_day   int       null,
    constraint maternity_holiday_pk
        primary key (id)
);

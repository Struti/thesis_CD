create table holiday_calculation_plan
(
    calculation_plan_id BIGINT    not null auto_increment,
    holiday_types       LONGTEXT  null,
    description         char(255) null,
    constraint holiday_calculation_plan_pk
        primary key (calculation_plan_id)
);

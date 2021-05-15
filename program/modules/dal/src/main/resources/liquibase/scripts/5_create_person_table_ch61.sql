create table person
(
    person_id                   BIGINT                                         not null auto_increment,
    title                       ENUM ('NONE','DR1','DR2','PHD','HABIL','PROF') null,
    full_name                   char(100)                                      null,
    birth_name                  char(100)                                      null,
    gender                      ENUM ('MALE','FEMALE')                         not null,
    birth_date                  date                                           not null,
    disability                  BOOLEAN                                        null,
    have_child                  BOOLEAN                                        null,
    start_date                  date                                           not null,
    last_date                   date                                           null,
    employee_role               ENUM ('EMPLOYEE','SUPERVISOR')                 not null,
    actual_location             TINYTEXT                                       null,
    holiday_calculation_plan_id BIGINT                                         null,
    specific_plan               LONGTEXT                                       null,
    holiday_history_id          BIGINT                                         null,
    constraint person_pk
        primary key (person_id),
    constraint person_holiday_calculation_plan_calculation_plan_id_fk
        foreign key (holiday_calculation_plan_id) references holiday_calculation_plan (calculation_plan_id),
    constraint person_holiday_history_holiday_history_id_fk
        foreign key (holiday_history_id) references holiday_history (holiday_history_id)
            on update cascade
            on delete cascade
);

alter table child
    add constraint child_person_person_id_fk
        foreign key (person_id) references person (person_id);

alter table holiday_history
    add constraint holiday_history_person_person_id_fk
        foreign key (person_id) references person (person_id);


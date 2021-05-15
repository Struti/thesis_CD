create table child
(
    child_id                 BIGINT  not null auto_increment,
    born                     boolean not null,
    person_id                BIGINT  not null,
    expected_date            date    null,
    birth_date               date    null,
    disability               boolean not null,
    disability_cert_exp_date date    null,
    constraint child_pk
        primary key (child_id)
);


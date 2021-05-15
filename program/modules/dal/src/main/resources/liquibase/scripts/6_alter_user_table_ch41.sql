alter table user
    add person_id BIGINT not null;

alter table user
    add constraint user_person_person_id_fk
        foreign key (person_id) references person (person_id)
            on update cascade
            on delete cascade;

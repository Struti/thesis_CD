INSERT INTO holidayDB.person (person_id, title, full_name, birth_name, gender, birth_date, disability, have_child, start_date, last_date, employee_role,
                              actual_location, holiday_calculation_plan_id, holiday_history_id)
VALUES (1, 'NONE', 'SUPER ADMIN', 'SUPER ADMIN', 'MALE', '1992-03-04', 0, 0, '2012-09-01', null, 'SUPERVISOR', 'Miskolc,Hungary', null, null);

INSERT INTO holidayDB.user (user_id, email, password, role, person_id)
VALUES (1, 'aitnehez@uni-miskolc.hu', 'Test1234', 'SUPER_ADMIN', 1);

INSERT INTO holidayDB.person (person_id, title, full_name, birth_name, gender, birth_date, disability, have_child, start_date, last_date, employee_role,
                              actual_location, holiday_calculation_plan_id, holiday_history_id)
VALUES (2, 'Dr', 'Kulcsárné Forrai Mónika', 'Forrai Mónika', 'FEMALE', '1980-12-12', 0, 0, '2000-12-15', null, 'EMPLOYEE', 'Miskolc,Hungary', null, null);

INSERT INTO holidayDB.user (user_id, email, password, role, person_id)
VALUES (2, 'aitkfm@uni-miskolc.hu', 'Test1234', 'USER', 1);

INSERT INTO holidayDB.person (person_id, title, full_name, birth_name, gender, birth_date, disability, have_child, start_date, last_date, employee_role,
                              actual_location, holiday_calculation_plan_id, holiday_history_id)
VALUES (3, 'NONE', 'Germuskáné Tőzsér Enikő', 'Tőzsér Enikő', 'FEMALE', '1980-12-12', 0, 0, '2000-12-15', null, 'EMPLOYEE', 'Miskolc,Hungary', null, null);

INSERT INTO holidayDB.user (user_id, email, password, role, person_id)
VALUES (3, 'aiteniko@uni-miskolc.hu', 'Test1234', 'ADMIN', 1);

INSERT INTO holidayDB.person (person_id, title, full_name, birth_name, gender, birth_date, disability, have_child, start_date, last_date, employee_role,
                              actual_location, holiday_calculation_plan_id, holiday_history_id)
VALUES (4, 'Dr', 'Nehéz Károly', 'Nehéz Károly', 'MALE', '1973-12-12', 0, 0, '1999-12-15', null, 'SUPERVISOR', 'Miskolc,Hungary', null, null);

INSERT INTO holidayDB.user (user_id, email, password, role, person_id)
VALUES (4, 'aitnehez@uni-miskolc.hu', 'Test1234', 'USER', 1);

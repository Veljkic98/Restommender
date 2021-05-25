insert into user (username, password, first_name, last_name, type) values ('miro', '123', 'Miroslav', 'Kostic', 0);
insert into user (username, password, first_name, last_name, type) values ('veki', '123', 'Veljko', 'Drazic', 1);
insert into user (username, password, first_name, last_name, type) values ('niki', '123', 'Nikola', 'Skundric', 1);
insert into user (username, password, first_name, last_name, type) values ('jova', '123', 'Ivica', 'Dacic', 1);
insert into user (username, password, first_name, last_name, type) values ('dule', '123', 'Andrej', 'Blazic', 1);
insert into user (username, password, first_name, last_name, type) values ('laki', '123', 'Lazar', 'Pikic', 1);
insert into user (username, password, first_name, last_name, type) values ('aki', '123', 'Aleksandar', 'Petrovic', 1);
insert into user (username, password, first_name, last_name, type) values ('zoki', '123', 'Dragan', 'Krnjajic', 1);
insert into user (username, password, first_name, last_name, type) values ('drale', '123', 'Dragan', 'Vujinovic', 1);

insert into restaurant (name, location, music, accomodation, type, smoking_area, nonsmoking_area, alcoholic_drinks,nonalcoholic_drinks, pet_friendly, kid_friendly) values
    ('Petrus', 0.2, 'glasna', 'visokoSedenje', 'moderni', true, true, true, true, false, false);
insert into restaurant (name, location, music, accomodation, type, smoking_area, nonsmoking_area, alcoholic_drinks,nonalcoholic_drinks, pet_friendly, kid_friendly) values
    ('corso', 2, 'opustajuca', 'udobno', 'tradicionalni', true, false, true, true, true, false);
insert into restaurant (name, location, music, accomodation, type, smoking_area, nonsmoking_area, alcoholic_drinks,nonalcoholic_drinks, pet_friendly, kid_friendly) values
    ('atrijum', 10, 'opustajuca', 'udobno', 'svirka', true, false, true, true, false, true);

insert into reservation (user_id, restaurant_id, num_of_persons, discount) values (2, 1, 2, 0);
insert into reservation (user_id, restaurant_id, num_of_persons, discount) values (3, 2, 4, 0);
insert into reservation (user_id, restaurant_id, num_of_persons, discount) values (4, 3, 5, 0);
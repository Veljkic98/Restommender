insert into authority (name) values ('ROLE_AUTH_USER');

insert into authenticated_user (id, email, password, first_name, last_name, type) values (nextval('person_seq'), 'mail1@gmail.com','$2a$10$smy7Eo0CdCrhcjYe8lAOMeMoxqeGTHGilpNcOiAAMUQR0F.5EVmHG', 'Miroslav', 'Kostic', 0);
insert into authenticated_user (id, email, password, first_name, last_name, type) values (nextval('person_seq'), 'mail2@gmail.com', '$2a$10$smy7Eo0CdCrhcjYe8lAOMeMoxqeGTHGilpNcOiAAMUQR0F.5EVmHG', 'Veljko', 'Drazic', 1);
insert into authenticated_user (id, email, password, first_name, last_name, type) values (nextval('person_seq'), 'mail3@gmail.com', '$2a$10$smy7Eo0CdCrhcjYe8lAOMeMoxqeGTHGilpNcOiAAMUQR0F.5EVmHG', 'Nikola', 'Skundric', 1);
insert into authenticated_user (id, email, password, first_name, last_name, type) values (nextval('person_seq'), 'mail4@gmail.com', '$2a$10$smy7Eo0CdCrhcjYe8lAOMeMoxqeGTHGilpNcOiAAMUQR0F.5EVmHG', 'Ivica', 'Dacic', 1);
insert into authenticated_user (id, email, password, first_name, last_name, type) values (nextval('person_seq'), 'mail5@gmail.com', '$2a$10$smy7Eo0CdCrhcjYe8lAOMeMoxqeGTHGilpNcOiAAMUQR0F.5EVmHG', 'Andrej', 'Blazic', 1);
insert into authenticated_user (id, email, password, first_name, last_name, type) values (nextval('person_seq'), 'mail6@gmail.com', '$2a$10$smy7Eo0CdCrhcjYe8lAOMeMoxqeGTHGilpNcOiAAMUQR0F.5EVmHG', 'Lazar', 'Pikic', 1);
insert into authenticated_user (id, email, password, first_name, last_name, type) values (nextval('person_seq'), 'mail7@gmail.com','$2a$10$smy7Eo0CdCrhcjYe8lAOMeMoxqeGTHGilpNcOiAAMUQR0F.5EVmHG', 'Aleksandar', 'Petrovic', 1);
insert into authenticated_user (id, email, password, first_name, last_name, type) values (nextval('person_seq'), 'mail8@gmail.com', '$2a$10$smy7Eo0CdCrhcjYe8lAOMeMoxqeGTHGilpNcOiAAMUQR0F.5EVmHG', 'Dragan', 'Krnjajic', 1);
insert into authenticated_user (id, email, password, first_name, last_name, type) values (nextval('person_seq'), 'mail9@gmail.com', '$2a$10$smy7Eo0CdCrhcjYe8lAOMeMoxqeGTHGilpNcOiAAMUQR0F.5EVmHG', 'Dragan', 'Vujinovic', 1);

insert into user_authority (user_id, authority_id) values (1, 1);
insert into user_authority (user_id, authority_id) values (2, 1);
insert into user_authority (user_id, authority_id) values (3, 1);
insert into user_authority (user_id, authority_id) values (4, 1);
insert into user_authority (user_id, authority_id) values (5, 1);
insert into user_authority (user_id, authority_id) values (6, 1);
insert into user_authority (user_id, authority_id) values (7, 1);
insert into user_authority (user_id, authority_id) values (8, 1);
insert into user_authority (user_id, authority_id) values (9, 1);

insert into restaurant (name, location, music, accomodation,  smoking_area, nonsmoking_area, alcoholic_drinks,nonalcoholic_drinks, pet_friendly, rate, kid_friendly) values
                        ('Petrus', 0.2, 'relaxing', 'udobno', true, true, true, true, true, 4, true);
insert into restaurant (name, location, music, accomodation, smoking_area, nonsmoking_area, alcoholic_drinks,nonalcoholic_drinks, pet_friendly, rate, kid_friendly) values
                        ('corso', 2, 'relaxing', 'tradicionalno', true, false, true, true, true, 4, true);
insert into restaurant (name, location, music, accomodation,  smoking_area, nonsmoking_area, alcoholic_drinks,nonalcoholic_drinks, pet_friendly, rate, kid_friendly) values
                        ('atrijum', 10, 'loud', 'udobno', true, false, true, true, false, 2, true);

insert into reservation (user_id, restaurant_id, num_of_persons, discount) values (2, 1, 2, 0);
insert into reservation (user_id, restaurant_id, num_of_persons, discount) values (3, 2, 4, 0);
insert into reservation (user_id, restaurant_id, num_of_persons, discount) values (4, 3, 5, 0);
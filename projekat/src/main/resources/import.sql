/* KORISNICI */
INSERT INTO user(user_id, first_name, last_name, password, pid, user_name, user_type, active, entity_version) VALUES (1, 'nikola', 'stojanovic', 123, 123, 'nikola', 3, false, 1);
INSERT INTO user(user_id, first_name, last_name, password, pid, user_name, user_type, active, entity_version) VALUES (2, 'milos', 'tepic', 123, 123, 'mitep', 1, true, 1);
INSERT INTO user(user_id, first_name, last_name, password, pid, user_name, user_type, active, entity_version) VALUES (3, 'julija', 'mirkovic', 123, 123, 'masato', 0, true, 1);

/* PORUKE */
INSERT INTO message(message_id, receiver_id, sender_id, content, entity_version) VALUES (1, 2, 1, 'Tepicu macane, nisi ti za bacanje!', 0);
INSERT INTO message(message_id, receiver_id, sender_id, content, entity_version) VALUES (2, 1, 2, 'Dobra fora', 0);

/* MESTO */
INSERT INTO location(location_id, state, city, street_name, street_number, entity_version) VALUES (1, 'Republika Srbija', 'Novi Sad', 'Bulevar cara Lazara', '11', 0);
INSERT INTO location(location_id, state, city, street_name, street_number, entity_version) VALUES (2, 'Republika Srbija', 'Beograd', 'Jurija Gagarina', '8', 0);
INSERT INTO location(location_id, state, city, street_name, street_number, entity_version) VALUES (3, 'Republika Srbija', 'Novi Sad', 'Novosadskog sajma', '35', 0);

/* TIP SMESTAJA */
INSERT INTO accomodation_type(accomodation_type_id, name, entity_version) VALUES (1, 'Apartment', 0);
INSERT INTO accomodation_type(accomodation_type_id, name, entity_version) VALUES (2, 'Hotel', 0);
INSERT INTO accomodation_type(accomodation_type_id, name, entity_version) VALUES (3, 'Bed&Breakfast', 0);

/* SMESTAJ */
INSERT INTO accomodation(accomodation_id, accomodation_name, accomodation_type, capacity, category, location_id, entity_version) VALUES (1,'Hotel Centar', 1, 10, 4, 1, 0);
INSERT INTO accomodation(accomodation_id, accomodation_name, accomodation_type, capacity, category, location_id, entity_version) VALUES (2,'Kod Ljubice', 3, 20, 3, 2, 0);
INSERT INTO accomodation(accomodation_id, accomodation_name, accomodation_type, capacity, category, location_id, entity_version) VALUES (3,'Hotel Park', 1, 120, 4, 3, 0);

/* SLIKE 
INSERT INTO picture(picture_id, content, accomodation_id, entity_version) VALUES (1, LOAD_FILE('C:\\Users\\Nikola\\Desktop\\test.png'), 1, 0)*/

/* CENE */
INSERT INTO price(price_id, accomodation_id, start_date, end_date, price, entity_version) VALUES(1, 1, '1514764800000', '1529193600000', 300, 0);
INSERT INTO price(price_id, accomodation_id, start_date, end_date, price, entity_version) VALUES(2, 1, '1529193600000', '1546214400000', 200, 0);

/* REZERVACIJE */
INSERT INTO reservation(reservation_id, accomodation, start_date, end_date, number_of_persons, user_id, entity_version) VALUES(1, 1, '1528588800000', '1529020800000', 3, 1, 0);
INSERT INTO reservation(reservation_id, accomodation, start_date, end_date, number_of_persons, user_id, entity_version) VALUES(2, 2, '1526169600000', '1526601600000', 5, 1, 0);
INSERT INTO reservation(reservation_id, accomodation, start_date, end_date, number_of_persons, user_id, entity_version) VALUES(3, 1, '1524009600000', '1524873600000', 2, 1, 0);


INSERT INTO bonus_service(bonus_service_id, name, entity_version) VALUES (1,'WiFi', 0);
INSERT INTO bonus_service(bonus_service_id, name, entity_version) VALUES (2,'Parking', 0);
INSERT INTO bonus_service(bonus_service_id, name, entity_version) VALUES (3,'Breakfast', 0);
INSERT INTO bonus_service(bonus_service_id, name, entity_version) VALUES (4,'Full board', 0);
INSERT INTO bonus_service(bonus_service_id, name, entity_version) VALUES (5,'Half board', 0);
INSERT INTO bonus_service(bonus_service_id, name, entity_version) VALUES (6,'TV', 0);
INSERT INTO bonus_service(bonus_service_id, name, entity_version) VALUES (7,'Kitchen', 0);
INSERT INTO bonus_service(bonus_service_id, name, entity_version) VALUES (8,'Bathroom', 0);

INSERT INTO accomodation_bonus_services(bonus_service_id, accomodation_id) VALUES (1,1);
INSERT INTO accomodation_bonus_services(bonus_service_id, accomodation_id) VALUES (2,1);
INSERT INTO accomodation_bonus_services(bonus_service_id, accomodation_id) VALUES (6,1);
INSERT INTO accomodation_bonus_services(bonus_service_id, accomodation_id) VALUES (7,1);

/* KOMENTARI */
INSERT INTO comment(comment_id, approved, content, rating, entity_version, accomodation_id, author) VALUES (1, null, 'Nista ne valja.', 1, 1, 1, 2);
INSERT INTO comment(comment_id, approved, content, rating, entity_version, accomodation_id, author) VALUES (2, null, 'Super.', 5, 1, 1, 3);
INSERT INTO comment(comment_id, approved, content, rating, entity_version, accomodation_id, author) VALUES (3, 1, 'Super.', 5, 1, 2, 3);
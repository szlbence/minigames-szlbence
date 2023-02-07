INSERT INTO carts(name, total_price) VALUES ('cart', 0.00);
INSERT INTO userentity(username, password, role, cart_id) VALUES ('Jani', 'password', 'ADMIN', 1);
UPDATE carts SET user_id = 1 WHERE id = 1;
INSERT INTO products(price, name, description, category) VALUES (2000, 'Chocolate Box', 'A tasty selection of the finest chocolates.', 'SWEET');
INSERT INTO products(price, name, description, category) VALUES (3200, 'Wine', 'For the evenings. Or mornings, the choice is yours.', 'ALCOHOL');
INSERT INTO products(price, name, description, category) VALUES (4000, 'Horse Mask', 'A great mask for creating TikTok videos.', 'KID');
INSERT INTO products(price, name, description, category) VALUES (400, 'Doughnut', 'Not only for police officers.', 'SWEET');
INSERT INTO products(price, name, description, category) VALUES (1800, 'SixPack Beer', 'If you are too lazy to have a six pack, on yourself, buy one of these and drink away your sadness.', 'ALCOHOL');
INSERT INTO products(price, name, description, category) VALUES (10000, 'HotWheels Car', 'Buy this if you do not want to spend time with your kid.', 'KID');
INSERT INTO carts(name, total_price) VALUES ('cart', 0.00);
INSERT INTO userentity(username, password, role, cart_id, coin, cpc, multiplier) VALUES ('Jani', '$2a$10$oJ.GBBo9zBsSkoi00atoSO8.j1ICheLGVOKGTaeJZId7kRrfGQZoG', 'ADMIN', 1, 10000, 1, 1);
INSERT INTO userentity(username, password, role, cart_id, coin, cpc, multiplier) VALUES ('Bence', '$2a$10$oJ.GBBo9zBsSkoi00atoSO8.j1ICheLGVOKGTaeJZId7kRrfGQZoG', 'USER', 1, 15000, 1, 1);
INSERT INTO products(price, upgradeprice, cpc, name, description, category) VALUES (2000, 1000, 1, 'Chocolate Box', 'A tasty selection of the finest chocolates.', 'SWEET');
INSERT INTO products(price, upgradeprice, cpc, name, description, category) VALUES (3200, 1600, 10, 'Wine', 'For the evenings. Or mornings, the choice is yours.', 'ALCOHOL');
INSERT INTO products(price, upgradeprice, cpc, name, description, category) VALUES (4000, 2000, 25, 'Horse Mask', 'A great mask for creating TikTok videos.', 'KID');
INSERT INTO products(price, upgradeprice, cpc, name, description, category) VALUES (400, 200, 50, 'Doughnut', 'Not only for police officers.', 'SWEET');
INSERT INTO products(price, upgradeprice, cpc, name, description, category) VALUES (1800, 900, 100, 'SixPack Beer', 'If you are too lazy to have a six pack, on yourself, buy one of these and drink away your sadness.', 'ALCOHOL');
INSERT INTO products(price, upgradeprice, cpc, name, description, category) VALUES (10000, 5000, 2, 'HotWheels Car', 'Buy this if you do not want to spend time with your kid.', 'KID');

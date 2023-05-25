INSERT INTO carts(name, total_price) VALUES ('cart', 0.00);
INSERT INTO userentity(username, password, role, cart_id, coin, cpc, multiplier) VALUES ('Jani', '$2a$10$oJ.GBBo9zBsSkoi00atoSO8.j1ICheLGVOKGTaeJZId7kRrfGQZoG', 'ADMIN', 1, 10000, 1, 1);
INSERT INTO userentity(username, password, role, cart_id, coin, cpc, multiplier) VALUES ('Bence', '$2a$10$oJ.GBBo9zBsSkoi00atoSO8.j1ICheLGVOKGTaeJZId7kRrfGQZoG', 'USER', 1, 0, 1, 1);
INSERT INTO products(price, upgradeprice, cpc, name, description, category) VALUES (4, 2, 1, 'Miner Lamp', 'After darkness comes light!', 'SWEET');
INSERT INTO products(price, upgradeprice, cpc, name, description, category) VALUES (10, 5, 4, 'Pickaxe', 'Why work with your bare hands?!', 'ALCOHOL');
INSERT INTO products(price, upgradeprice, cpc, name, description, category) VALUES (50, 25, 25, 'Truck', 'Ship those coins faster to the buyers!', 'KID');
INSERT INTO products(price, upgradeprice, cpc, name, description, category) VALUES (200, 100, 110, 'Excavator', 'Who needs pickaxes if you have an excavator?', 'SWEET');
INSERT INTO products(price, upgradeprice, cpc, name, description, category) VALUES (1000, 500, 600, 'Smelting Factory', 'Smelting ores have never been faster!', 'ALCOHOL');
INSERT INTO products(price, upgradeprice, cpc, name, description, category) VALUES (10000, 5000, 7500, 'Gold Mine', 'Looks like somebody hit the jackpot!', 'KID');

INSERT INTO employees (active, email, first_name, last_name, login, password, phone, role, salary, salon_id) VALUES
(1, 'director@central.pl', 'Adam', 'Nowak', 'adamn', 'pass123', '600111000', 'DIRECTOR', 15000, (SELECT id FROM salons WHERE name='AutoSalon Central')),
(1, 'manager@krakow.pl', 'Ewa', 'Kowalska', 'ewak', 'pass123', '600222000', 'MANAGER', 9000, (SELECT id FROM salons WHERE name='AutoSalon Krakow')),
(1, 'saler@gdansk.pl', 'Marek', 'Lewandowski', 'marekl', 'pass123', '600333000', 'SALER', 7000, (SELECT id FROM salons WHERE name='AutoSalon Gdansk')),
(1, 'saler2@central.pl', 'Anna', 'Lis', 'annalis', 'pass123', '600444000', 'SALER', 7000, (SELECT id FROM salons WHERE name='AutoSalon Central')),
(1, 'saler3@krakow.pl', 'Tomasz', 'Gorski', 'tomaszg', 'pass123', '600555000', 'SALER', 7000, (SELECT id FROM salons WHERE name='AutoSalon Krakow'));

INSERT INTO employees (active, email, first_name, last_name, login, password, phone, role, salary, salon_id) VALUES
(1, 'director@central.pl', 'Adam', 'Nowak', 'adamn', '$2b$12$f9P9/np74qWsiL794uXjOOQUtIm0r36TeNtXGtH9obHdUDnyYzbvS', '600111000', 'DIRECTOR', 15000, (SELECT id FROM salons WHERE name='AutoSalon Central')),
(1, 'manager@krakow.pl', 'Ewa', 'Kowalska', 'ewak', '$2b$12$BI0mQYZGXRSoPK28ThcwAOG/9KFxb7RKG6LEjSarGzfMlUljfl0AG', '600222000', 'MANAGER', 9000, (SELECT id FROM salons WHERE name='AutoSalon Krakow')),
(1, 'saler@gdansk.pl', 'Marek', 'Lewandowski', 'marekl', '$2b$12$sa//NSTjKONi3r5n/IfAUuSyTMZuyOZDuI.vWh1oCKu9fk9aLxGl6', '600333000', 'SELLER', 7000, (SELECT id FROM salons WHERE name='AutoSalon Gdansk')),
(1, 'saler2@central.pl', 'Anna', 'Lis', 'annalis', '$2b$12$wuIyhjrECilY0JfHZqyNIeKZUGm0Fw6sO9TPWmngc9Qvbv/pjX902', '600444000', 'SELLER', 7000, (SELECT id FROM salons WHERE name='AutoSalon Central')),
(1, 'saler3@krakow.pl', 'Tomasz', 'Gorski', 'tomaszg', '$2b$12$MWp7TnYO0YVhnnL81AK82u0D2jiGI.vE4cM1aS6V6KkGqN5JIJMGC', '600555000', 'SELLER', 7000, (SELECT id FROM salons WHERE name='AutoSalon Krakow'));
-- Employees:
-- adamn      -> pass123
-- ewak       -> pass123
-- marekl     -> pass123
-- annalis    -> pass123
-- tomaszg    -> pass123
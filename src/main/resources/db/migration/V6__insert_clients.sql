INSERT INTO clients (active, company_name, email, first_name, last_name, login, password, phone, tax_number, tax_number_type) VALUES
(1, NULL, 'jan.kowalski@example.com', 'Jan', 'Kowalski', 'jank', '$2b$12$NCySvs2iMHqRT88y.BKqgeJNtA6nUj0lEAa0TXKwMAW5ZUMWi/F/O', '600111222', '1234567890', 'PESEL'),
(1, NULL, 'anna.nowak@example.com', 'Anna', 'Nowak', 'annan', '$2b$12$2MJHrLmbEN1RhnSir6CeFeCtUoPINzH.828UG315Vq3wtIBgqrpKW', '600333444', '0987654321', 'PESEL'),
(1, 'Firma Auto', 'firma@example.com', NULL, NULL, 'firma1', '$2b$12$H27FsYY1Qz6K8lBh0MhGUuNO3yDcA9sr592TUoOh34cRSl2iDwHe2', '600555666', '1122334455', 'NIP'),
(1, NULL, 'pawel.zielinski@example.com', 'Pawel', 'Zielinski', 'pawelz', '$2b$12$E84bSU5nRAaQQynrxp4AhubprnD5XtjnSFcbHoDtktaiJ5EklcLWC', '600777888', '2233445566', 'PESEL'),
(1, 'Transport Sp. z o.o.', 'transport@example.com', NULL, NULL, 'transport1', '$2b$12$pHdqhzdOLOq1SzzmZ4Fcw.Ra5JSaM86F2q6a0ic.ztGOJpPvZiG/a', '600999000', '3344556677', 'NIP'),
(1, NULL, 'martyna.kaczmarek@example.com', 'Martyna', 'Kaczmarek', 'martynak', '$2b$12$nbkxhsT9UlJtZ6GatJi9duod2E/D02TPtrh1Sszywnv07vTMGyTpW', '601111222', '4455667788', 'PESEL'),
(1, NULL, 'tomasz.nowicki@example.com', 'Tomasz', 'Nowicki', 'tomaszn', '$2b$12$JU0TqWIIHZi6OzORLw.ruOnVyQQ3CpNeWbx/8XJ8uxjJP.QPvz/8i', '601222333', '5566778899', 'PESEL'),
(1, 'Firma Transport', 'firma2@example.com', NULL, NULL, 'firma2', '$2b$12$P7NobEADUAbS2gbhI7w2ReKRRKT.D5mc25qcnmHzOK9.US3Amt8bm', '601333444', '6677889900', 'NIP'),
(1, NULL, 'joanna.wisniewska@example.com', 'Joanna', 'Wisniewska', 'joannaw', '$2b$12$ATEESEByHAVbHGKr0JnKluxMcjIE.t/P5upEJ0d4ZnUkgVjNc2GnS', '601444555', '7788990011', 'PESEL'),
(1, NULL, 'krzysztof.maj@example.com', 'Krzysztof', 'Maj', 'krzysztofM', '$2b$12$kO3P3W3WZFQYVwFl1982Ue8N2fZ4BEbZetwxtLIfaPNBCK06S8zw2', '601555666', '8899001122', 'PESEL');

-- Clients:
-- jank       -> pass123
-- annan      -> pass123
-- firma1     -> pass123
-- pawelz     -> pass123
-- transport1 -> pass123
-- martynak   -> pass123
-- tomasztn   -> pass123
-- firma2     -> pass123
-- joannaw    -> pass123
-- krzysztofM -> pass123
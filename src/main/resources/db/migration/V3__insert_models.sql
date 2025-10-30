INSERT INTO models (generation, name, year_from, year_to, brand_id) VALUES
('F30', '3 Series', 2012, 2019, (SELECT id FROM brands WHERE name = 'BMW')),
('G20', '3 Series', 2019, 2025, (SELECT id FROM brands WHERE name = 'BMW')),
('G05', 'X5', 2018, 2025, (SELECT id FROM brands WHERE name = 'BMW'));

-- Mercedes-Benz
INSERT INTO models (generation, name, year_from, year_to, brand_id) VALUES
('W205', 'C-Class', 2014, 2021, (SELECT id FROM brands WHERE name = 'Mercedes-Benz')),
('W213', 'E-Class', 2016, 2025, (SELECT id FROM brands WHERE name = 'Mercedes-Benz')),
('X253', 'GLC', 2015, 2025, (SELECT id FROM brands WHERE name = 'Mercedes-Benz'));

-- Audi
INSERT INTO models (generation, name, year_from, year_to, brand_id) VALUES
('B9', 'A4', 2016, 2023, (SELECT id FROM brands WHERE name = 'Audi')),
('C8', 'A6', 2018, 2025, (SELECT id FROM brands WHERE name = 'Audi')),
('F5', 'Q7', 2015, 2025, (SELECT id FROM brands WHERE name = 'Audi'));

-- Volkswagen
INSERT INTO models (generation, name, year_from, year_to, brand_id) VALUES
('MK7', 'Golf', 2012, 2019, (SELECT id FROM brands WHERE name = 'Volkswagen')),
('MK8', 'Golf', 2019, 2025, (SELECT id FROM brands WHERE name = 'Volkswagen')),
('Tiguan II', 'Tiguan', 2016, 2025, (SELECT id FROM brands WHERE name = 'Volkswagen'));

-- Toyota
INSERT INTO models (generation, name, year_from, year_to, brand_id) VALUES
('E140', 'Corolla', 2006, 2013, (SELECT id FROM brands WHERE name = 'Toyota')),
('E170', 'Corolla', 2013, 2018, (SELECT id FROM brands WHERE name = 'Toyota')),
('E210', 'Corolla', 2019, 2025, (SELECT id FROM brands WHERE name = 'Toyota')),
('XZ10', 'RAV4', 2019, 2025, (SELECT id FROM brands WHERE name = 'Toyota'));

-- Honda
INSERT INTO models (generation, name, year_from, year_to, brand_id) VALUES
('10th', 'Civic', 2016, 2025, (SELECT id FROM brands WHERE name = 'Honda')),
('5th', 'CR-V', 2012, 2016, (SELECT id FROM brands WHERE name = 'Honda'));

-- Nissan
INSERT INTO models (generation, name, year_from, year_to, brand_id) VALUES
('L33', 'Altima', 2013, 2018, (SELECT id FROM brands WHERE name = 'Nissan')),
('D40', 'Navara', 2005, 2015, (SELECT id FROM brands WHERE name = 'Nissan'));

-- Mazda
INSERT INTO models (generation, name, year_from, year_to, brand_id) VALUES
('ND', 'MX-5', 2015, 2025, (SELECT id FROM brands WHERE name = 'Mazda')),
('CX-5', 'CX-5', 2012, 2025, (SELECT id FROM brands WHERE name = 'Mazda'));

-- Lexus
INSERT INTO models (generation, name, year_from, year_to, brand_id) VALUES
('XE30', 'ES', 2018, 2025, (SELECT id FROM brands WHERE name = 'Lexus')),
('XZ10', 'RX', 2015, 2025, (SELECT id FROM brands WHERE name = 'Lexus'));

-- Subaru
INSERT INTO models (generation, name, year_from, year_to, brand_id) VALUES
('5th', 'Impreza', 2016, 2025, (SELECT id FROM brands WHERE name = 'Subaru')),
('5th', 'Forester', 2018, 2025, (SELECT id FROM brands WHERE name = 'Subaru'));

-- Hyundai
INSERT INTO models (generation, name, year_from, year_to, brand_id) VALUES
('CN7', 'Elantra', 2020, 2025, (SELECT id FROM brands WHERE name = 'Hyundai')),
('NX4', 'Tucson', 2021, 2025, (SELECT id FROM brands WHERE name = 'Hyundai'));

-- Kia
INSERT INTO models (generation, name, year_from, year_to, brand_id) VALUES
('CE', 'Cerato', 2018, 2025, (SELECT id FROM brands WHERE name = 'Kia')),
('NX2', 'Sportage', 2021, 2025, (SELECT id FROM brands WHERE name = 'Kia'));

-- Tesla
INSERT INTO models (generation, name, year_from, year_to, brand_id) VALUES
('1st', 'Model S', 2012, 2025, (SELECT id FROM brands WHERE name = 'Tesla')),
('1st', 'Model 3', 2017, 2025, (SELECT id FROM brands WHERE name = 'Tesla')),
('1st', 'Model X', 2015, 2025, (SELECT id FROM brands WHERE name = 'Tesla')),
('1st', 'Model Y', 2020, 2025, (SELECT id FROM brands WHERE name = 'Tesla'));
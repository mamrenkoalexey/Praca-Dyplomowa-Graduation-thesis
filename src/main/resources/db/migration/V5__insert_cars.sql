INSERT INTO cars
(body_type, color, description, fuel_type, mileage, price, production_year, registration_number, status, vin, model_id, salon_id)
VALUES
('SEDAN', 'Black', 'Świetny stan, jeden właściciel. Samochód bardzo zadbany, bezwypadkowy. Idealny do codziennej jazdy po mieście.', 'PETROL', 45000, 35000, 2019, 'WAW1001', 'AVAILABLE', 'VIN100001',
  (SELECT id FROM models WHERE name='3 Series' AND generation='G20' AND brand_id=(SELECT id FROM brands WHERE name='BMW')),
  (SELECT id FROM salons WHERE name='AutoSalon Central' AND city='Warsaw')
),
('SUV', 'White', 'Rodzinny samochód, przestronne wnętrze i duży bagażnik. Doskonały na długie podróże.', 'DIESEL', 60000, 45000, 2018, 'WAW1002', 'AVAILABLE', 'VIN100002',
  (SELECT id FROM models WHERE name='X5' AND generation='G05' AND brand_id=(SELECT id FROM brands WHERE name='BMW')),
  (SELECT id FROM salons WHERE name='AutoSalon Krakow' AND city='Krakow')
),
('HATCHBACK', 'Red', 'Sportowy i szybki. Idealny dla młodych kierowców i fanów dynamicznej jazdy.', 'PETROL', 30000, 28000, 2020, 'WAW1003', 'AVAILABLE', 'VIN100003',
  (SELECT id FROM models WHERE name='Golf' AND generation='MK8' AND brand_id=(SELECT id FROM brands WHERE name='Volkswagen')),
  (SELECT id FROM salons WHERE name='AutoSalon Gdansk' AND city='Gdansk')
),
('SEDAN', 'Blue', 'Komfortowy samochód z niskim przebiegiem. Doskonały do codziennego użytkowania w mieście i na trasie.', 'HYBRID', 20000, 37000, 2021, 'WAW1004', 'AVAILABLE', 'VIN100004',
  (SELECT id FROM models WHERE name='C-Class' AND generation='W205' AND brand_id=(SELECT id FROM brands WHERE name='Mercedes-Benz')),
  (SELECT id FROM salons WHERE name='AutoSalon Central' AND city='Warsaw')
),
('SUV', 'Grey', 'Idealny SUV dla całej rodziny. Wygodny, przestronny i bezpieczny.', 'PETROL', 50000, 42000, 2019, 'WAW1005', 'AVAILABLE', 'VIN100005',
  (SELECT id FROM models WHERE name='GLC' AND generation='X253' AND brand_id=(SELECT id FROM brands WHERE name='Mercedes-Benz')),
  (SELECT id FROM salons WHERE name='AutoSalon Krakow' AND city='Krakow')
),
('SEDAN', 'White', 'Luksusowy sedan, bardzo zadbany. Wnętrze wykonane z wysokiej jakości materiałów.', 'PETROL', 35000, 55000, 2018, 'WAW1006', 'AVAILABLE', 'VIN100006',
  (SELECT id FROM models WHERE name='E-Class' AND generation='W213' AND brand_id=(SELECT id FROM brands WHERE name='Mercedes-Benz')),
  (SELECT id FROM salons WHERE name='AutoSalon Gdansk' AND city='Gdansk')
),
('SUV', 'Black', 'Sportowy SUV w doskonałym stanie. Dynamiczny silnik i wygodne wnętrze.', 'DIESEL', 40000, 48000, 2020, 'WAW1007', 'AVAILABLE', 'VIN100007',
  (SELECT id FROM models WHERE name='Q7' AND generation='F5' AND brand_id=(SELECT id FROM brands WHERE name='Audi')),
  (SELECT id FROM salons WHERE name='AutoSalon Central' AND city='Warsaw')
),
('SEDAN', 'Silver', 'Niezawodny samochód, czysty i zadbany. Idealny do codziennej jazdy.', 'PETROL', 30000, 31000, 2019, 'WAW1008', 'AVAILABLE', 'VIN100008',
  (SELECT id FROM models WHERE name='A4' AND generation='B9' AND brand_id=(SELECT id FROM brands WHERE name='Audi')),
  (SELECT id FROM salons WHERE name='AutoSalon Krakow' AND city='Krakow')
),
('SEDAN', 'Black', 'Luksusowy i komfortowy sedan. Świetnie nadaje się na długie podróże.', 'PETROL', 25000, 60000, 2021, 'WAW1009', 'AVAILABLE', 'VIN100009',
  (SELECT id FROM models WHERE name='A6' AND generation='C8' AND brand_id=(SELECT id FROM brands WHERE name='Audi')),
  (SELECT id FROM salons WHERE name='AutoSalon Gdansk' AND city='Gdansk')
),
('HATCHBACK', 'Blue', 'Kompaktowy i szybki hatchback. Świetny do jazdy miejskiej.', 'PETROL', 15000, 22000, 2020, 'WAW1010', 'AVAILABLE', 'VIN100010',
  (SELECT id FROM models WHERE name='Golf' AND generation='MK7' AND brand_id=(SELECT id FROM brands WHERE name='Volkswagen')),
  (SELECT id FROM salons WHERE name='AutoSalon Central' AND city='Warsaw')
),
('SUV', 'White', 'Rodzinny SUV w doskonałym stanie. Duża przestrzeń i wysoki komfort jazdy.', 'DIESEL', 50000, 43000, 2017, 'WAW1011', 'AVAILABLE', 'VIN100011',
  (SELECT id FROM models WHERE name='Tiguan' AND generation='Tiguan II' AND brand_id=(SELECT id FROM brands WHERE name='Volkswagen')),
  (SELECT id FROM salons WHERE name='AutoSalon Krakow' AND city='Krakow')
),
('SEDAN', 'Red', 'Niezawodny samochód codzienny. Oszczędny i komfortowy.', 'PETROL', 40000, 30000, 2016, 'WAW1012', 'AVAILABLE', 'VIN100012',
  (SELECT id FROM models WHERE name='Corolla' AND generation='E210' AND brand_id=(SELECT id FROM brands WHERE name='Toyota')),
  (SELECT id FROM salons WHERE name='AutoSalon Gdansk' AND city='Gdansk')
),
('SUV', 'Grey', 'Kompaktowy SUV. Idealny do miasta i na trasę.', 'HYBRID', 20000, 35000, 2019, 'WAW1013', 'AVAILABLE', 'VIN100013',
  (SELECT id FROM models WHERE name='RAV4' AND generation='XZ10' AND brand_id=(SELECT id FROM brands WHERE name='Toyota')),
  (SELECT id FROM salons WHERE name='AutoSalon Central' AND city='Warsaw')
),
('SEDAN', 'Black', 'Sportowy hatchback. Dynamiczny silnik i wygodna kabina.', 'PETROL', 30000, 28000, 2018, 'WAW1014', 'AVAILABLE', 'VIN100014',
  (SELECT id FROM models WHERE name='Civic' AND generation='10th' AND brand_id=(SELECT id FROM brands WHERE name='Honda')),
  (SELECT id FROM salons WHERE name='AutoSalon Krakow' AND city='Krakow')
),
('SUV', 'Blue', 'Niezawodny SUV. Przestronny i komfortowy.', 'PETROL', 40000, 36000, 2017, 'WAW1015', 'AVAILABLE', 'VIN100015',
  (SELECT id FROM models WHERE name='CR-V' AND generation='5th' AND brand_id=(SELECT id FROM brands WHERE name='Honda')),
  (SELECT id FROM salons WHERE name='AutoSalon Gdansk' AND city='Gdansk')
),
('SEDAN', 'White', 'Komfortowy samochód rodzinny. Sprawdzony i niezawodny.', 'PETROL', 35000, 33000, 2018, 'WAW1016', 'AVAILABLE', 'VIN100016',
  (SELECT id FROM models WHERE name='Altima' AND generation='L33' AND brand_id=(SELECT id FROM brands WHERE name='Nissan')),
  (SELECT id FROM salons WHERE name='AutoSalon Central' AND city='Warsaw')
),
('PICKUP', 'Black', 'Samochód użytkowy, idealny do pracy. Duży ładunek i wytrzymałość.', 'DIESEL', 60000, 40000, 2015, 'WAW1017', 'AVAILABLE', 'VIN100017',
  (SELECT id FROM models WHERE name='Navara' AND generation='D40' AND brand_id=(SELECT id FROM brands WHERE name='Nissan')),
  (SELECT id FROM salons WHERE name='AutoSalon Krakow' AND city='Krakow')
),
('CONVERTIBLE', 'Red', 'Przyjemny w prowadzeniu kabriolet. Doskonały na weekendowe wyjazdy.', 'PETROL', 20000, 50000, 2020, 'WAW1018', 'AVAILABLE', 'VIN100018',
  (SELECT id FROM models WHERE name='MX-5' AND generation='ND' AND brand_id=(SELECT id FROM brands WHERE name='Mazda')),
  (SELECT id FROM salons WHERE name='AutoSalon Gdansk' AND city='Gdansk')
),
('SUV', 'Grey', 'Niezawodny SUV. Komfortowy i oszczędny w eksploatacji.', 'PETROL', 30000, 37000, 2019, 'WAW1019', 'AVAILABLE', 'VIN100019',
  (SELECT id FROM models WHERE name='CX-5' AND generation='CX-5' AND brand_id=(SELECT id FROM brands WHERE name='Mazda')),
  (SELECT id FROM salons WHERE name='AutoSalon Central' AND city='Warsaw')
),
('SEDAN', 'Black', 'Luksusowy sedan. Doskonały na codzienne i długie trasy.', 'PETROL', 15000, 55000, 2021, 'WAW1020', 'AVAILABLE', 'VIN100020',
  (SELECT id FROM models WHERE name='ES' AND generation='XE30' AND brand_id=(SELECT id FROM brands WHERE name='Lexus')),
  (SELECT id FROM salons WHERE name='AutoSalon Krakow' AND city='Krakow')
),
('SUV', 'White', 'Komfortowy SUV rodzinny. Przestronny i bezpieczny.', 'PETROL', 25000, 42000, 2020, 'WAW1021', 'AVAILABLE', 'VIN100021',
  (SELECT id FROM models WHERE name='RX' AND generation='XZ10' AND brand_id=(SELECT id FROM brands WHERE name='Lexus')),
  (SELECT id FROM salons WHERE name='AutoSalon Gdansk' AND city='Gdansk')
),
('SEDAN', 'Blue', 'Niezawodny samochód codzienny. Oszczędny i praktyczny.', 'PETROL', 30000, 28000, 2018, 'WAW1022', 'AVAILABLE', 'VIN100022',
  (SELECT id FROM models WHERE name='Impreza' AND generation='5th' AND brand_id=(SELECT id FROM brands WHERE name='Subaru')),
  (SELECT id FROM salons WHERE name='AutoSalon Central' AND city='Warsaw')
),
('SUV', 'Red', 'Rodzinny SUV. Przestronny, wygodny i bezpieczny.', 'PETROL', 35000, 36000, 2019, 'WAW1023', 'AVAILABLE', 'VIN100023',
  (SELECT id FROM models WHERE name='Forester' AND generation='5th' AND brand_id=(SELECT id FROM brands WHERE name='Subaru')),
  (SELECT id FROM salons WHERE name='AutoSalon Krakow' AND city='Krakow')
),
('SEDAN', 'White', 'Kompaktowy sedan. Idealny do miasta i na krótkie trasy.', 'PETROL', 40000, 32000, 2017, 'WAW1024', 'AVAILABLE', 'VIN100024',
  (SELECT id FROM models WHERE name='Elantra' AND generation='CN7' AND brand_id=(SELECT id FROM brands WHERE name='Hyundai')),
  (SELECT id FROM salons WHERE name='AutoSalon Gdansk' AND city='Gdansk')
);

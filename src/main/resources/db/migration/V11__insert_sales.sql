INSERT INTO sales (notes, payment_method, sale_date, total_amount, car_id, client_id, employee_id) VALUES
('Sprzedaż SUV', 'TRANSFER', '2023-06-15', 55000, (SELECT id FROM cars WHERE vin='VIN100002'), (SELECT id FROM clients WHERE email='anna.nowak@example.com'), (SELECT id FROM employees WHERE login='ewak')),
('Sprzedaż hatchback', 'CASH', '2023-07-10', 28000, (SELECT id FROM cars WHERE vin='VIN100003'), (SELECT id FROM clients WHERE email='pawel.zielinski@example.com'), (SELECT id FROM employees WHERE login='marekl'));

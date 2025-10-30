INSERT INTO payments (active, amount, deu_date, payment_date, method, status, lease_id) VALUES
(1, 17000, '2023-01-05', '2023-01-05', 'TRANSFER', 'COMPLETED', (SELECT id FROM leases WHERE contract_number='LEASE1001'));

INSERT INTO payments (active, amount, deu_date, payment_date, method, status, rent_id) VALUES
(1, 700, '2023-05-01', '2023-05-01', 'CASH', 'COMPLETED', (SELECT id FROM rents WHERE rent_number='RENT1001'));

INSERT INTO payments (active, amount, deu_date, payment_date, method, status, sale_id) VALUES
(1, 55000, '2023-03-15', '2023-03-15', 'TRANSFER', 'COMPLETED', (SELECT id FROM sales WHERE car_id=(SELECT id FROM cars WHERE vin='VIN100002')));

INSERT INTO payments (active, amount, deu_date, payment_date, method, status, invoice_id) VALUES
(1, 55000, '2023-03-15', '2023-03-15', 'TRANSFER', 'COMPLETED', (SELECT id FROM invoices WHERE invoice_number='INV1001'));
INSERT INTO invoices (invoice_date, invoice_name, invoice_number, invoice_tax_number, total_amount, client_id) VALUES
('2023-06-15', 'Invoice 1001', 'INV1001', '123456789', 55000, (SELECT id FROM clients WHERE email='anna.nowak@example.com'));

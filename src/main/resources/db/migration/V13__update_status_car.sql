
UPDATE cars c
JOIN sales s ON c.id = s.car_id
SET c.status = 'SOLD';


UPDATE cars c
JOIN leases l ON c.id = l.car_id
SET c.status = 'RESERVED';


UPDATE cars c
JOIN rents r ON c.id = r.car_id
SET c.status = 'IN_SERVICE';


UPDATE cars
SET status = 'AVAILABLE'
WHERE status NOT IN ('SOLD', 'RESERVED', 'IN_SERVICE');
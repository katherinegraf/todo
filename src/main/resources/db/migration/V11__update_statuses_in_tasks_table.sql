UPDATE tasks
SET status = 'completed'
WHERE id > 700;

UPDATE tasks
SET status = 'cancelled'
WHERE status IS NULL;
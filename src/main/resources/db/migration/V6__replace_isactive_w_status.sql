ALTER TABLE tasks
ADD COLUMN status text;
UPDATE tasks
SET status = (CASE is_active
    when true then 'incomplete'
    when false then 'complete'
    END);
ALTER TABLE tasks
DROP COLUMN is_active;
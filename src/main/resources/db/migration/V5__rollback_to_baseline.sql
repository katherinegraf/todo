ALTER TABLE tasks
ADD COLUMN is_active boolean;
UPDATE tasks
SET is_active = (CASE status
    when 'incomplete' then true
    when 'complete' then false
    END);
ALTER TABLE tasks
DROP COLUMN status;
UPDATE tasks
SET status = (CASE status
    WHEN 'incomplete' THEN 'active'
    END);
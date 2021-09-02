UPDATE tasks
SET status = (CASE is_active
    when 'true' then 'incomplete'
    when 'false' then 'complete'
    END);
UPDATE tasks
SET status (CASE WHEN 'incomplete' then 'active');
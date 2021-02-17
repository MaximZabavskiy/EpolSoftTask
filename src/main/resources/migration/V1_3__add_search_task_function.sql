drop function if exists smart_task_search;

create function smart_task_search(
    status bigint, startDate date, endDate date)
    RETURNS setof m_tasks
AS
$$

select t.* from m_tasks t
where t.status = $1 and t.start_date >= $2 and t.end_date <= $3
limit 5;

$$ language SQL;


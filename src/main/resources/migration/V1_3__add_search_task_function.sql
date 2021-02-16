drop function if exists smart_task_search;

create function smart_task_search(
    name varchar(100), status bigint, startDate date, endDate date)
    RETURNS setof m_tasks
AS
$$

select t.* from m_tasks t
where t.name like '%$1%' and t.status = $2 and t.start_date >= $3 and t.end_date <= $4
limit 1;

$$ language SQL;


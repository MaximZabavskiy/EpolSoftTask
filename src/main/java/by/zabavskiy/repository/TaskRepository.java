package by.zabavskiy.repository;

import by.zabavskiy.domain.Task;

import java.sql.Date;
import java.util.List;

public interface TaskRepository {
   List<Task> searchByParamsCriteriaApi(String name, Long status, Date startDate, Date endDate);
}

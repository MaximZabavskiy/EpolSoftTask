package by.zabavskiy.service;

import by.zabavskiy.domain.Task;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface TaskService {

  List<Task> findAll();

  Task save(Task task);

  Task update(Task task);

  void deleteById(Long id);

  Optional<Task> findById(Long taskId);

  List<Task> searchByParamsFunctionCall(Long status, Date startDate, Date endDate);

  List<Task> searchByParamsHQL(Long status, Date startDate, Date endDate);

  List<Task> searchByParamNameCriteriaApi(String name);

  List<Task> searchByParamStatus(Long status);

  List<Task> searchByParamStartDate(Date startDate);

  List<Task> searchByParamEndDate(Date endDate);

  List<Task> searchByParamsStartAndEndDates(Date startDate, Date endDate);
}

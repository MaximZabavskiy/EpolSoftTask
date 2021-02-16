package by.zabavskiy.service;

import by.zabavskiy.domain.Task;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface TaskService {

  List<Task> findAll();

  Task save(Task user);

  Task update(Task user);

  void deleteById(Long id);

  Optional<Task> findById(Long taskId);

  //    List<Task> searchByParams(String name, Long status, Date startDate, Date endDate);

  List<Task> searchByParamsCriteriaApi(String name, Long status, Date startDate, Date endDate);
}

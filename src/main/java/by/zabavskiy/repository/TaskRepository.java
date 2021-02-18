package by.zabavskiy.repository;

import by.zabavskiy.domain.Task;

import java.util.List;

public interface TaskRepository {

   List<Task> searchByParamNameCriteriaApi(String name);
}

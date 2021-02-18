package by.zabavskiy.service.impl;

import by.zabavskiy.domain.Task;
import by.zabavskiy.repository.TaskRepository;
import by.zabavskiy.repository.impl.TaskSpringDataRepository;
import by.zabavskiy.service.TaskService;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskSpringDataRepository taskSpringDataRepository;
    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskSpringDataRepository taskSpringDataRepository, TaskRepository taskRepository) {
        this.taskSpringDataRepository = taskSpringDataRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> findAll() {
        return taskSpringDataRepository.findAll();
    }

    @Override
    public Task save(Task task) {
        return taskSpringDataRepository.save(task);
    }

    @Override
    public Task update(Task task) {
        return taskSpringDataRepository.save(task);
    }

    @Override
    public void deleteById(Long id) {
        taskSpringDataRepository.deleteById(id);
    }

    @Override
    public Optional<Task> findById(Long taskId) {
        return taskSpringDataRepository.findById(taskId);
    }


    @Override
    public List<Task> searchByParamNameCriteriaApi(String name) {
        return taskRepository.searchByParamNameCriteriaApi(name);
    }


    @Override
    public List<Task> searchByParamsHQL(Long status, Date startDate, Date endDate) {
        return taskSpringDataRepository.searchByParamsHQL(status, startDate, endDate);
    }


    @Override
    public List<Task> searchByParamsFunctionCall(Long status, Date startDate, Date endDate) {
        return taskSpringDataRepository.searchByParamsFunctionCall(status, startDate, endDate);
    }


    @Override
    public List<Task> searchByParamStatus(Long status) {
        return taskSpringDataRepository.searchByParamStatus(status);
    }


    @Override
    public List<Task> searchByParamStartDate(Date startDate) {
        return taskSpringDataRepository.searchByParamStartDate(startDate);
    }


    @Override
    public List<Task> searchByParamEndDate(Date endDate) {
        return taskSpringDataRepository.searchByParamEndDate(endDate);
    }


    @Override
    public List<Task> searchByParamsStartAndEndDates(Date startDate, Date endDate) {
        return taskSpringDataRepository.searchByParamsStartAndEndDates(startDate, endDate);
    }
}


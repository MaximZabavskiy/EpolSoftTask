package by.zabavskiy.controller.convert.task;

import by.zabavskiy.controller.request.TaskEditRequest;
import by.zabavskiy.domain.Task;
import by.zabavskiy.exception.EntityNotFoundException;
import by.zabavskiy.repository.impl.TaskSpringDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskEditRequestConverter extends TaskEntityConverter<TaskEditRequest, Task> {

    @Autowired
    private TaskSpringDataRepository taskSpringDataRepository;

    @Override
    public Task convert(TaskEditRequest request) {

        Task task = taskSpringDataRepository.findById(request.getId()).orElseThrow(EntityNotFoundException::new);
        return doConvert(task, request);

    }
}
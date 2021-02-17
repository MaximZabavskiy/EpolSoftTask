package by.zabavskiy.controller.convert.task;

import by.zabavskiy.controller.request.TaskCreateRequest;
import by.zabavskiy.domain.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;

@RequiredArgsConstructor
public abstract class TaskEntityConverter<S, T> implements Converter<S, T> {


    protected Task doConvert(Task task, TaskCreateRequest request) {

        task.setName(request.getName());
        task.setDescription(request.getDescription());
        task.setStartDate(request.getStartDate());
        task.setEndDate(request.getEndDate());
        task.setStatus(request.getStatus());

        return task;
    }
}

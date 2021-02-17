package by.zabavskiy.controller.convert.task;

import by.zabavskiy.controller.request.TaskCreateRequest;
import by.zabavskiy.domain.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskCreateRequestConverter extends TaskEntityConverter<TaskCreateRequest, Task> {

  @Override
  public Task convert(TaskCreateRequest request) {

    Task task = new Task();

    return doConvert(task, request);
  }
}

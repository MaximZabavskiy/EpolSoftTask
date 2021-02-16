package by.zabavskiy.controller.convert.element;

import by.zabavskiy.controller.request.ElementCreateRequest;
import by.zabavskiy.domain.Element;
import by.zabavskiy.domain.Task;
import by.zabavskiy.exception.EntityNotFoundException;
import by.zabavskiy.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;

@RequiredArgsConstructor
public abstract class ElementEntityConverter<S, T> implements Converter<S, T> {

  private final TaskService taskService;


  protected Element doConvert(Element element, ElementCreateRequest request) {

    Task task = taskService.findById(request.getTaskId()).orElseThrow(EntityNotFoundException::new);

    element.setName(request.getName());
    element.setDescription(request.getDescription());
    element.setValue(request.getValue());

    element.setTask(task);
    return element;
  }
}

package by.zabavskiy.controller.convert.element;

import by.zabavskiy.controller.request.ElementCreateRequest;
import by.zabavskiy.domain.Element;
import by.zabavskiy.service.TaskService;
import org.springframework.stereotype.Component;

@Component
public class ElementCreateRequestConverter extends ElementEntityConverter<ElementCreateRequest, Element> {

    public ElementCreateRequestConverter(TaskService taskService) {
        super(taskService);
    }

    @Override
    public Element convert(ElementCreateRequest request) {

        Element element = new Element();

        return doConvert(element, request);
    }
}

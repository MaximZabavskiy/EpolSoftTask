package by.zabavskiy.controller.convert.element;

import by.zabavskiy.controller.request.ElementEditRequest;
import by.zabavskiy.domain.Element;
import by.zabavskiy.exception.EntityNotFoundException;
import by.zabavskiy.repository.impl.ElementSpringDataRepository;
import by.zabavskiy.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ElementEditRequestConverter extends ElementEntityConverter<ElementEditRequest, Element> {

    @Autowired
    private ElementSpringDataRepository elementSpringDataRepository;

    public ElementEditRequestConverter(TaskService taskService) {
        super(taskService);
    }

    @Override
    public Element convert(ElementEditRequest request) {

        Element element = elementSpringDataRepository.findById(request.getId()).orElseThrow(EntityNotFoundException::new);
        return doConvert(element, request);

    }
}
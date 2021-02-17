package by.zabavskiy.service.impl;

import by.zabavskiy.domain.Element;
import by.zabavskiy.domain.Task;
import by.zabavskiy.repository.ElementRepository;
import by.zabavskiy.repository.TaskRepository;
import by.zabavskiy.repository.impl.ElementSpringDataRepository;
import by.zabavskiy.repository.impl.TaskSpringDataRepository;
import by.zabavskiy.service.ElementService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElementServiceImpl implements ElementService {

    private final ElementSpringDataRepository elementSpringDataRepository;
    private final ElementRepository elementRepository;

    public ElementServiceImpl(ElementSpringDataRepository elementSpringDataRepository, ElementRepository elementRepository) {
        this.elementSpringDataRepository = elementSpringDataRepository;
        this.elementRepository = elementRepository;
    }

    @Override
    public List<Element> findAll() {
        return elementSpringDataRepository.findAll();
    }

    @Override
    public Element save(Element element) {
        return elementSpringDataRepository.save(element);
    }

    @Override
    public Element update(Element element) {
        return elementSpringDataRepository.save(element);
    }

    @Override
    public void deleteElementById(Long id) {
        elementSpringDataRepository.deleteElementById(id);
    }

    @Override
    public List<Element> searchByParamValueCriteriaApi(String value){
        return elementRepository.searchByParamValueCriteriaApi(value);
    }

    @Override
    public List<Element> searchByParamTaskId(Long id){
        return elementSpringDataRepository.searchByParamTaskId(id);
    }

}

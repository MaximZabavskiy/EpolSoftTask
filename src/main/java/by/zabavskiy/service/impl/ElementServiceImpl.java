package by.zabavskiy.service.impl;

import by.zabavskiy.domain.Element;
import by.zabavskiy.domain.Task;
import by.zabavskiy.repository.impl.ElementSpringDataRepository;
import by.zabavskiy.repository.impl.TaskSpringDataRepository;
import by.zabavskiy.service.ElementService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElementServiceImpl implements ElementService {

    private ElementSpringDataRepository elementSpringDataRepository;

    public ElementServiceImpl(ElementSpringDataRepository elementSpringDataRepository) {
        this.elementSpringDataRepository = elementSpringDataRepository;
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
    public void deleteById(Long id) {
        elementSpringDataRepository.deleteById(id);
    }

}

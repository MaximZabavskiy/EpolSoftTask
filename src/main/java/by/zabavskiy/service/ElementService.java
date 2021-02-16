package by.zabavskiy.service;

import by.zabavskiy.domain.Element;
import by.zabavskiy.domain.Task;

import java.util.List;

public interface ElementService {

    List<Element> findAll();

    Element save(Element user);

    Element update(Element user);

    void deleteById(Long id);
}

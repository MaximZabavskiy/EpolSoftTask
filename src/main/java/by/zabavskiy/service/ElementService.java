package by.zabavskiy.service;

import by.zabavskiy.domain.Element;

import java.util.List;

public interface ElementService {

    List<Element> findAll();

    Element save(Element element);

    Element update(Element element);

    void deleteElementById(Long id);

    List<Element> searchByParamValueCriteriaApi(String value);

    List<Element> searchByParamTaskId(Long taskId);
}

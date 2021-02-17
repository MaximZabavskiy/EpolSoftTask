package by.zabavskiy.repository;

import by.zabavskiy.domain.Element;

import java.util.List;

public interface ElementRepository {

    List<Element> searchByParamValueCriteriaApi(String value);
}

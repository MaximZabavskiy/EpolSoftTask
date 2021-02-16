package by.zabavskiy.repository.impl;

import by.zabavskiy.domain.Element;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElementSpringDataRepository extends JpaRepository<Element, Long> {
}

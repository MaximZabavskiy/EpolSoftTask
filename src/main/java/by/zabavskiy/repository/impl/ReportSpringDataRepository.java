package by.zabavskiy.repository.impl;

import by.zabavskiy.domain.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportSpringDataRepository extends JpaRepository<Report, Long> {
}

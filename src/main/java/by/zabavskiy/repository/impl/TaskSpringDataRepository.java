package by.zabavskiy.repository.impl;

import by.zabavskiy.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface TaskSpringDataRepository extends JpaRepository<Task, Long> {

    @Query(
            value = "select * from smart_task_search(:name, :status, :startDate, :endDate)",
            nativeQuery = true)
    List<Task> searchWithFunctionCall(
            @Param("name") String name,
            @Param("status") Long status,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate);
}

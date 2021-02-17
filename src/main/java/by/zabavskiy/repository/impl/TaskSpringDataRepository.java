package by.zabavskiy.repository.impl;

import by.zabavskiy.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface TaskSpringDataRepository extends JpaRepository<Task, Long> {

    @Query(value = "select * from smart_task_search(:status, :startDate, :endDate)", nativeQuery = true)
    List<Task> searchByParamsFunctionCall(
            @Param("status") Long status,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate);


    @Query(value = "select t from Task t where t.status = :status or t.startDate >= :startDate or t.endDate <= :endDate")
    List<Task> searchByParamsHQL(Long status, Date startDate, Date endDate);


    @Query(value = "select t from Task t where t.status = :status")
    List<Task> searchByParamStatus(Long status);


    @Query(value = "select t from Task t where t.startDate >= :startDate")
    List<Task> searchByParamStartDate(Date startDate);


    @Query(value = "select t from Task t where t.endDate <= :endDate")
    List<Task> searchByParamEndDate(Date endDate);
}

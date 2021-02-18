package by.zabavskiy.repository.impl;

import by.zabavskiy.domain.Element;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

public interface ElementSpringDataRepository extends JpaRepository<Element, Long> {

    @Query(value = "select * from m_elements e join m_tasks t on e.task_id = t.id where t.id =:id", nativeQuery=true)
    List<Element> searchByParamTaskId(@Param("id") Long taskId);


    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = SQLException.class)
    @Modifying
    @Query(value = "delete from m_elements where id = :id", nativeQuery = true)
    int deleteElementById(@Param("id") Long id);
}

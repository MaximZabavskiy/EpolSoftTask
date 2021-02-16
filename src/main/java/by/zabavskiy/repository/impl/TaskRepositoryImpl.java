package by.zabavskiy.repository.impl;

import by.zabavskiy.domain.Task;
import by.zabavskiy.domain.Task_;
import by.zabavskiy.repository.TaskRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.Date;
import java.util.List;

@Repository
public class TaskRepositoryImpl implements TaskRepository {

    @PersistenceContext//need to create?
    private EntityManager entityManager;

    @Override
    public List<Task> searchByParamsCriteriaApi(String name, Long status, java.sql.Date startDate, java.sql.Date endDate) {


    //1. Get Builder for Criteria object
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<Task> query = cb.createQuery(Task.class); //here select, where, orderBy, having
    Root<Task> root = query.from(Task.class); //here params  select * from m_users -> mapping


        /*type of future params in prepared statement*/

    ParameterExpression<String> nameSearchParam = cb.parameter(String.class);
    ParameterExpression<Long> statusSearchParam = cb.parameter(Long.class);
    ParameterExpression<Date> startDateSearchParam = cb.parameter(Date.class);
    ParameterExpression<Date> endDateSearchParam = cb.parameter(Date.class);


        /*Provide access to fields in class that mapped to columns*/

    Expression<String> partOfName = root.get(Task_.name);
    Expression<Long> statusExp = root.get(Task_.status);
    Expression<Date> startDateExp = root.get(Task_.startDate);
    Expression<Date> endDateExp = root.get(Task_.endDate);


        /*SQL Query customizing*/

            query.select(root)
                    .distinct(true)
                    .where(
                            cb.or(
                                    cb.like(partOfName, nameSearchParam),
                                    cb.equal(statusExp, statusSearchParam),
                                    cb.greaterThanOrEqualTo(startDateExp, startDateSearchParam),
                                    cb.lessThanOrEqualTo(endDateExp, endDateSearchParam)
                    ))
                    .orderBy(cb.asc(root.get(Task_.id)));

            TypedQuery<Task> resultQuery = entityManager.createQuery(query); //prepared statement on hql
            resultQuery.setParameter(nameSearchParam, StringUtils.join("%", name, "%"));
            resultQuery.setParameter(statusSearchParam, status);
            resultQuery.setParameter(startDateSearchParam, startDate);
            resultQuery.setParameter(endDateSearchParam, endDate);
            
            return resultQuery.getResultList();

    }
}

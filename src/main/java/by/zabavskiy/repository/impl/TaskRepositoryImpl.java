package by.zabavskiy.repository.impl;

import by.zabavskiy.domain.Task;
import by.zabavskiy.domain.Task_;
import by.zabavskiy.repository.TaskRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class TaskRepositoryImpl implements TaskRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Task> searchByParamNameCriteriaApi(String name) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Task> query = cb.createQuery(Task.class);
        Root<Task> root = query.from(Task.class);

        ParameterExpression<String> nameSearchParam = cb.parameter(String.class);

        Expression<String> partOfName = root.get(Task_.name);

        query
                .select(root)
                .distinct(true)
                .where(cb.like(partOfName, nameSearchParam))
                .orderBy(cb.asc(root.get(Task_.id)));

        TypedQuery<Task> resultQuery = entityManager.createQuery(query);

        resultQuery.setParameter(nameSearchParam, StringUtils.join("%", name, "%"));

        return resultQuery.getResultList();
    }
}

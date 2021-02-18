package by.zabavskiy.repository.impl;

import by.zabavskiy.domain.Element;
import by.zabavskiy.domain.Element_;
import by.zabavskiy.repository.ElementRepository;
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
public class ElementRepositoryImpl implements ElementRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Element> searchByParamValueCriteriaApi(String value) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Element> query = cb.createQuery(Element.class);
        Root<Element> root = query.from(Element.class);

        ParameterExpression<String> valueSearchParam = cb.parameter(String.class);

        Expression<String> partOfValue = root.get(Element_.value);

        query.select(root)
                .distinct(true)
                    .where
                            (cb.like(partOfValue, valueSearchParam))
                    .orderBy(cb.asc(root.get(Element_.id)));

        TypedQuery<Element> resultQuery = entityManager.createQuery(query);

        resultQuery.setParameter(valueSearchParam, StringUtils.join("%", value, "%"));

        return resultQuery.getResultList();
    }
}

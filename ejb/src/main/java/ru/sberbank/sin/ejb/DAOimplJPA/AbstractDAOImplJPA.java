package ru.sberbank.sin.ejb.DAOimplJPA;


import ru.sberbank.sin.ejb.DAO.AbstractDAO;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;

/**
 * Created by 1 on 25.03.2016.
 */
public abstract class AbstractDAOImplJPA<T extends Serializable> implements AbstractDAO<T> {

    protected abstract EntityManager getEntityManager();

    private Class<T> entityClass;

    public Class<T> getEntityClass() {
        return entityClass;
    }


    public AbstractDAOImplJPA(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void persist(T entity) {
        getEntityManager().persist(entity);
    }

    public T merge(T entity) {
        getEntityManager().merge(entity);
        getEntityManager().flush();
        return entity;
    }

    public void remove(T entity) {
        if (entity != null) {
            getEntityManager().remove(entity);
        }
    }

    public T removeByKey(Object id){
        T entity = (T) getEntityManager().find(entityClass, id);
        remove(entity);
        return entity;
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public void refresh(T entity) {
        getEntityManager().refresh(entity);
    }

    public TypedQuery<T> namedQuery(String queryName) {
        return getEntityManager().createNamedQuery(queryName, entityClass);
    }

    public TypedQuery<T> query(String queryString) {
        return getEntityManager().createQuery(queryString, entityClass);
    }

    public long count() {
        CriteriaQuery criteriaQuery = getEntityManager().getCriteriaBuilder().createQuery();
        Root<T> root = criteriaQuery.from(entityClass);
        criteriaQuery.select(getEntityManager().getCriteriaBuilder().count(root));
        Query query = getEntityManager().createQuery(criteriaQuery);

        return ((Long) query.getSingleResult()).longValue();
    }


}
package ru.sberbank.sin.ejb.DAO;

import javax.persistence.TypedQuery;
import java.io.Serializable;

/**
 * Created by 1 on 25.03.2016.
 */
public interface AbstractDAO<T extends Serializable> {

    Class<T> getEntityClass();

    void persist(T entity);

    T  merge(T entity);

    void remove(T entity);

    T removeByKey(Object id);

    T find(Object id);

    void refresh(T entity);

    TypedQuery<T> namedQuery(String queryName);

    TypedQuery<T> query(String queryString);

    long count();

}

package ru.sberbank.sin.ejb.DAO;

import ru.sberbank.sin.common.entities.Account;

import java.util.List;

/**
 * Created by 1 on 29.03.2016.
 */
public interface AccountDAO extends AbstractDAO<Account>{
    List<Account> findAll();
}

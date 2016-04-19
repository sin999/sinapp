package ru.sberbank.sin.common.services;

import ru.sberbank.sin.common.entities.Account;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 1 on 28.03.2016.
 */

public interface AccountService extends Serializable{
    List<Account> findAll();
    Account find(Integer id);
    Account save(Account account);
    void remove(Account account);
    Account removeByKey(Integer accountId);
}

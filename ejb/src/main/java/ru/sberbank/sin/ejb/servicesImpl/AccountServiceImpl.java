package ru.sberbank.sin.ejb.servicesImpl;



import ru.sberbank.sin.common.entities.Account;
import ru.sberbank.sin.ejb.DAO.AccountDAO;
import ru.sberbank.sin.common.services.AccountService;

import javax.ejb.*;
import java.util.List;

/**
 * Created by 1 on 28.03.2016.
 */
@Stateless
@Remote(AccountService.class)
public class AccountServiceImpl implements AccountService {
    @EJB
    private AccountDAO accountDAO;

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Account> findAll() {
        return accountDAO.findAll();
//        return accountDAO.namedQuery(Account.QUERY_FIND_ALL).getResultList();
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Account save(Account account){
        accountDAO.merge(account);
        return account;
    }

    public void remove(Account account){
        accountDAO.remove(account);
    }

    public Account removeByKey(Integer accountId){
        return accountDAO.removeByKey(accountId);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Account find(Integer id){
        return accountDAO.find(id);
    }

}

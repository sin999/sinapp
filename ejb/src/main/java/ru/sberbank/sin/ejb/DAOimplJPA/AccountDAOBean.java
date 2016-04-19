package ru.sberbank.sin.ejb.DAOimplJPA;


import ru.sberbank.sin.common.entities.Account;
import ru.sberbank.sin.ejb.DAO.AccountDAO;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by 1 on 25.03.2016.
 */
@Stateless
@Remote(AccountDAO.class)
public class AccountDAOBean extends AbstractDAOImplJPA<Account> implements AccountDAO {

    @PersistenceContext(unitName="account")
    private EntityManager entityManager;

    public AccountDAOBean(){
        super(Account.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Account> findAll() {
        return namedQuery(Account.QUERY_FIND_ALL).getResultList();
    }

}

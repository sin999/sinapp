package ru.sberbank.sin.servlet.ws.rs;

import ru.sberbank.sin.common.entities.Account;
import ru.sberbank.sin.common.services.AccountService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by 1 on 26.03.2016.
 */
@Path("accounts")
public class RestAccountService {
    @EJB
    private AccountService accountService;

    @GET
    @Path(value="/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        GenericEntity<List<Account>> list = new GenericEntity<List<Account>>(accountService.findAll()) {};
        return Response.ok(list).build();
    }

    @GET
    @Path(value="get/{accountId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Account getAccountById(@PathParam(value="accountId") Integer accountId) {
        Account account = accountService.find(accountId);
        return account;
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Path(value="/put")
    @Produces({MediaType.APPLICATION_JSON})
    public Account saveAccount(Account account) {
        if(account!=null) {
            account= accountService.save(account);
        }else {
            account=null;
        }
        return account;
    }

    @GET
    @Path(value="delete/{accountId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Account deleteById(@PathParam(value="accountId") Integer accountId) {
        Account account=null;
        try {
            account = accountService.removeByKey(accountId);
        }catch (IllegalArgumentException e){
            //sin //todo: It looks like that we will have to decide how to pass exception up there to the client application
            account=null;
        }
        return account;
    }
}

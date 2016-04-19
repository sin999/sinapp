package ru.sberbank.sin.servlet;


import ru.sberbank.sin.common.entities.Account;
import ru.sberbank.sin.common.services.AccountService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 1 on 25.03.2016.
 */
@WebServlet("/accountService")
public class AccountServlet extends HttpServlet {
    @EJB
    private AccountService accountService;



    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        outHtml(req, resp,"put");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        outHtml(req, resp,"get");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        outHtml(req, resp,"post");
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        outHtml(req, resp,"delete");
    }

    private void outHtml(HttpServletRequest req, HttpServletResponse resp,String type) throws IOException{
        List<Account> accounts=new ArrayList<Account>();
        String errMessage="";
//        if(this.accountEAO.count()==0L){
//            Account account = new Account();
//            account.setAmount(100.0);
//            account.setOwner("Proskuryakov Sergey");
//            this.accountEAO.persist(account);
//        }else {
        try {
            accounts = this.accountService.findAll();
        }catch (Exception e){
            errMessage=e.getLocalizedMessage();
        }
//        }
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<h1>" +errMessage+ accounts.size()+ "test  "+ type+"   " +(new Date()) +"  "+(this.accountService==null?"null":"EAO Injected!")+"</h1>");
        out.println("<table>");
        for(Account account:accounts){
            out.println("<tr><td>"+errMessage+"</td><td>"+account.getAmount()+"</td><td>"+account.getOwner()+"</td></tr>");
        }
        out.println("</table>");
    }

    private void outJson(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        String group = req.getParameter("group");
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
//        JsonSerializer someJsonSerializer = new JsonSerializer();
//        String json = someJsonSerializer.serialize(userService.findUsers(group));
        out.print("json");
    }

}


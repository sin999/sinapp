package ru.sberbank.sin.servlet.ws.rs;

import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.api.json.JSONJAXBContext;
import ru.sberbank.sin.common.entities.Account;

import javax.ws.rs.ext.ContextResolver;
import javax.xml.bind.JAXBContext;

/**
 * Created by 1 on 27.03.2016.
 */
//@Provider
public class JAXBContextResolver implements ContextResolver< JAXBContext > {

    private JAXBContext context;
    private Class[] types = {Account.class};

    public JAXBContextResolver() throws Exception {
        this.context = new JSONJAXBContext(JSONConfiguration.mapped().arrays("Account").build(),
                types);

    }

    public JAXBContext getContext(Class objectType) {
        for (Class type : types) {
            if (type == objectType) {
                return context;
            }
        }
        return null;
    }
}

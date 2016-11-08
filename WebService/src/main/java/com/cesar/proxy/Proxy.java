package com.cesar.proxy;

import com.cesar.business.BusinessBean;
import com.cesar.business.RemoteBusinessBean;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.naming.Context;
import util.Constantes;


@Startup
@Singleton
public class Proxy {

    private static RemoteBusinessBean bean;

    @PostConstruct
    public void setup() {
        try {
            Class classBean = BusinessBean.class;
            Class classInterface = RemoteBusinessBean.class;
            final String distinctName = "";
            final String beanName = classBean.getSimpleName();
            final String viewClassName = classInterface.getName();
            final Hashtable props = new Hashtable();
            props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
            final Context context = new javax.naming.InitialContext(props);

            bean = (RemoteBusinessBean) context.lookup("ejb:" + Constantes.APP_NAME + "/" + Constantes.MODULE_NAME
                    + "/" + distinctName + "/" + beanName + "!" + viewClassName);

        } catch (Exception ex) {
            Logger.getLogger(Proxy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static RemoteBusinessBean getbBusinessBean() {
        return bean;
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cesar.proxy;

import com.cesar.business.RemoteBusinessBean;
import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import org.jboss.ejb.client.ContextSelector;
import org.jboss.ejb.client.EJBClientConfiguration;
import org.jboss.ejb.client.EJBClientContext;
import org.jboss.ejb.client.PropertiesBasedEJBClientConfiguration;
import org.jboss.ejb.client.remoting.ConfigBasedEJBClientContextSelector;

/**
 *
 * @author cesar
 */
@Startup
@Singleton
public class Proxy {

    private RemoteBusinessBean businessBean;

    @PostConstruct
    public void setup() {
        String IPJBoss = "localhost";
        String PortaJBoss = "4447";
        String UsuarioJboss = "wscovabra";
        String SenhaJboss = "pa33Lx$k";

        Properties p = new Properties();
        {
            p.put("remote.connectionprovider.create.options.org.xnio.Options.SSL_ENABLED", "false");
            p.put("remote.connections", "default");
            p.put("remote.connection.default.host", IPJBoss);
            p.put("remote.connection.default.port", PortaJBoss);
            p.put("remote.connection.default.username", UsuarioJboss);
            p.put("remote.connection.default.password", SenhaJboss);
        }

        EJBClientConfiguration cc = new PropertiesBasedEJBClientConfiguration(p);
        ContextSelector<EJBClientContext> selector = new ConfigBasedEJBClientContextSelector(cc);
        EJBClientContext.setSelector(selector);

    }

    public RemoteBusinessBean getBusinessBean() {
        return businessBean;
    }

}

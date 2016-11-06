package com.cesar.proxy;

/**
 *
 * @author cesar
 */
import com.cesar.business.BusinessBean;
import com.cesar.business.RemoteBusinessBean;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.jboss.ejb.client.ContextSelector;
import org.jboss.ejb.client.EJBClientConfiguration;
import org.jboss.ejb.client.EJBClientContext;
import org.jboss.ejb.client.PropertiesBasedEJBClientConfiguration;
import org.jboss.ejb.client.remoting.ConfigBasedEJBClientContextSelector;
import util.Constantes;

public class ConexaoJBoss {

    private static String IPJBoss = "";
    private static String PortaJBoss = "";
    private static String UsuarioJboss = "";
    private static String SenhaJboss = "";
    private static ConexaoJBoss instance;

    public static ConexaoJBoss getInstance() {
        if (instance == null) {
            instance = new ConexaoJBoss();
        }
        return instance;
    }

    private ConexaoJBoss() {

        IPJBoss = "localhost";
        PortaJBoss = "4447";
        UsuarioJboss = "wscovabra";
        SenhaJboss = "pa33Lx$k";

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

    private Object lookupRemote(Class classBean, Class classInterface) throws NamingException {
        Properties props = new Properties();
        props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        InitialContext context = new InitialContext(props);

        return context.lookup(jndiEjb(classBean, classInterface));
    }

    public static String jndiEjb(Class classBean, Class classInterface) {
        String appName = Constantes.APP_NAME;
        String moduleName = Constantes.MODULE_NAME;
        final String distinctName = "";
        final String beanName = classBean.getSimpleName();
        final String viewClassName = classInterface.getName();
        if (classBean.getAnnotation(javax.ejb.Stateful.class) != null) {
            return ("ejb:" + appName + "/" + moduleName
                    + "/" + distinctName + "/" + beanName + "!" + viewClassName + "?stateful");
        } else {
            return ("ejb:" + appName + "/" + moduleName
                    + "/" + distinctName + "/" + beanName + "!" + viewClassName);
        }
    }
    
    public static void main(String[] args) throws NamingException {
        RemoteBusinessBean bean = (RemoteBusinessBean)ConexaoJBoss.getInstance().lookupRemote(BusinessBean.class, RemoteBusinessBean.class);
        bean.cadFornecedor(null);
    }

}

package com.cesar.client;

import com.cesar.CadastroProvider;
import com.cesar.CadastroProviderService;
import com.cesar.Fornecedor;
import com.cesar.Retorno;
import javax.xml.ws.BindingProvider;

/**
 * Hello world!
 *
 */
public class JaxWsClient {

    public static void main(String[] args) {

        try {

            CadastroProviderService service = new CadastroProviderService();

            CadastroProvider port = service.getCadastroProviderPort();

            BindingProvider bp = (BindingProvider) port;
            bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, "wscovabra");
            bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, "pa33Lx$k");

            Fornecedor f = new Fornecedor();
            f.setId(100);
            f.setRazaoSocial("Teste");
            Retorno retorno = port.cadFornecedor(f);

            System.out.println(retorno.getStatus());
            System.out.println(retorno.getMensagem());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

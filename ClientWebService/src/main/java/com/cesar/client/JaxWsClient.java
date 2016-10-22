package com.cesar.client;

import com.cesar.CadastroProvider;
import com.cesar.CadastroProviderService;
import com.cesar.Fornecedor;
import com.cesar.Retorno;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;

/**
 * Hello world!
 *
 */
public class JaxWsClient {

    public static void main(String[] args) {

        try {

            URL url = new URL("http://localhost:8080/WebService/cadastroProvider?wsdl");

            QName qName = new QName("http://cesar.com/", "CadastroProviderService");

            CadastroProviderService service = new CadastroProviderService(url, qName);

            CadastroProvider port = service.getCadastroProviderPort();

            BindingProvider provider = (BindingProvider) port;
            Map<String, Object> req_ctx = provider.getRequestContext();

            Map<String, List<String>> headers = new HashMap<String, List<String>>();
            headers.put("usuario", Collections.singletonList("cesar"));
            headers.put("senha", Collections.singletonList("123456"));
            req_ctx.put(MessageContext.HTTP_REQUEST_HEADERS, headers);

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

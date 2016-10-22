package com.cesar;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author cesar
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class CadastroProvider {

    @Resource
    WebServiceContext wsctx;

    @WebMethod(operationName = "cadFornecedor")
    public Retorno cadFornecedor(@WebParam(name = "fornecedor") Fornecedor fornecedor) {

        Retorno retorno = new Retorno();
        if (permitirConexao()) {

            if (fornecedor == null || fornecedor.getId() <= 0 || fornecedor.getRazaoSocial() == null || fornecedor.getRazaoSocial().isEmpty()) {
                retorno.setStatus(2);
                retorno.setMensagem("Erro: campos obrigatorios nao informados");
            } else {
                retorno.setStatus(1);
                retorno.setMensagem("Fornecedor " + fornecedor.getId() + "  cadastrado com sucesso");
            }

        } else {
            retorno.setStatus(3);
            retorno.setMensagem("Acesso negado");
        }
        return retorno;

    }

    private boolean permitirConexao() {
        MessageContext mctx = wsctx.getMessageContext();

        // Use the request headers to get the details
        Map http_headers = (Map) mctx.get(MessageContext.HTTP_REQUEST_HEADERS);
        List<String> userList = (List) http_headers.get("usuario");
        List<String> passList = (List) http_headers.get("senha");

        String username = "";
        String password = "";

        if (userList != null) {
            username = userList.get(0);
        }

        if (passList != null) {
            password = passList.get(0);

        }

        return username.equals("cesar") && password.equals("123456");
    }
}

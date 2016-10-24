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

/**
 *
 * @author cesar
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class CadastroProvider {

    private static final int COD_SUCESSO = 1;
    private static final int COD_ERRO = 2;

    @Resource
    WebServiceContext wsctx;

    @WebMethod(operationName = "cadFornecedor")
    public Retorno cadFornecedor(@WebParam(name = "fornecedor") Fornecedor fornecedor) {
        Retorno retorno = new Retorno();
        try {

            verificaPermissaoAcesso();

            retorno = verificaDadosFornecedorExistente(fornecedor);

            if (retorno.getStatus() != COD_SUCESSO) {
                retorno = validaDadosFornecedor(fornecedor);
                persisteDadosFornecedor(fornecedor, retorno);
            }

            return retorno;
        } catch (Exception ex) {
            retorno.setStatus(COD_ERRO);
            retorno.setMensagem(ex.getMessage());
            return retorno;
        }
    }

    private Retorno validaDadosFornecedor(Fornecedor fornecedor) {
        Retorno retorno = new Retorno();
        retorno.setStatus(COD_SUCESSO);
        retorno.setMensagem("");

        if (fornecedor == null || fornecedor.getId() <= 0 || fornecedor.getRazaoSocial() == null || fornecedor.getRazaoSocial().isEmpty()) {
            retorno.setStatus(COD_ERRO);
            retorno.setMensagem("Campos obrigatorios nao preenchidos");
        }
        if (fornecedor.getRazaoSocial().length() > 10) {
            retorno.setStatus(COD_ERRO);
            retorno.setMensagem(retorno.getMensagem() + "|Razao social fora do layout");
        }

        return retorno;
    }

    private void verificaPermissaoAcesso() throws Exception {
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

        boolean acessoPermitido = username.equals("cesar") && password.equals("123456");
        if (!acessoPermitido) {
            throw new Exception("Acesso negado");
        }
    }

    private void persisteDadosFornecedor(Fornecedor fornecedor, Retorno retorno) {
        //persiste os dados do fornecedor com o status de retorno
    }

    private Retorno verificaDadosFornecedorExistente(Fornecedor fornecedor) {
        Retorno retorno = new Retorno();
        //se ja existe o fornecedor com status 1 - Sucesso no banco de dados, retornamos o status e a mensagem existente
        //retorno.setStatus(<codigo retorno do banco de dados>);
        //retorno.setMensagem(<mensagem retorno do banco de dados>);
        return retorno;
    }

}

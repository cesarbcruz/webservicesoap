package com.cesar;

import com.cesar.business.model.Fornecedor;
import com.cesar.business.model.Retorno;
import com.cesar.proxy.Proxy;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import util.Constantes;

/**
 *
 * @author cesar
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class CadastroProvider {

    @Inject
    Proxy proxy;

    @WebMethod(operationName = "cadFornecedor")
    public Retorno cadFornecedor(@WebParam(name = "fornecedor") Fornecedor fornecedor) {
        try {

            return proxy.getbBusinessBean().cadFornecedor(fornecedor);

        } catch (Exception ex) {
            return criarRetornoErroSistemico(ex);
        }
    }

    private Retorno criarRetornoErroSistemico(Exception ex) {
        Retorno retorno = new Retorno();
        retorno.setStatus(Constantes.COD_ERRO);
        retorno.setMensagem(Constantes.MSG_ERRO_PADRAO);
        Logger.getLogger(CadastroProvider.class.getName()).log(Level.SEVERE, "{0}\n{1}", new Object[]{retorno.getMensagem(), ex.getMessage()});
        return retorno;
    }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cesar.business;

import com.cesar.business.model.Fornecedor;
import com.cesar.business.model.Retorno;
import javax.ejb.Stateless;

/**
 *
 * @author cesar
 */
@Stateless
public class BusinessBean implements RemoteBusinessBean {

    @Override
    public Retorno cadFornecedor(Fornecedor f) {
        Retorno r = new Retorno();
        r.setStatus(1);
        r.setMensagem("fornecedor cadastrado com sucesso");
        return r;
    }

}

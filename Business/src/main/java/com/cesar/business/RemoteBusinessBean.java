/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cesar.business;

import com.cesar.business.model.Fornecedor;
import com.cesar.business.model.Retorno;

/**
 *
 * @author cesar
 */
public interface RemoteBusinessBean {

    public Retorno cadFornecedor(Fornecedor f);
}

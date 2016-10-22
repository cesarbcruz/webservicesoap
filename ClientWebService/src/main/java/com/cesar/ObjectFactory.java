
package com.cesar;

import com.cesar.*;
import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.cesar package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.cesar
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Retorno }
     * 
     */
    public Retorno createRetorno() {
        return new Retorno();
    }

    /**
     * Create an instance of {@link Fornecedor }
     * 
     */
    public Fornecedor createFornecedor() {
        return new Fornecedor();
    }

}

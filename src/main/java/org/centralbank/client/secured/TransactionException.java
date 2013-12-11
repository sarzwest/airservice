
package org.centralbank.client.secured;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "TransactionFault", targetNamespace = "http://centralbank.org/")
public class TransactionException
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private TransactionFault faultInfo;

    /**
     * 
     * @param message
     * @param faultInfo
     */
    public TransactionException(String message, TransactionFault faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param message
     * @param faultInfo
     * @param cause
     */
    public TransactionException(String message, TransactionFault faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: org.centralbank.client.secured.TransactionFault
     */
    public TransactionFault getFaultInfo() {
        return faultInfo;
    }

}

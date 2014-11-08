
package org.centralbank.client.signed;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "SignedService", targetNamespace = "http://centralbank.org/", wsdlLocation = "http://aos.czacm.org/bank/signed?wsdl")
public class SignedService
    extends Service
{

    private final static URL SIGNEDSERVICE_WSDL_LOCATION;
    private final static WebServiceException SIGNEDSERVICE_EXCEPTION;
    private final static QName SIGNEDSERVICE_QNAME = new QName("http://centralbank.org/", "SignedService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://aos.czacm.org/bank/signed?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        SIGNEDSERVICE_WSDL_LOCATION = url;
        SIGNEDSERVICE_EXCEPTION = e;
    }

    public SignedService() {
        super(__getWsdlLocation(), SIGNEDSERVICE_QNAME);
    }

    public SignedService(WebServiceFeature... features) {
        super(__getWsdlLocation(), SIGNEDSERVICE_QNAME, features);
    }

    public SignedService(URL wsdlLocation) {
        super(wsdlLocation, SIGNEDSERVICE_QNAME);
    }

    public SignedService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, SIGNEDSERVICE_QNAME, features);
    }

    public SignedService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SignedService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns BankService
     */
    @WebEndpoint(name = "SignedPort")
    public BankService getSignedPort() {
        return super.getPort(new QName("http://centralbank.org/", "SignedPort"), BankService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns BankService
     */
    @WebEndpoint(name = "SignedPort")
    public BankService getSignedPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://centralbank.org/", "SignedPort"), BankService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (SIGNEDSERVICE_EXCEPTION!= null) {
            throw SIGNEDSERVICE_EXCEPTION;
        }
        return SIGNEDSERVICE_WSDL_LOCATION;
    }

}
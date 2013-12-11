/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centralbank.client;

import airservice.exceptions.SystemException;
import airservice.qualifiers.SecuredClient;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import org.apache.ws.security.handler.WSHandlerConstants;
import org.centralbank.client.secured.SecuredService;
import org.centralbank.client.signed.BankService;
import org.centralbank.client.signed.SignedService;
import org.centralbank.client.signed.TransactionException;
import org.centralbank.client.unsecured.UnsecuredService;

/**
 *
 * @author Tomas "sarzwest" Jiricek
 */
@SecuredClient
public class SecuredBankClient extends BankClient {

    private Map<String, Object> inContext;
    private Map<String, Object> outContext;
    private org.centralbank.client.secured.BankService securedPort;

    public SecuredBankClient() {
    }

    @PostConstruct
    public void init() {
        SecuredService securedService = new SecuredService();
        securedPort = securedService.getSecuredPort();
        
        inContext = new HashMap<String, Object>();
        inContext.put(WSHandlerConstants.ACTION, WSHandlerConstants.SIGNATURE + " " + WSHandlerConstants.ENCRYPT);
        inContext.put(WSHandlerConstants.SIG_PROP_FILE, "bank.properties");
        inContext.put(WSHandlerConstants.SIGNATURE_USER, "bank");
        inContext.put(WSHandlerConstants.DEC_PROP_FILE, "client.properties");
        inContext.put(WSHandlerConstants.ENCRYPTION_USER, "client");
        inContext.put(WSHandlerConstants.PW_CALLBACK_CLASS, "org.centralbank.client.PasswordCallback");
        registerInInterceptor(securedPort, inContext);
        
        outContext = new HashMap<String, Object>();
        outContext.put(WSHandlerConstants.ACTION, WSHandlerConstants.SIGNATURE + " " + WSHandlerConstants.ENCRYPT);
        outContext.put(WSHandlerConstants.USER, "xxx");
        outContext.put(WSHandlerConstants.SIG_PROP_FILE, "client.properties");
        outContext.put(WSHandlerConstants.SIGNATURE_USER, "client");
        outContext.put(WSHandlerConstants.ENC_PROP_FILE, "bank.properties");
        outContext.put(WSHandlerConstants.ENCRYPTION_USER, "bank");
        outContext.put(WSHandlerConstants.PW_CALLBACK_CLASS, "org.centralbank.client.PasswordCallback");
        registerOutInterceptor(securedPort, outContext);
    }

    public static void testSecured() {
        SecuredService securedService = new SecuredService();
        org.centralbank.client.secured.BankService securedPort = securedService.getSecuredPort();

        // konfigurace vstupniho filtru
        Map<String, Object> inContext = new HashMap<String, Object>();
        inContext.put(WSHandlerConstants.ACTION, WSHandlerConstants.SIGNATURE + " " + WSHandlerConstants.ENCRYPT);
        inContext.put(WSHandlerConstants.SIG_PROP_FILE, "bank.properties");
        inContext.put(WSHandlerConstants.SIGNATURE_USER, "bank");
        inContext.put(WSHandlerConstants.DEC_PROP_FILE, "client.properties");
        inContext.put(WSHandlerConstants.ENCRYPTION_USER, "client");
        inContext.put(WSHandlerConstants.PW_CALLBACK_CLASS, "org.centralbank.client.PasswordCallback");
        registerInInterceptor(securedPort, inContext);
        // konfigurace vystupniho filtru
        Map<String, Object> outContext = new HashMap<String, Object>();
        outContext.put(WSHandlerConstants.ACTION, WSHandlerConstants.SIGNATURE + " " + WSHandlerConstants.ENCRYPT);
        outContext.put(WSHandlerConstants.USER, "xxx");
        outContext.put(WSHandlerConstants.SIG_PROP_FILE, "client.properties");
        outContext.put(WSHandlerConstants.SIGNATURE_USER, "client");
        outContext.put(WSHandlerConstants.ENC_PROP_FILE, "bank.properties");
        outContext.put(WSHandlerConstants.ENCRYPTION_USER, "bank");
        outContext.put(WSHandlerConstants.PW_CALLBACK_CLASS, "org.centralbank.client.PasswordCallback");
        registerOutInterceptor(securedPort, outContext);
        try {
            securedPort.newTransaction(159, 753, 456, "sekjured ***");
            System.out.println("secured probehla");
        } catch (org.centralbank.client.secured.TransactionException ex) {
            System.out.println("secured neprobehla");
//            Logger.getLogger(SecuredBankClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void pay(long fromAccount, long toAccount, long amount, String note) throws SystemException {
        try {
            securedPort.newTransaction(fromAccount, toAccount, amount, note);
        } catch (org.centralbank.client.secured.TransactionException ex) {
            Logger.getLogger(SecuredBankClient.class.getName()).log(Level.SEVERE, null, ex);
            throw new SystemException("Payment failure: " + ex.getMessage());
        }
    }

    public static void main(String[] args) throws TransactionException {
        testUnsecured();
        testSigned();
        testSecured();
    }

    public static void testUnsecured() {
        UnsecuredService unsecuredService = new UnsecuredService();
        org.centralbank.client.unsecured.BankService bankservice = unsecuredService.getUnsecuredPort();
        try {
            bankservice.newTransaction(123456789, 987654333, 999, "some note");
            System.out.println("unsecured probehla");
        } catch (org.centralbank.client.unsecured.TransactionException ex) {
            System.out.println("unsecured neprobehla");
//            Logger.getLogger(SecuredBankClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void testSigned() {
        SignedService signedService = new SignedService();
        org.centralbank.client.signed.BankService signedPort = signedService.getSignedPort();
        // konfigurace vstupniho filtru
        Map<String, Object> inContext = new HashMap<String, Object>();
        inContext.put(WSHandlerConstants.ACTION, WSHandlerConstants.SIGNATURE); // akce, kterou registrujeme
        inContext.put(WSHandlerConstants.SIG_PROP_FILE, "bank.properties"); // konfigurace keystore
        inContext.put(WSHandlerConstants.USER, "bank"); // kterou identitu z keystore budeme pouzivat
        inContext.put(WSHandlerConstants.PW_CALLBACK_CLASS, "org.centralbank.client.PasswordCallback"); // prihlaseni ke keystore
        registerInInterceptor(signedPort, inContext);
        // konfigurace vystupniho filtru
        Map<String, Object> outContext = new HashMap<String, Object>();
        outContext.put(WSHandlerConstants.ACTION, WSHandlerConstants.SIGNATURE); // akce, kterou registrujeme
        outContext.put(WSHandlerConstants.SIG_PROP_FILE, "client.properties"); // konfigurace keystore
        outContext.put(WSHandlerConstants.USER, "client"); // kterou identitu z keystore budeme pouzivat
        outContext.put(WSHandlerConstants.PW_CALLBACK_CLASS, "org.centralbank.client.PasswordCallback"); // prihlaseni ke keystore
        registerOutInterceptor(signedPort, outContext);

        try {
            signedPort.newTransaction(123, 321, 1456, "cnuesbce");
            System.out.println("signed probehla");
        } catch (org.centralbank.client.signed.TransactionException ex) {
            System.out.println("signed neprobehla");
//            Logger.getLogger(SecuredBankClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

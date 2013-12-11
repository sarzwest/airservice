/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centralbank.client.unsecured;

/**
 *
 * @author Tomas "sarzwest" Jiricek
 */
public class Test {
    
    public static void main(String[] args) throws TransactionException {
        UnsecuredService unsecuredService = new UnsecuredService();
        BankService bankservice = unsecuredService.getUnsecuredPort();
        bankservice.newTransaction(123456789, 987654333, 999, "some note");
    }
}

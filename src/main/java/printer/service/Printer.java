/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package printer.service;

import emailsender.consumer.Email;
import javax.activation.DataHandler;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author Tomas "sarzwest" Jiricek
 */
@WebService(targetNamespace = "http://aos.fel.cvut.cz/airservice/")
public interface Printer {
    
    @WebMethod(operationName = "printReservation")
    public DataHandler print(@WebParam(name = "Reservation")Reservation reservation);
    
    @WebMethod(operationName = "sendEmail")
    public void sendEmail(@WebParam(name = "EmailAddress")String email, @WebParam(name = "Reservation")Reservation reservation);
}

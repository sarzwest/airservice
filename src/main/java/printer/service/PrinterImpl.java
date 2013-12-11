/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package printer.service;

import emailsender.consumer.Email;
import emailsender.producer.EmailSenderClient;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebService;
import javax.xml.ws.soap.MTOM;


/**
 *
 * @author Tomas "sarzwest" Jiricek
 */
@MTOM
@WebService(portName = "PrinterPort", 
        serviceName = "PrinterService", 
        targetNamespace = "http://aos.fel.cvut.cz/airservice/", 
        endpointInterface = "printer.service.Printer")
@Stateless
public class PrinterImpl implements Printer{
    
    @Inject
    EmailSenderClient emailClient;
    
    @Override
    public DataHandler print(Reservation reservation) {
        String s = generateFileContent(reservation);
        return new ByteArrayDataHandler(s.getBytes(), "reservation.txt");
    }

    @Override
    public void sendEmail(String emailAddress, Reservation reservation) {
        ByteArrayDataHandler byteArrayDataHandler = new ByteArrayDataHandler(generateFileContent(reservation).getBytes(), "reservation.txt");
        ByteArrayDataSource dataSource = (ByteArrayDataSource) byteArrayDataHandler.getDataSource();
        Email email = new Email(emailAddress, new ByteArrayDataHandler(generateFileContent(reservation).getBytes(), "reservation.txt"));
        emailClient.sendEmail(email);
    }

    public String generateFileContent(Reservation reservation) {
        String s = "Document about reservation payment\n"
                + "FLIGHT: " + reservation.getFlightName() + "\n"
                + "CREATED ON: " + reservation.getCreated() + "\n"
                + "SEATS RESERVED: " + reservation.getSeats();
        return s;
    }
}

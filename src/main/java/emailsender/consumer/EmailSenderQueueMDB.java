/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package emailsender.consumer;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**
 *
 * @author Tomas "sarzwest" Jiricek
 */
@MessageDriven(name = "EmailSenderQueueMDB", activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/EMAILSENDERMDBQueue"),
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")
})
public class EmailSenderQueueMDB implements MessageListener{

    @Override
    public void onMessage(Message msg) {
        try {
            ObjectMessage om = (ObjectMessage)msg;
            Email email = (Email) om.getObject();
            System.out.println(email.getEmailAddress());
            System.out.println(email.getAttachement());
            Thread.sleep(5000);
        } catch (JMSException ex) {
            Logger.getLogger(EmailSenderQueueMDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(EmailSenderQueueMDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

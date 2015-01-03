/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package emailsender.producer;

import emailsender.consumer.Email;
import javax.annotation.Resource;
import javax.jms.BytesMessage;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.StreamMessage;
import javax.jms.TextMessage;

/**
 *
 * @author Tomas "sarzwest" Jiricek
 */
public class EmailSenderClient {

    @Resource(mappedName = "java:/ConnectionFactory")
    private ConnectionFactory connectionFactory;
    @Resource(mappedName = "java:/queue/EMAILSENDERMDBQueue")
    private Queue queue;

    public void sendEmail(Email email) {
        Connection connection = null;
        try {
            connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(queue);
            messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);
            messageProducer.setTimeToLive(10000);
            connection.start();
            ObjectMessage objMsg = session.createObjectMessage();
            objMsg.setObject(email);
            
            messageProducer.send(objMsg);
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

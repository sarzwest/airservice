/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package emailsender.consumer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;

/**
 *
 * @author Tomas "sarzwest" Jiricek
 */
public class Email implements Serializable{
    
    private String emailAddress;
    private String attachement;

    public Email() {
    }

    public Email(String emailAddress, DataHandler attachement) {
        this.emailAddress = emailAddress;
        StringBuilder sb = new StringBuilder();
        try {
            Object o = attachement.getContent();
            ByteArrayInputStream is = (ByteArrayInputStream) o;
            byte b = (byte) is.read();
            while(b != -1){
                sb.append((char)b);
                b = (byte)is.read();
            }
        } catch (IOException ex) {
            Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.attachement = sb.toString();
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

//    public DataHandler getAttachement() {
//        return attachement;
//    }
//
//    public void setAttachement(DataHandler attachement) {
//        this.attachement = attachement;
//    }
//    
//    public String getAttachementAsString(){
//        StringBuilder sb = new StringBuilder();
//        try {
//            Object o = attachement.getContent();
//            ByteArrayInputStream is = (ByteArrayInputStream) o;
//            char c = (char) is.read();
//            while(c != 0){
//                sb.append(c);
//            }
//            System.out.println(o);
//            return sb.toString();
//        } catch (IOException ex) {
//            Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
//        }
//    }

    public String getAttachement() {
        return attachement;
    }

    public void setAttachement(String attachement) {
        this.attachement = attachement;
    }
}

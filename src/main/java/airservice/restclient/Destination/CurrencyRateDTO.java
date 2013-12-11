/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package airservice.restclient.Destination;

/**
 *
 * @author Tomas "sarzwest" Jiricek
 */
public class CurrencyRateDTO {
    private String to;
    private float rate;
    private String from;

    public CurrencyRateDTO() {
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
    
}

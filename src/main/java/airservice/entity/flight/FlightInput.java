/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package airservice.entity.flight;

/**
 *
 * @author Tomas "sarzwest" Jiricek
 */
public class FlightInput extends Flight{

    private String dateOfDeparture;//YYYY-MM-DDThh:mm:ssTZD
    private long from;//při vytváření se posílá pouze id letu
    private long to;//při vytváření se posílá pouze id letu

    public FlightInput() {
    }

    public FlightInput(float distance, String name, float price, int seats, String dateOfDep, long from, long to) {
        super(distance, name, price, seats);
        this.dateOfDeparture = dateOfDep;
        this.from = from;
        this.to = to;
    }

    public String getDateOfDeparture() {
        return dateOfDeparture;
    }

    public void setDateOfDeparture(String dateOfDepartureString){
        this.dateOfDeparture = dateOfDepartureString;
    }

    public long getFrom() {
        return from;
    }

    public void setFrom(long from) {
        this.from = from;
    }

    public long getTo() {
        return to;
    }

    public void setTo(long to) {
        this.to = to;
    }
}

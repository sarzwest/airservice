/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package airservice.entity.reservation;

import airservice.entity.StateChoices;
import airservice.entity.flight.FlightOutput;
import airservice.util.Properties;
import java.text.SimpleDateFormat;
import javax.ws.rs.core.UriBuilder;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Tomas "sarzwest" Jiricek
 */
@XmlRootElement(name = "reservation")
public class ReservationOutput extends Reservation{
    
    private long id;
    private FlightOutput flight;//při vytváření se posílá pouze id letu
    private String created;//datum a čas vytvoření rezervace
    private String password;//náhodně vygenerované heslo, které klient používá pro přístup k rezervaci a jejím úpravám
    private String state;//stav rezervace (new, canceled, paid)
    private String uri;

    public ReservationOutput() {
    }
    
    public ReservationOutput(ReservationEntity reservation, UriBuilder ub){
        super(reservation.getSeats());
        flight = new FlightOutput(reservation.getFlight());
        created = new SimpleDateFormat(Properties.DATE_FORMAT).format(reservation.getCreated());
        state = convertState(reservation.getState());
        uri = ub.path(String.valueOf(reservation.getId())).build().getPath();
        id = reservation.getId();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setState(String state) {
        this.state = state;
    }

    public FlightOutput getFlight() {
        return flight;
    }

    public void setFlight(FlightOutput flight) {
        this.flight = flight;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getState() {
        return state;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
    
    private String convertState(StateChoices stat){
        if(stat == StateChoices.NEW){
            return "NEW";
        }else if(stat==StateChoices.CANCELED){
            return "CANCELED";
        }else{
            return "PAID";
        }
    }
}

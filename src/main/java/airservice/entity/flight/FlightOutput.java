/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package airservice.entity.flight;

import airservice.entity.destination.DestinationOutput;
import airservice.util.Properties;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ws.rs.core.UriBuilder;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Tomas "sarzwest" Jiricek
 */
@XmlRootElement(name = "flight")
public class FlightOutput extends Flight{
    
    private long id;
    private String uri;
    private String dateOfDeparture;//YYYY-MM-DDThh:mm:ssZ
    private DestinationOutput from;//při vytváření se posílá pouze id letu
    private DestinationOutput to;//při vytváření se posílá pouze id letu
    private float distance;
    private float price;

    public FlightOutput() {
    }

    public FlightOutput(FlightEntity flight) {
        super(flight.getDistance(), flight.getName(), flight.getPrice(), flight.getSeats());
        id = flight.getId();
        dateOfDeparture = new SimpleDateFormat(Properties.DATE_FORMAT).format(flight.getDateOfDeparture());
        
        from = new DestinationOutput(flight.getFrom());
        to = new DestinationOutput(flight.getTo());
        distance = flight.getDistance();
        price = flight.getPrice();
    }

    public String getUri() {
        return uri;
    }

    public final void setUri(String uri) {
        this.uri = uri;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public float getDistance() {
        return distance; //To change body of generated methods, choose Tools | Templates.
    }

    public String getDateOfDeparture() {
        return dateOfDeparture;
    }

    public void setDateOfDeparture(String dateOfDeparture) {
        this.dateOfDeparture = dateOfDeparture;
    }

    public DestinationOutput getFrom() {
        return from;
    }

    public void setFrom(DestinationOutput from) {
        this.from = from;
    }

    public DestinationOutput getTo() {
        return to;
    }

    public void setTo(DestinationOutput to) {
        this.to = to;
    }
}

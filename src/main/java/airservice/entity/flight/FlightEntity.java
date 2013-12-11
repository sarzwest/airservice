/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package airservice.entity.flight;

import airservice.db.AOSMemoryDB;
import airservice.entity.destination.DestinationEntity;
import airservice.exceptions.BadRequestException;
import airservice.exceptions.BusinessLogicException;
import airservice.util.Properties;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ws.rs.core.UriBuilder;

/**
 *
 * @author Tomas "sarzwest" Jiricek
 */
public class FlightEntity extends Flight {

    private static long idGenerator;
    
    private long id;
    private Date dateOfDeparture;//YYYY-MM-DDThh:mm:ssZ
    private DestinationEntity from;//při vytváření se posílá pouze id letu
    private DestinationEntity to;//při vytváření se posílá pouze id letu
    private String uri;
    //transient
    private int seatsReserved;

    public FlightEntity(FlightInput flight, UriBuilder ub) throws ParseException {
        super(flight.getDistance(), flight.getName(), flight.getPrice(), flight.getSeats());
        DestinationEntity from = AOSMemoryDB.getDestinationById(flight.getFrom());
        if(from == null){
            throw new BadRequestException("Destination 'from' is not in database");
        }
        DestinationEntity to = AOSMemoryDB.getDestinationById(flight.getTo());
        if(to == null){
            throw new BadRequestException("Destination 'to' is not in database");
        }
        Date date = new SimpleDateFormat(Properties.DATE_FORMAT).parse(flight.getDateOfDeparture());
        this.dateOfDeparture = date;
        this.from = from;
        this.to = to;
        this.id = ++idGenerator;
        setUri(ub.path(String.valueOf(id)).build().getPath());
    }
    
    /**
     * testovaci constructor, nepouzivat !!!!
     * @param flight
     * @throws ParseException 
     */
    public FlightEntity(FlightInput flight) throws ParseException {
        super(flight.getDistance(), flight.getName(), flight.getPrice(), flight.getSeats());
        DestinationEntity from = AOSMemoryDB.getDestinationById(flight.getFrom());
        if(from == null){
            throw new BadRequestException("Destination 'from' is not in database");
        }
        DestinationEntity to = AOSMemoryDB.getDestinationById(flight.getTo());
        if(to == null){
            throw new BadRequestException("Destination 'to' is not in database");
        }
        Date date = new SimpleDateFormat(Properties.DATE_FORMAT).parse(flight.getDateOfDeparture());
        this.dateOfDeparture = date;
        this.from = from;
        this.to = to;
        this.id = ++idGenerator;
    }

    public void reserveSeats(int newSeats) throws BusinessLogicException {
        if (seatsReserved + newSeats > getSeats()) {
            throw new BusinessLogicException("Flight is full. Capacity is " + getSeats() + " and already is reserved " + seatsReserved + " seats.");
        }
        seatsReserved += newSeats;
    }

    public final void setUri(String uri) {
        this.uri = uri;
    }

    public long getId() {
        return id;
    }

    public int getSeatsReserved() {
        return seatsReserved;
    }

    public void setSeatsReserved(int seatsReserved) {
        this.seatsReserved = seatsReserved;
    }

    public Date getDateOfDeparture() {
        return dateOfDeparture;
    }

    public void setDateOfDeparture(Date dateOfDeparture) {
        this.dateOfDeparture = dateOfDeparture;
    }

    public DestinationEntity getFrom() {
        return from;
    }

    public void setFrom(DestinationEntity from) {
        this.from = from;
    }

    public DestinationEntity getTo() {
        return to;
    }

    public void setTo(DestinationEntity to) {
        this.to = to;
    }

    public void deleteSeats(int seats) {
        this.seatsReserved -= seats;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package airservice.entity.reservation;

import airservice.db.AOSMemoryDB;
import airservice.entity.StateChoices;
import airservice.entity.flight.FlightEntity;
import airservice.exceptions.BusinessLogicException;
import java.util.Date;
import javax.persistence.Entity;

/**
 *
 * @author Tomas "sarzwest" Jiricek
 */

public class ReservationEntity extends Reservation{
    
    private static long idGenerator;
    
    private long id;
    private String password;
    private Date created;
    private FlightEntity flight;
    private StateChoices state;

    public ReservationEntity(ReservationInput reservation, long flightId) {
        super(reservation.getSeats());
        created = new Date();
        state = StateChoices.NEW;
        flight = AOSMemoryDB.getFlightById(flightId);
        password = generatePassword();
        id = ++idGenerator;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public long getId() {
        return id;
    }

    public Date getCreated() {
        return created;
    }

    public FlightEntity getFlight() {
        return flight;
    }

    public void setFlight(FlightEntity flight) {
        this.flight = flight;
    }

    public StateChoices getState() {
        return state;
    }

    public void setState(StateChoices state) {
        this.state = state;
    }
    
    private String generatePassword() {
        char[] passwordChar = new char[20];
        for (int i = 0; i < passwordChar.length; i++) {
            byte b = (byte) (Math.random() * 122);
            while (b < 65 || (b > 90 && b < 97) || b > 122) {
                b = (byte) (Math.random() * 122);
            }
            passwordChar[i] = (char)b;
        }
        String pass = new String(passwordChar);
        return pass;
    }
    
    public void checkPassword(String check) throws BusinessLogicException{
        if(!password.equals(check)){
            throw new BusinessLogicException("Reservation password and HTTP header \"X-Password\" doesnt match.");
        }
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package airservice.util;

import airservice.db.AOSMemoryDB;
import airservice.entity.destination.Destination;
import airservice.entity.flight.FlightEntity;
import airservice.entity.reservation.ReservationEntity;
import airservice.entity.StateChoices;
import airservice.exceptions.BadRequestException;
import airservice.entity.destination.DestinationInput;
import airservice.entity.flight.FlightInput;
import airservice.entity.reservation.ReservationOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Tomas "sarzwest" Jiricek
 */
public class Mapper {
    
    public static StateChoices getState(String stat){
        if(stat.equals("NEW")){
            return StateChoices.NEW;
        }else if(stat.equals("CANCELED")){
            return StateChoices.CANCELED;
        }else{
            return StateChoices.PAID;
        }
    }
    
    public static String getState(StateChoices stat){
        if(stat == StateChoices.NEW){
            return "NEW";
        }else if(stat==StateChoices.CANCELED){
            return "CANCELED";
        }else{
            return "PAID";
        }
    }
}

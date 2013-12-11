/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package airservice.logic;

import airservice.db.AOSMemoryDB;
import airservice.entity.flight.FlightEntity;
import airservice.entity.reservation.ReservationEntity;
import airservice.entity.StateChoices;
import java.util.Date;
import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;

/**
 *
 * @author Tomas "sarzwest" Jiricek
 */
@Stateless
public class TimerBean {
    
    public long TIME_OUT = 7200000;//2 hodiny
//    public long TIME_OUT = 240;
    public long reservationId;
    
    @Resource
    TimerService timerService;

    public void startTimer(long reservationId) {
        this.reservationId = reservationId;
        Timer timer = timerService.createTimer(TIME_OUT, null);
    }
    
    @Timeout
    public void deleteReservation(){
        ReservationEntity reservation = AOSMemoryDB.getReservationById(reservationId);
        if(reservation.getState() == StateChoices.NEW){
            reservation.setState(StateChoices.CANCELED);
            FlightEntity flight = reservation.getFlight();
            flight.deleteSeats(reservation.getSeats());
        }
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package airservice.entity.reservation;

/**
 *
 * @author Tomas "sarzwest" Jiricek
 */
public abstract class Reservation {
    private int seats;

    public Reservation() {
    }

    public Reservation(int seats) {
        this.seats = seats;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }
    
}

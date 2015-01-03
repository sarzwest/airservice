/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package airservice.entity.reservation;

/**
 *
 * @author Tomas "sarzwest" Jiricek
 */
public class ReservationInput extends Reservation{
    
    private long flight;//při vytváření se posílá pouze id letu
    private String password;//náhodně vygenerované heslo, které klient používá pro přístup k rezervaci a jejím úpravám
    private String state;//stav rezervace (new, canceled, paid)

    public ReservationInput() {
    }

    public ReservationInput(long flight, String password, String state, int seats) {
        super(seats);
        this.flight = flight;
        this.password = password;
        this.state = state;
    }
    

    public void setFlight(long flight) {
        this.flight = flight;
    }

    public long getFlight() {
        return flight;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

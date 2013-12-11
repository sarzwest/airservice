/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package airservice.entity.flight;

/**
 *
 * @author Tomas "sarzwest" Jiricek
 */
public abstract class Flight {
    private float distance;
    private String name; //unique
    private float price;
    private int seats;

    public Flight() {
    }

    public Flight(float distance, String name, float price, int seats) {
        this.distance = distance;
        this.name = name;
        this.price = price;
        this.seats = seats;
    }

    public float getDistance() {
        return distance;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public int getSeats() {
        return seats;
    }

    public void setDistance(float distance) {
        this.distance = distance;
        this.price = distance * 10;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }
    
}

package airservice.db;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import airservice.entity.destination.Destination;
import airservice.entity.flight.FlightEntity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author lubos
 */
public class AOSMemoryDBFlightQueryset extends ArrayList<FlightEntity> {

    AOSMemoryDBFlightQueryset(Collection<FlightEntity> values) {
        super(values);
    }

    public AOSMemoryDBFlightQueryset orderBy(String name) {
        String direction = "asc";
        if (name != null && name.contains(":")) {
            String[] params = name.split(":", 2);
            direction = params[1];
            name = params[0];
        }
        Collections.sort(this, new AOSMemoryDBFlightQueryset.OrderingComparator(name, direction));
        return this;
    }

     class OrderingComparator implements Comparator<FlightEntity> {

        private String field;
        private String direction;

        public OrderingComparator(String field, String direction) {
            this.field = field;
            this.direction = direction;
        }

        @Override
        public int compare(FlightEntity o1, FlightEntity o2) {
            int ord = direction.equals("asc") ? 1 : -1;
            if (this.field != null && this.field.endsWith("name")) {
                return o1.getName().compareTo(o2.getName()) * ord;
            }else if (this.field != null && this.field.endsWith("dateOfDeparture")) {
                return o1.getDateOfDeparture().compareTo(o2.getDateOfDeparture()) * ord;
            }
            
            return (int) (o1.getId() - o2.getId()) * ord;
        }
    }
}
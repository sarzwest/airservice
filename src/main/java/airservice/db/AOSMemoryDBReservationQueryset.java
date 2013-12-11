package airservice.db;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import airservice.entity.destination.Destination;
import airservice.entity.reservation.ReservationEntity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author lubos
 */
public class AOSMemoryDBReservationQueryset extends ArrayList<ReservationEntity> {

    AOSMemoryDBReservationQueryset(Collection<ReservationEntity> values) {
        super(values);
    }

    public AOSMemoryDBReservationQueryset orderBy(String name) {
        String direction = "asc";
        if (name != null && name.contains(":")) {
            String[] params = name.split(":", 2);
            direction = params[1];
            name = params[0];
        }
        Collections.sort(this, new AOSMemoryDBReservationQueryset.OrderingComparator(name, direction));
        return this;
    }

     class OrderingComparator implements Comparator<ReservationEntity> {

        private String field;
        private String direction;

        public OrderingComparator(String field, String direction) {
            this.field = field;
            this.direction = direction;
        }

        @Override
        public int compare(ReservationEntity o1, ReservationEntity o2) {
            int ord = direction.equals("asc") ? 1 : -1;
//            if (this.field != null && this.field.endsWith("name")) {
//                return o1.getName().compareTo(o2.getName()) * ord;
//            }
            return (int) (o1.getId() - o2.getId()) * ord;
        }
    }
}
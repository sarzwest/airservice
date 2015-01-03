package airservice.db;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import airservice.entity.destination.DestinationEntity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author lubos
 */
public class AOSMemoryDBDestinationQueryset extends ArrayList<DestinationEntity> {

    AOSMemoryDBDestinationQueryset(Collection<DestinationEntity> values) {
        super(values);
    }

    public AOSMemoryDBDestinationQueryset orderBy(String name) {
        String direction = "asc";
        if (name != null && name.contains(":")) {
            String[] params = name.split(":", 2);
            direction = params[1];
            name = params[0];
        }
        Collections.sort(this, new AOSMemoryDBDestinationQueryset.OrderingComparator(name, direction));
        return this;
    }

    private class OrderingComparator implements Comparator<DestinationEntity> {

        private String field;
        private String direction;

        public OrderingComparator(String field, String direction) {
            this.field = field;
            this.direction = direction;
        }

        @Override
        public int compare(DestinationEntity o1, DestinationEntity o2) {
            int ord = direction.equals("asc") ? 1 : -1;
            if (this.field != null && this.field.endsWith("name")) {
                return o1.getName().compareTo(o2.getName()) * ord;
            }
            return (int) (o1.getId() - o2.getId()) * ord;
        }
    }
}
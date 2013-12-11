/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package airservice.util;

import airservice.entity.flight.FlightOutput;
import java.util.Collection;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Tomas "sarzwest" Jiricek
 */
@XmlRootElement(name = "collection")
public class CollectionWrapper {
    
    Collection<FlightOutput> flight;

    public CollectionWrapper() {
    }

    public CollectionWrapper(Collection<FlightOutput> flight) {
        this.flight = flight;
    }

    @XmlElement(name = "flight")
    public Collection<FlightOutput> getFlight() {
        return flight;
    }

    public void setFlight(Collection<FlightOutput> flight) {
        this.flight = flight;
    }
    
    
}

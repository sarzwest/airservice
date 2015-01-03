/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package airservice.entity.destination;

import airservice.restclient.Destination.DestinationInfoDTO.Location;
import javax.ws.rs.core.UriBuilder;

/**
 *
 * @author Tomas "sarzwest" Jiricek
 */
public class DestinationEntity extends Destination{
    
    private static long idGenerator;
    
    private long id;
    private String uri;
    private Location location;

    public DestinationEntity(String name, UriBuilder ub, Location location) {
        super(name);
        this.id = ++idGenerator;
        this.location = location;
        this.setUri(ub.path(String.valueOf(id)).build().getPath());
    }
    
    /**
     * test constructor, dont use it!!!
     * @param name 
     */
    public DestinationEntity(String name) {
        super(name);
        this.id = ++idGenerator;
    }

    public long getId() {
        return id;
    }

    public String getUri() {
        return uri;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public final void setUri(String uri) {
        this.uri = uri;
    }
}

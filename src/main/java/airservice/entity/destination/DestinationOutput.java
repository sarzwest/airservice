/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package airservice.entity.destination;

import javax.ws.rs.core.UriBuilder;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Tomas "sarzwest" Jiricek
 */
@XmlRootElement(name = "destination")
public class DestinationOutput extends Destination {

    private long id;
    private String uri;

    public DestinationOutput() {
    }
    
    public DestinationOutput(DestinationEntity destination) {
        super(destination.getName());
        this.id = destination.getId();
        this.uri = destination.getUri();
    }

    @XmlElement(required = true)
    @Override
    public String getName() {
        return super.getName();
    }

    public String getUri() {
        return uri;
    }

    public final void setUri(String uri) {
        this.uri = uri;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}

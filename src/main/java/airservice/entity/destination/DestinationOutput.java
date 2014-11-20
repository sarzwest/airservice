/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package airservice.entity.destination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.core.UriBuilder;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import scala.Array;

/**
 *
 * @author Tomas "sarzwest" Jiricek
 */
@XmlRootElement(name = "destination")
public class DestinationOutput extends Destination {

    private long id;
    private String uri;
    private List<Destination> list = new ArrayList<Destination>(Arrays.asList(new Destination[]{new DestinationEntity("a"), new DestinationEntity("b")}));

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
    
    
    
    public List<Destination> getList() {
		return list;
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

	@Override
	public String toString() {
		return "DestinationOutput [id=" + id + ", uri=" + uri + "]";
	}
}

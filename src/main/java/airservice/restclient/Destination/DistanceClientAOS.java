/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package airservice.restclient.Destination;

import airservice.exceptions.SystemException;
import airservice.restclient.Destination.DestinationInfoDTO.Location;
import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import emailsender.consumer.Email;
import printer.service.ByteArrayDataHandler;

/**
 *
 * @author Tomas "sarzwest" Jiricek
 */
public class DistanceClientAOS {
    public static final String RESOURCE_URL = "http://aos.czacm.org/flight-distance";
    
    private Client client;
    private Gson gson;

    public DistanceClientAOS() {
        client = Client.create();
        gson = new Gson();
    }
    
    public float getDistance(Location from, Location to) throws SystemException {
        WebResource webResource = client
                .resource(RESOURCE_URL)
                .queryParam("from", from.getLat() + "," + from.getLng())
                .queryParam("to", to.getLat() + "," + to.getLng());
        ClientResponse response = webResource.accept("application/json")
                .get(ClientResponse.class);
        if (response.getStatus() != 200) {
            throw new SystemException("Cannot get distance from aos.czacm.org. HTTP error code: " + response.getStatus());
        }
        String output = response.getEntity(String.class);
        DistanceDTO distance = gson.fromJson(output, DistanceDTO.class);
        return distance.getLength();
    }
    
    public static void main(String[] args) throws SystemException {
//        Email email = new Email("jiratom", new ByteArrayDataHandler("bla bla".getBytes(), "jmeno"));
//        System.out.println(email.getAttachement());
        Location l1 = new Location();
        l1.setLat(50.0755381f);
        l1.setLng(14.4378005f);
        Location l2 = new Location();
        l2.setLat(49.1950602f);
        l2.setLng(16.6068371f);
        new DistanceClientAOS().getDistance(l1, l2);
    }
}

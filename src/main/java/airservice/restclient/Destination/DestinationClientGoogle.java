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

/**
 *
 * @author Tomas "sarzwest" Jiricek
 */
public class DestinationClientGoogle {
    public static final String RESOURCE_URL = "http://maps.googleapis.com/maps/api/geocode/json";

    private Client client;
    private Gson gson;

    public DestinationClientGoogle() {
        client = Client.create();
        gson = new Gson();
    }

    public Location getLocation(String destination) throws SystemException {
        WebResource webResource = client
                .resource(RESOURCE_URL)
                .queryParam("address", destination).queryParam("sensor", "false");
        ClientResponse response = webResource.accept("application/json")
                .get(ClientResponse.class);
        if (response.getStatus() != 200) {
            throw new SystemException("Cannot get data about " + destination + " from Google.com. HTTP error code: " + response.getStatus());
        }
        String output = response.getEntity(String.class);
        DestinationInfoDTO fromJson = gson.fromJson(output, DestinationInfoDTO.class);
        return fromJson.getResults().get(0).getGeometry().getLocation();
    }

    public static void main(String[] args) throws SystemException {
        new DestinationClientGoogle().getLocation("Praha");
    }
}

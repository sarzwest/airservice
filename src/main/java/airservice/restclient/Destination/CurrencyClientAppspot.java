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
public class CurrencyClientAppspot {
    public static final String RESOURCE_URL = "http://rate-exchange.appspot.com/currency";

    private Client client;
    private Gson gson;

    public CurrencyClientAppspot() {
        client = Client.create();
        gson = new Gson();
    }

    public float getRate(String from, String to) throws SystemException {
        WebResource webResource = client
                .resource(RESOURCE_URL)
                .queryParam("from", from).queryParam("to", to);
        ClientResponse response = webResource.accept("application/json")
                .get(ClientResponse.class);
        if (response.getStatus() != 200) {
            throw new SystemException("Cannot get currency rate from Appspot.com. HTTP error code: " + response.getStatus());
        }
        String output = response.getEntity(String.class);
        CurrencyRateDTO fromJson = gson.fromJson(output, CurrencyRateDTO.class);
        float rate = fromJson.getRate();
        if(rate == 0.0){
            throw new SystemException("Something went wrong. Probably X-Currency header carried wrong data.");
        }
        return rate;
    }

    public static void main(String[] args) throws SystemException {
        new CurrencyClientAppspot().getRate("CZK", "EUR");
    }
}

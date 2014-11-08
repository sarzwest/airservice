/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package airservice.resources.tests;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Tomas "sarzwest" Jiricek
 */
public class SubResource {
    
    String text;

    public SubResource() {
    }

    public SubResource(String text) {
        this.text = text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSubResource(){
        return Response.status(Response.Status.OK).entity("toto je subresource").build();
    }

    public String getText() {
        return text;
    }
}

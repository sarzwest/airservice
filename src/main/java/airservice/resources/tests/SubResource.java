/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package airservice.resources.tests;

import airservice.entity.destination.DestinationInput;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Tomas "sarzwest" Jiricek
 */
//@Api(value = "", description = "Testovaci sub resource class")
//@Produces(MediaType.APPLICATION_XML)
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

//    @ApiOperation(value = "Some info here",
//            response = SubResource.class)
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    //@Produces(MediaType.APPLICATION_JSON)
    public Response getSubResource(DestinationInput d) {
        return Response.status(Response.Status.OK).entity("toto je subresource").build();
    }

    @GET
    @Path("getsomething")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSomething() {
        return Response.status(Response.Status.OK).entity("toto je subresource 2").build();
    }

    public String getText() {
        return text;
    }
}

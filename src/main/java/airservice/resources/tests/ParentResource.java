/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package airservice.resources.tests;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Tomas "sarzwest" Jiricek
 */
public abstract class ParentResource extends Parent2Resource{
    
    @GET
    @Path("inheritance")
    public Response getInheritance(){
        return Response.ok("toto je inheritance", MediaType.APPLICATION_JSON).build();
    }
}

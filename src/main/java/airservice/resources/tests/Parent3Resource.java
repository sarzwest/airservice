/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package airservice.resources.tests;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 *
 * @author Tomas "sarzwest" Jiricek
 */
public interface Parent3Resource {
 
    @GET
    @Path("inheritanceinterface5")
    public Response getInheritance5();
}

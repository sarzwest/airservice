/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package airservice.resources;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.container.TimeoutHandler;
import javax.ws.rs.core.Response;

/**
 *
 * @author Tomas "sarzwest" Jiricek
 */
@Path("ejbtest")
@Stateless
public class EJBTestResource {
    
//    @GET
//    @Path("async")
//    @Asynchronous
//    public void async(@Suspended final AsyncResponse ar) {
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(EJBTestResource.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        ar.resume("Asynchronous ejb method done!");
//    }
}

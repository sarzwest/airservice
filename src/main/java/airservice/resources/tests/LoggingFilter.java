/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airservice.resources.tests;

import java.io.IOException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Tomas "sarzwest" Jiricek
 */
@Provider
public class LoggingFilter implements ContainerRequestFilter, ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext crc) throws IOException {
        System.out.println("Request: " + crc.getMethod() + " " + crc.getUriInfo().getPath());
    }

    @Override
    public void filter(ContainerRequestContext crc, ContainerResponseContext crc1) throws IOException {
        System.out.println("Response: " + crc1.getEntity());
    }

}

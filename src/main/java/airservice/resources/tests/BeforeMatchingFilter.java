/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package airservice.resources.tests;

import java.io.IOException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Tomas "sarzwest" Jiricek
 */
@Provider
@PreMatching
public class BeforeMatchingFilter implements ContainerRequestFilter{

    @Override
    public void filter(ContainerRequestContext crc) throws IOException {
        System.out.println("Before matching filter: ");
    }
    
}

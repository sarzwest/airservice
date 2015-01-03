/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package airservice.resources.tests;

import java.io.IOException;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Tomas "sarzwest" Jiricek
 */
@Provider
@PreMatching
@SessionScoped
public class BeforeMatchingFilter implements ContainerRequestFilter, Serializable{

    @Context
    private transient HttpServletRequest servletRequest;
    private int s;
    @Override
    public void filter(ContainerRequestContext crc) throws IOException {
        System.out.println("Before matching filter: ");
        HttpSession session = servletRequest.getSession(true);
        Integer attribute = (Integer)session.getAttribute("key");
        if(attribute == null){
            attribute = -1;
        }
        System.out.println(s);
        s = attribute;
        System.out.println(s);
        session.setAttribute("key", attribute + 1);
    }
    
}

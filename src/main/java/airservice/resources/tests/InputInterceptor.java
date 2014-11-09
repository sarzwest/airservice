/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airservice.resources.tests;

import java.io.IOException;
import java.util.Enumeration;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.ReaderInterceptorContext;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;

/**
 *
 * @author Tomas "sarzwest" Jiricek
 */
@Provider
public class InputInterceptor implements ReaderInterceptor {

    @Override
    public Object aroundReadFrom(ReaderInterceptorContext ric) throws IOException, WebApplicationException {
        System.out.println("Input type: " + ric.getType());
        return ric.proceed();
    }
}

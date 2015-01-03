/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airservice.resources.tests;

import java.io.IOException;
import javax.annotation.Priority;
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
@Priority(1)
public class OutputInterceptor implements WriterInterceptor {

    @Override
    public void aroundWriteTo(WriterInterceptorContext wic) throws IOException, WebApplicationException {
        System.out.println("Output");
        wic.proceed();
    }

}

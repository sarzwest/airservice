/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package airservice.resources.tests;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Tomas "sarzwest" Jiricek
 */
@Provider
public class MyExceptionMapper implements ExceptionMapper<MyException>{

    @Override
    public Response toResponse(MyException exception) {
        return Response.status(Response.Status.BAD_REQUEST).entity("hej kamo toto je vyjimka z excetion mapperu").type(MediaType.APPLICATION_JSON).build();
    }
    
}

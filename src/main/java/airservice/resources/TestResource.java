/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airservice.resources;

import airservice.entity.destination.DestinationOutput;
import airservice.resources.tests.DestOutChild;
import airservice.resources.tests.ExceptionEntity;
import airservice.resources.tests.MyException;
import airservice.resources.tests.Parent2Interface;
import airservice.resources.tests.ParentInterface;
import airservice.resources.tests.ParentResource;
import airservice.resources.tests.SubResource;
import airservice.resources.tests.Wrapper;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Encoded;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ContextResolver;

/**
 * REST Web Service
 *
 * @author Tomas "sarzwest" Jiricek
 */
@Path("/rest/test")
public class TestResource extends ParentResource implements ParentInterface, Parent2Interface{

    @HeaderParam("X-user")
    String user;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of TestResource
     */
    public TestResource() {
    }

    /**
     *
     * @param text kdyz bude obsahovat mezeru, tak se nedekoduje na mezeru, ale
     * bude tam %20. Obdobne se nebude dekodovat zbytek
     * @return
     */
    @GET
    @Path(value = "/encoded")
    @Produces(MediaType.APPLICATION_JSON)
    public Response encoded(@QueryParam("text") @Encoded String text) {
        System.out.println(text);
        return Response.status(Response.Status.OK).entity("je to ok").build();
    }

    /**
     * Anotovany field je nasetovany
     */
    @GET
    @Path(value = "/annotatedfield")
    @Produces(MediaType.APPLICATION_JSON)
    public Response annotatedField() {
        System.out.println(user);
        return Response.status(Response.Status.OK).entity("je to ok").build();
    }

    /**
     * GenericEntity priklad. Pouziva se, protoze JAX-RS nemuze korektne
     * zachytit Listy JAXB objektu
     */
    @GET
    @Path(value = "/collection")
    @Produces(MediaType.APPLICATION_XML)
    public Response collection() {
        List<DestinationOutput> aList = new ArrayList<DestinationOutput>();
        DestinationOutput dest = new DestinationOutput();
        dest.setName("Praha");
        aList.add(dest);
        dest = new DestinationOutput();
        dest.setName("Pv City");
        aList.add(dest);
        GenericEntity<List<DestinationOutput>> genericEntity = new GenericEntity<List<DestinationOutput>>(aList) {
        };
        return Response.status(Response.Status.OK).entity(genericEntity).build();
    }

    /**
     * Tady jsem si hral s generiky. Trida wrapper v sobe obsahuje generika.
     *
     * @param incomeWrap je jedno jak je trida tvorena generiky, nakonec je
     * presne definovana v parametru metody.
     * @return trosku problem, mozna delat tak, jak to ma Miredot.
     */
    @POST
    @Path(value = "/rawtype")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Wrapper rawType(Wrapper<DestOutChild> incomeWrap) {
        Wrapper<DestinationOutput> wrapper = new Wrapper<DestinationOutput>(new DestOutChild());
        return wrapper;
    }

    /**
     * Metoda pouzivajici generika. Muze konzumovat jakykoliv typ, vsechno je
     * totiz serializovano do likedhashmapy
     * Priklad volani:
     * 
    [
       {
           "destO":
           {
               "varInDestChild": "inDestChild"
           },
           "varInWrapper": "inWrapper"
       },
       {
           "destO":
           {
               "varInDestChild": "inDestChild2"
           },
           "varInWrapper": "inWrapper"
       }
    ]
     * @param <T>
     * @param src
     * @return
     */
    @POST
    @Path(value = "/rawtype2")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public <T> List<T> rawType2(List<T> src) {
        List<T> list = new ArrayList<T>();
        for (T t : src) {
            list.add(t);
        }
        return list;
    }

    @POST
    @Path(value = "/exception")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Wrapper exception(Wrapper<DestOutChild> incomeWrap) {
        if (incomeWrap.varInWrapper.equals("a")) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN).entity("exception text").build());
        }
        if (incomeWrap.varInWrapper.equals("b")) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).type(MediaType.APPLICATION_JSON).entity(new ExceptionEntity()).build());
        }
        Wrapper<DestinationOutput> wrapper = new Wrapper<DestinationOutput>(new DestOutChild());
        return wrapper;
    }
    
    //@GET
    @Path("subresourcelocator")
    @Produces(MediaType.APPLICATION_JSON)
    public SubResource getSubResource(){
        return new SubResource("tadaaa");
    }
    
    /**
     * Metoda jak v super class tak v interface, ale bere se ze superclass
     * @return 
     */
    @Override
    public Response getInheritance(){
        return Response.ok("toto je inheritance", MediaType.APPLICATION_JSON).build();
    }

    /**
     * Metoda v interface
     * @return 
     */
    @Override
    public Response getInheritance2() {
        return Response.ok("toto je inheritance", MediaType.APPLICATION_JSON).build();
    }
    
    /**
     * Implementovano ze dvou interfacu na stejne urovni - nelze, muselo se zakomentovat v jednom
     * @return 
     */
    @Override
    public Response getInheritance3() {
        return Response.ok("toto je inheritance", MediaType.APPLICATION_JSON).build();
    }
    
    /**
     * Implementovano ze dvou interfacu kazdy na jine urovni - nelze implementovat interface interfacem
     * @return 
     */
    /*@Override
    public Response getInheritance4() {
        return Response.ok("toto je inheritance", MediaType.APPLICATION_JSON).build();
    }*/
    
    /**
     * 
     * @return 
     */
//    @Override
//    public Response getInheritance5() {
//        return Response.ok("toto je inheritance", MediaType.APPLICATION_JSON).build();
//    }
    
    @GET
    @Path("exceptionmapper")
    public Response exceptionMapper() throws MyException{
        throw new MyException();
    }
}

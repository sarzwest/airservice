/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airservice.resources;

import airservice.entity.bean.FromStringBean;
import airservice.entity.bean.ParamConverBean;
import airservice.entity.bean.StringConstBean;
import airservice.entity.bean.ValueOfBean;
import airservice.entity.destination.DestinationOutput;
import airservice.resources.tests.*;
import com.sun.jersey.api.client.ClientResponse.Status;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.ws.rs.*;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.container.TimeoutHandler;
import javax.ws.rs.core.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

//import javax.validation.constraints.Pattern;

/**
 * REST Web Service
 *
 * @author Tomas "sarzwest" Jiricek
 */
@Path("/rest/test/{pathparam}")
@Produces("text/plain")
public class TestResource extends Class1 implements IFace2, ParentInterface,
        Parent2Interface {

    @DefaultValue("defvaluematrix")
    @MatrixParam("matrixparam")
    String matrix;
    @QueryParam("queryparam")
    String query;
    @PathParam("pathparam")
    //zakomentovano jinak to nefunguje, aby fungovalo musim nastavit treba takto: @Path("/rest/test/{pathparam}")
    int path;
    @CookieParam("cookieparam")
    String cookie;
    @HeaderParam("headerparam")
    String header;
    @FormParam("formparam")
    String form;

    @DefaultValue("string const def value")
    @QueryParam("stringconstbean")
    StringConstBean stringConstBean;
    @QueryParam("paramconverbean")
    ParamConverBean paramConverBean;
    @QueryParam("valueofbean")
    ValueOfBean valueOfBean;
    @QueryParam("fromstringbean")
    FromStringBean fromStringBean;

    /**listbean=a&listbean=okoni*/
    @DefaultValue("string list def value")
    @QueryParam("listbean")
    List<FromStringBean> listbean;
    /**setbean=a&setbean=okoni*/
    @QueryParam("setbean")
    Set<FromStringBean> setbean;
    /**sortedsetbean=a&sortedsetbean=okoni*/
    @QueryParam("sortedsetbean")
    SortedSet<FromStringBean> sortedsetbean;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of TestResource
     */
    public TestResource() {
    }

    /**
     * @param text kdyz bude obsahovat mezeru, tak se nedekoduje na mezeru, ale
     *             bude tam %20. Obdobne se nebude dekodovat zbytek
     * @return
     */
    @GET
    @POST
    @Path(value = "/encoded")
    @Produces(MediaType.APPLICATION_JSON)
    public Response encoded(@QueryParam("text") @Encoded String text, @QueryParam("fromstringbeanparam")FromStringBean f) {
        System.out.println(text);
        for(FromStringBean fsb: listbean){
            System.out.println(fsb.toString());
        }
        return Response.status(Response.Status.OK).entity("je to ok").build();
    }

    /**
     * Anotovany field je nasetovany, nastaven response object
     */
    @GET
    @Path(value = "/annotatedfield")
    @Produces(MediaType.TEXT_PLAIN)
    public Response annotatedField() {
        System.out.println(header);
        return Response.status(Response.Status.OK).entity("je to ok").build();
    }

    /**
     *
     */
    @POST
    @Path("entityparam/{id}")
    public Response newDestination(DestOutChild dest, @PathParam("id") String id) {
        return Response.ok().build();
    }

    /**
     * GenericEntity priklad. Pouziva se, protoze JAX-RS nemuze korektne
     * zachytit Listy JAXB objektu
     */
    @GET
    @Path(value = "/retgenericentity")
    @Produces(MediaType.APPLICATION_XML)
    public GenericEntity<List<DestinationOutput>> collection() {
        List<DestinationOutput> aList = new ArrayList<DestinationOutput>();
        DestinationOutput dest = new DestinationOutput();
        dest.setName("Praha");
        aList.add(dest);
        dest = new DestinationOutput();
        dest.setName("Pv City");
        aList.add(dest);
        GenericEntity<List<DestinationOutput>> genericEntity = new GenericEntity<List<DestinationOutput>>(
                aList) {
        };
        return genericEntity;
    }

    /**
     * Vnorene typy v sobe
     */
    @GET
    @Path(value = "/retgenericentityinnerdoublelist")
    @Produces(MediaType.APPLICATION_JSON)
    public GenericEntity<List<List<DestinationOutput>>> doubleInnerCollection() {
        List<DestinationOutput> aList = new ArrayList<DestinationOutput>();
        DestinationOutput dest = new DestinationOutput();
        dest.setName("Praha");
        aList.add(dest);
        dest = new DestinationOutput();
        dest.setName("Pv City");
        aList.add(dest);
        List<List<DestinationOutput>> outerList = new ArrayList<List<DestinationOutput>>();
        outerList.add(aList);
        GenericEntity<List<List<DestinationOutput>>> genericEntity = new GenericEntity<List<List<DestinationOutput>>>(
                outerList) {
        };
        return genericEntity;
    }

    /**
     * Tady jsem si hral s generiky. Trida wrapper v sobe obsahuje generika.
     *
     * @param incomeWrap je jedno jak je trida tvorena generiky, nakonec je presne
     *                   definovana v parametru metody.
     * @return trosku problem, mozna delat tak, jak to ma Miredot.
     */
    @POST
    @Path(value = "/rawtype")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Wrapper<DestinationOutput> rawType(Wrapper<DestOutChild> incomeWrap) {
        Wrapper<DestinationOutput> wrapper = new Wrapper<DestinationOutput>(
                new DestOutChild());
        return wrapper;
    }

    /**
     * Metoda bere jako parametr kolekci objektu
     */
    @POST
    @Path(value = "/reqlist")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response reqList(List<DestinationOutput> destination) {
        for (DestinationOutput d : destination) {
            System.out.println(d.getName());
        }
        return Response.status(Status.NO_CONTENT).build();
    }

    /**
     * PathParam jako parametr metody
     */
    @GET
    @Path("pathparam/{path}")
    public void getPathParam(@PathParam("path") String path) {
        System.out.println(path);
    }

    /**
     * QueryParam jako parametr metody
     */
    @GET
    @Path("queryparam")
    public void getQueryParam(@QueryParam("q") String q) {
        System.out.println(q);
        System.out.println();
    }

    /**
     * MatrixParam jako parametr metody
     */
    @POST
    @Path("matrixparam")
    public void getMatrixParam(@MatrixParam("m") List<String> m) {
        System.out.println(m);
    }

    /**
     * CookieParam jako parametr metody
     */
    @POST
    @Path("cookieparam")
    public void getCookieParam(@CookieParam("c") String c) {
        System.out.println(c);
    }

    /**
     * HeaderParam jako parametr metody
     */
    @POST
    @Path("headerparam")
    public void getHeaderParam(@HeaderParam("h") String h) {
        System.out.println(h);
    }

    /**
     * FormParam jako parametr metody
     */
    @POST
    @Path("formparam")
    public void getFormParam(@FormParam("f") String f) {
        System.out.println(f);
    }

    /**
     * Metoda pouzivajici generika. Muze konzumovat jakykoliv typ, vsechno je
     * totiz serializovano do likedhashmapy Priklad volani:
     * <p/>
     * [ { "destO": { "varInDestChild": "inDestChild" }, "varInWrapper":
     * "inWrapper" }, { "destO": { "varInDestChild": "inDestChild2" },
     * "varInWrapper": "inWrapper" } ]
     *
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
    @Path(value = "/object")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_XML)
    public <T> T object(T src) {
        return src;
    }

    /**
     * Gets the status info
     */
    @GET
    @Path("status")
    public Response getStatus() {
        return Response.status(Status.OK).build();
    }

    /**
     * Gets the status info
     */
    @GET
    @Path("retcontenttype")
    @Produces({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON,
            "application/xml"})
    public Response getContentType() {
        return Response.status(Status.OK).build();
    }

    /**
     * Return type is classic object
     */
    @GET
    @Path("retclassictype")
    @Produces({MediaType.APPLICATION_ATOM_XML})
    public DestinationOutput getClassicType() {
        DestinationOutput dstO = new DestinationOutput();
        dstO.setId(1234);
        dstO.setName("some name");
        return dstO;
    }

    /**
     * Return type is collection of classic objects
     */
    @GET
    @Path("retlistclassictype")
    @Produces({MediaType.APPLICATION_JSON})
    public List<DestinationOutput> getCollectionClassicTypes() {
        DestinationOutput dstO = new DestinationOutput();
        dstO.setId(1234);
        dstO.setName("some name");
        List<DestinationOutput> list = new ArrayList<DestinationOutput>();
        list.add(dstO);
        list.add(dstO);
        return list;
    }

    /**
     * Metoda vyhazuje WebApplicationException
     *
     * @param incomeWrap
     * @return
     */
    @POST
    @Path(value = "/exception")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Wrapper<DestinationOutput> exception(Wrapper<DestOutChild> incomeWrap) {
        if (incomeWrap.varInWrapper.equals("a")) {
            throw new WebApplicationException(Response
                    .status(Response.Status.BAD_REQUEST)
                    .type(MediaType.TEXT_PLAIN).entity("exception text")
                    .build());
        }
        if (incomeWrap.varInWrapper.equals("b")) {
            throw new WebApplicationException(Response
                    .status(Response.Status.BAD_REQUEST)
                    .type(MediaType.APPLICATION_JSON)
                    .entity(new ExceptionEntity()).build());
        }
        Wrapper<DestinationOutput> wrapper = new Wrapper<DestinationOutput>(
                new DestOutChild());
        return wrapper;
    }

    /**
     * Metoda vraci subresourcelocator ktery vraci response
     *
     * @return
     */
    //@GET - kdyz tu je GET tak to neni subresource locator
    @Path("subresourcelocator")
    //@Produces(MediaType.APPLICATION_JSON) - toto se vubec nebere v potaz
    public SubResource getSubResource() {
        return new SubResource("tadaaa");
    }

    /**
     * Metoda jak v super class tak v interface, ale bere se ze superclass
     *
     * @return
     */
    // @ApiOperation(value =
    // "Metoda jak v super class tak v interface, ale bere se ze superclass")
    @Override
    public Response getInheritance() {
        return Response.ok("toto je inheritance", MediaType.APPLICATION_JSON)
                .build();
    }

    /**
     * Metoda v interface
     *
     * @return
     */
    // @ApiOperation(value = "Metoda v interface")
    @Override
    public Response getInheritance2() {
        return Response.ok("toto je inheritance", MediaType.APPLICATION_JSON)
                .build();
    }

    /**
     * Implementovano ze dvou interfacu na stejne urovni - nelze, muselo se
     * zakomentovat v jednom
     *
     * @return
     */
    // @ApiOperation(value =
    // "Implementovano ze dvou interfacu na stejne urovni - nelze, muselo se zakomentovat v jednom")
    @Override
    public Response getInheritance3() {
        return Response.ok("toto je inheritance", MediaType.APPLICATION_JSON)
                .build();
    }

    /**
     * Implementovano ze dvou interfacu kazdy na jine urovni - nelze
     * implementovat interface interfacem
     *
     * @return
     */
    /*
     * @Override public Response getInheritance4() { return
	 * Response.ok("toto je inheritance", MediaType.APPLICATION_JSON).build(); }
	 */

    /**
     * @return
     */
    // @ApiOperation(value = "Inheritance")
    @Override
    public Response getInheritance5() {
        return Response.ok("toto je inheritance", MediaType.APPLICATION_JSON)
                .build();
    }

    @GET
    @Path("exceptionmapper")
    public Response exceptionMapper() throws MyException {
        throw new MyException();
    }

    //@ApiOperation(value = "Inheritance")
    @Override
    public Response foo() {
        return Response.ok("toto je inheritance", MediaType.APPLICATION_JSON)
                .build();
    }

    /**
     * Zkouska interceptoru
     *
     * @return
     */
    @GET
    @Path("compressed")
    @MyCompress
    public Response getCompressed() {
        return Response.ok("Called named interceptor for compress response",
                MediaType.TEXT_PLAIN).build();
    }

    /**
     * Prichozi validace Je potreba posilat hlavicku Accept: application/json
     *
     * @param number
     * @return
     */
    @GET
    @Path("valid/qp")
    public Response validateParam(
            @Pattern(regexp = "[0-9]{2}", message = "Parameter must be a valid number") @QueryParam("twodigitnumber") String number) {
        return Response.ok("yes it is two digit number", MediaType.TEXT_PLAIN)
                .build();
    }

    /**
     * Odchozi validace
     *
     * @param number
     * @return
     */
    @GET
    @Path("valid/retval")
    @Valid
    @Pattern(regexp = "[0-9]{2}", message = "Parameter must be a valid number")
    public String validateParam2(@QueryParam("twodigitnumber") String number) {
        return number;
    }

    /**
     * { "nullableInput": "", "firstName":"Tomas", "lastName":"Jiricek",
     * "nullableOutput":"b" }
     *
     * @param user
     * @return
     * @Valid zapina validaci na vsech fieldech obsahujici omezeni
     * @InputUserValidation zapina custom validaci
     * @OutputUserValidation zapina custom validaci
     */
    @POST
    @Path("valid/reqent")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Valid
    @OutputUserValidation
    public UserEntity validateReqEntity(
            @Valid @InputUserValidation UserEntity user) {
        user.setNullableOutput(null);
        return user;
    }

    /**
     * Asynchronni volani
     *
     * @param ar
     */
    @GET
    @Path("async")
    public void async(@Suspended final AsyncResponse ar) {
        ar.setTimeoutHandler(new TimeoutHandler() {
            @Override
            public void handleTimeout(AsyncResponse ar) {
                ar.resume(Response.status(Response.Status.SERVICE_UNAVAILABLE)
                        .entity("Operation timed out -- please try again")
                        .build());
            }
        });
        ar.setTimeout(10, TimeUnit.SECONDS);

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(TestResource.class.getName()).log(
                            Level.SEVERE, null, ex);
                }
                System.out.println("Done");
                ar.resume("Cau kamo");
            }
        });
    }
}

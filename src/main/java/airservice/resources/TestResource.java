/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airservice.resources;

import airservice.entity.destination.DestinationOutput;
import airservice.resources.tests.Class1;
import airservice.resources.tests.DestOutChild;
import airservice.resources.tests.ExceptionEntity;
import airservice.resources.tests.IFace2;
import airservice.resources.tests.IFace3;
import airservice.resources.tests.InputUserValidation;
import airservice.resources.tests.MyCompress;
import airservice.resources.tests.MyException;
import airservice.resources.tests.OutputUserValidation;
import airservice.resources.tests.Parent2Interface;
import airservice.resources.tests.ParentInterface;
import airservice.resources.tests.ParentResource;
import airservice.resources.tests.SubResource;
import airservice.resources.tests.UserEntity;
import airservice.resources.tests.Wrapper;

import com.sun.jersey.api.client.ClientResponse.Status;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
//import javax.validation.constraints.Pattern;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.Encoded;
import javax.ws.rs.FormParam;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.container.TimeoutHandler;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ContextResolver;

import org.jboss.resteasy.spi.validation.ValidateRequest;

/**
 * REST Web Service
 * 
 * @author Tomas "sarzwest" Jiricek
 */
@Api(value = "/rest/test", description = "Testovaci resource class")
@Path("/rest/test/")
public class TestResource extends Class1 implements IFace2, ParentInterface,
		Parent2Interface {

	@MatrixParam("matrixparam")
	String matrix;
	@QueryParam("queryparam")
	String query;
	// @PathParam("pathparam") zakomentovano jinak to nefunguje
	String path;
	@CookieParam("cookieparam")
	String cookie;
	@HeaderParam("headerparam")
	String header;
	@FormParam("formparam")
	String form;

	@Context
	private UriInfo context;

	/**
	 * Creates a new instance of TestResource
	 */
	public TestResource() {
	}

	/**
	 * 
	 * @param text
	 *            kdyz bude obsahovat mezeru, tak se nedekoduje na mezeru, ale
	 *            bude tam %20. Obdobne se nebude dekodovat zbytek
	 * @return
	 */
	@ApiOperation(value = "nedekoduje url a query string")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "status OK", response = String.class) })
	@GET
	@Path(value = "/encoded")
	@Produces(MediaType.APPLICATION_JSON)
	public Response encoded(@QueryParam("text") @Encoded String text) {
		System.out.println(text);
		return Response.status(Response.Status.OK).entity("je to ok").build();
	}

	/**
	 * Anotovany field je nasetovany, nastaven response object
	 */
	@ApiOperation(value = "Anotovany field je nasetovany", response = String.class)
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
	@ApiOperation("entity parameter test")
	@POST
	@Path("entityparam/{id}")
	public Response newDestination(DestOutChild dest, @PathParam("id") String id) {
		return Response.ok().build();
	}

	/**
	 * GenericEntity priklad. Pouziva se, protoze JAX-RS nemuze korektne
	 * zachytit Listy JAXB objektu
	 */
	@ApiOperation(value = "GenericEntity priklad. Pouziva se, protoze JAX-RS nemuze korektne zachytit Listy JAXB objektu"
			, responseContainer = "List", response = DestinationOutput.class)
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
		GenericEntity<List<DestinationOutput>> genericEntity = new GenericEntity<List<DestinationOutput>>(
				aList) {
		};
		return Response.status(Response.Status.OK).entity(genericEntity)
				.build();
	}

	/**
	 * Tady jsem si hral s generiky. Trida wrapper v sobe obsahuje generika.
	 * 
	 * @param incomeWrap
	 *            je jedno jak je trida tvorena generiky, nakonec je presne
	 *            definovana v parametru metody.
	 * @return trosku problem, mozna delat tak, jak to ma Miredot.
	 */
	@ApiOperation(value = "Tady jsem si hral s generiky. Trida wrapper v sobe obsahuje generika")
	@POST
	@Path(value = "/rawtype")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Wrapper rawType(Wrapper<DestOutChild> incomeWrap) {
		Wrapper<DestinationOutput> wrapper = new Wrapper<DestinationOutput>(
				new DestOutChild());
		return wrapper;
	}
	
	/**
	 * Metoda bere jako parametr kolekci objektu
	 */
	@ApiOperation(value = "Metoda bere jako parametr kolekci objektu")
	@POST
	@Path(value = "/reqlist")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response regList(@ApiParam(allowMultiple = true)List<DestinationOutput> destination) {
		for (DestinationOutput d : destination) {
			System.out.println(d.getName());
		}
		return Response.status(Status.NO_CONTENT).build();
	}
	
	/**
	 * PathParam jako parametr metody
	 */
	@ApiOperation(value = "PathParam jako parametr metody")
	@GET
	@Path("pathparam/{path}")
	public void getPathParam(@PathParam("path")String path){
		System.out.println(path);
	}
	
	/**
	 * QueryParam jako parametr metody
	 */
	@ApiOperation(value = "QueryParam jako parametr metody")
	@GET
	@Path("queryparam")
	public void getQueryParam(@QueryParam("q")String q){
		System.out.println(q);
	}
	
	/**
	 * MatrixParam jako parametr metody
	 */
	@ApiOperation(value = "MatrixParam jako parametr metody")
	@POST
	@Path("matrixparam")
	public void getMatrixParam(@MatrixParam("m")List<String> m){
		System.out.println(m);
	}

	/**
	 * Metoda pouzivajici generika. Muze konzumovat jakykoliv typ, vsechno je
	 * totiz serializovano do likedhashmapy Priklad volani:
	 * 
	 * [ { "destO": { "varInDestChild": "inDestChild" }, "varInWrapper":
	 * "inWrapper" }, { "destO": { "varInDestChild": "inDestChild2" },
	 * "varInWrapper": "inWrapper" } ]
	 * 
	 * @param <T>
	 * @param src
	 * @return
	 */
	@ApiOperation(value = "Metoda pouzivajici generika. Muze konzumovat jakykoliv typ, vsechno je"
			+ " totiz serializovano do likedhashmapy")
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

	/**
	 * Metoda vyhazuje WebApplicationException
	 * 
	 * @param incomeWrap
	 * @return
	 */
	@ApiOperation(value = "Metoda vyhazuje WebApplicationException")
	@POST
	@Path(value = "/exception")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Wrapper exception(Wrapper<DestOutChild> incomeWrap) {
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
	// @ApiOperation(value =
	// "Metoda vraci subresourcelocator ktery vraci response")
	// @GET
	@Path("subresourcelocator")
	@Produces(MediaType.APPLICATION_JSON)
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
	 * 
	 * @return
	 */
	// @ApiOperation(value = "Inheritance")
	@Override
	public Response getInheritance5() {
		return Response.ok("toto je inheritance", MediaType.APPLICATION_JSON)
				.build();
	}

	@ApiOperation(value = "Vyjimka mapovana na response pomoci exception mapper")
	@GET
	@Path("exceptionmapper")
	public Response exceptionMapper() throws MyException {
		throw new MyException();
	}

	// @ApiOperation(value = "Inheritance")
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
	@ApiOperation(value = "Zkouska interceptoru")
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
	@ApiOperation(value = "Prichozi validace Je potreba posilat hlavicku Accept: application/json")
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
	 * @Valid zapina validaci na vsech fieldech obsahujici omezeni
	 * @InputUserValidation zapina custom validaci
	 * @OutputUserValidation zapina custom validaci
	 * @param user
	 * @return
	 */
	@ApiOperation(value = "@Valid zapina validaci na vsech fieldech obsahujici omezeni")
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
	@ApiOperation(value = "Asynchronni volani")
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

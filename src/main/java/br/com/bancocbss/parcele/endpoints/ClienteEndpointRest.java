package br.com.bancocbss.parcele.endpoints;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Logger;

import br.com.bancocbss.parcele.model.Cliente;
import br.com.bancocbss.parcele.model.viewobject.ClienteVO;
import br.com.bancocbss.parcele.service.ClienteService;

@Path("cliente")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class ClienteEndpointRest {

	@Inject
	private Logger log;

	@Inject
	private ClienteService clienteService;

	@GET
	@Path("/hello")
	@Produces(MediaType.APPLICATION_JSON)
	public JsonArray sayHello() {
		log.info("log: sayHello");
		return Json.createArrayBuilder().add("Hello").build();
		// return "{rest: 'Hello'}";
	}

	@GET
	@Path("all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getClientes() {
		log.info("rest getClientes ");
		List<Cliente> clientes = clienteService.getAll();
		return Response.ok(clientes).build();
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)
	public Cliente lookupClienteById(@PathParam("id") long id) {
		Cliente cliente = clienteService.findByID(id);
		if (cliente == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return cliente;
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/echo")
	public Response EchoCliente(ClienteVO cliente) {
		log.info("echo Cliente");
		System.out.println("Rest getCliente called...");
		cliente.setNome(cliente.getNome() + "..");
		return Response.ok(cliente).build();
	}

	@PUT
	public Response createCliente(Cliente cliente, @Context UriInfo uriInfo) {

		Response.ResponseBuilder builder = null;

		try {
			clienteService.addCliente(cliente);
			// Create an "ok" response

			UriBuilder uribuilder = uriInfo.getAbsolutePathBuilder();
			uribuilder.path(Long.toString(cliente.getId()));

			builder = Response.created(uribuilder.build()).entity(cliente);
		} catch (Exception e) {
			// Handle generic exceptions
			Map<String, String> responseObj = new HashMap<>();
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}

		return builder.build();
	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteCliente(@PathParam("id") long id) {
		Response.ResponseBuilder builder = null;

		try {
			Cliente c = clienteService.findByID(id);
			// Create an "ok" response

			clienteService.remove(c);

			builder = Response.noContent();
		} catch (Exception e) {
			// Handle generic exceptions
			Map<String, String> responseObj = new HashMap<>();
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}

		return builder.build();
	}

	@DELETE
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteAllClientes() {
		Response.ResponseBuilder builder = null;

		try {
			builder = Response.ok(clienteService.removeAll());
		} catch (Exception e) {
			// Handle generic exceptions
			Map<String, String> responseObj = new HashMap<>();
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}

		return builder.build();
	}
}

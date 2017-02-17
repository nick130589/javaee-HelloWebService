package br.com.bancocbss.parcele.rest;

import javax.jws.WebMethod;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import br.com.bancocbss.parcele.model.Cliente;

@Path("/teste")
@Produces(MediaType.APPLICATION_JSON)
public class RestEndpoint {

	final static Logger logger = Logger.getLogger(RestEndpoint.class);

	@GET
	@Path("hello")
	public String sayHello() {
		logger.info("sayHello");
		return "Hello";
	}

	@GET
	@Path("/name/{name}")
	public String sayName(@PathParam("name") String name) {
		logger.info("sayName " + name);
		return "The name is: " + (name) + "!";
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String sayHi(String name) {
		logger.info("sayHi " + name);
		return "Hello, The name is: " + (name) + "!";
	}
	
	@GET
	@Path("/cliente/{name}-{sobrenome}")
	public Cliente getCliente(@PathParam("name") String name, @PathParam("sobrenome")String sobrenome) {
		logger.info("rest getCliente"+name+" "+sobrenome);
		System.out.println("Rest getCliente called...");
		return new Cliente(name, sobrenome);
	}
	

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/echocliente")
	public Cliente EchoCliente(Cliente cliente) {
		logger.info("echo Cliente");
		System.out.println("Webservice getCliente called...");
		cliente.setNome(cliente.getNome()+"..");
		return cliente;
	}
}
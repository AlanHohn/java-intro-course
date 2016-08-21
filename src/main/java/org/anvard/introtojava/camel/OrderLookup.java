package org.anvard.introtojava.camel;

import java.util.Date;
import java.util.Random;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/order")
public class OrderLookup {

	private final Random r = new Random();
	
	@GET
	@Path("/lookup/{id}/{time}")
	@Produces( { "application/json" })
	public OrderInfo lookup(@PathParam("id") Integer id, @PathParam("time") Long time) {
		OrderInfo info = new OrderInfo();
		info.setCustomerName("Johannes Smythe");
		info.setOrderNumber(id);
		info.setTimestamp(new Date(time));
		info.setOrderTotal(r.nextDouble() * 100.0);
		return info;
    }

	@GET
	@Path("/lookup/{id}")
	@Produces( { "application/json" })
	public OrderInfo partialLookup(@PathParam("id") Integer id) {
		OrderInfo info = new OrderInfo();
		info.setCustomerName("Johannes Smythe");
		info.setOrderTotal(r.nextDouble() * 100.0);
		return info;
    }

	@POST
	@Path("/lookup")
	@Consumes( { "application/json" })
	@Produces( { "application/json" })
	public OrderInfo fillIn(OrderInfo info) {
		info.setCustomerName("Johannes Smythe");
		info.setOrderTotal(r.nextDouble() * 100.0);
		return info;
    }

}

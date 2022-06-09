package tp8;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tp8.bean.DriverBean;
import tp8.bean.UserTokenBean;
import tp8.entity.Driver;

@Path("drivers")
public class SampleResource {

	@EJB
	DriverBean driverBean;

	@EJB
	UserTokenBean userToken;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response getAll(@HeaderParam("Authorization") String bearerToken) {
		try {
			var token = bearerToken.split(" ")[1];
			userToken.getForToken(token);
		} catch (Exception e) {
			return Response.status(401).build();
		}

		return Response.ok(driverBean.getAll()).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/add")
	public Response add(@HeaderParam("Authorization") String bearerToken, Driver driver) {
		try {
			var token = bearerToken.split(" ")[1];
			userToken.getForToken(token);
		} catch (Exception e) {
			return Response.status(401).build();
		}

		if (driver.getFirstname() == null || driver.getLastname() == null){
			return Response.status(403).build();
		}

		try {
			driverBean.create(driver);
			return Response.ok().build();
		} catch (Exception e) {
			return Response.status(500).build();
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/update")
	public Response update(@HeaderParam("Authorization") String bearerToken, Driver driver) {
		try {
			var token = bearerToken.split(" ")[1];
			userToken.getForToken(token);
		} catch (Exception e) {
			return Response.status(401).build();
		}

		if (driver.getFirstname() == null || driver.getLastname() == null){
			return Response.status(403).build();
		}

		try {
			driverBean.update(driver);
			return Response.ok(driver).build();
		} catch (Exception e) {
			return Response.status(500).build();
		}
	}

	@DELETE
	@Path("/delete/{id}")
	public Response delete(@HeaderParam("Authorization") String bearerToken, @PathParam("id") Long id) {
		try {
			var token = bearerToken.split(" ")[1];
			userToken.getForToken(token);
		} catch (Exception e) {
			return Response.status(401).build();
		}

		try {
			driverBean.delete(id);
			return Response.ok().build();
		} catch (Exception e) {
			return Response.status(500).build();
		}
	}
}

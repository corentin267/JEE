package tp8;

import tp8.bean.UserBean;
import tp8.entity.User;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

@Path("users")
public class UserResource {

	@EJB
	UserBean userBean;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/add")
	public Response add(User user) {
		if (user.getUsername() == null || user.getPassword() == null){
			return Response.status(400).build();
		}

		try {
			userBean.create(user);
			return Response.ok().build();
		} catch (Exception e) {
			return Response.status(500).build();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/login")
	public Response login(User user) {
		if (user.getUsername() == null || user.getPassword() == null){
			return Response.status(400).build();
		}

		try {
			var token = userBean.login(user);
			return Response.ok(token).build();
		} catch (Exception e) {
			return Response.status(500).build();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/logout")
	public Response logout(@HeaderParam("Authorization") String bearerToken) {
		try {
			var token = bearerToken.split(" ")[1];
			userBean.logout(token);
			return Response.ok().build();
		} catch (Exception e) {
			return Response.status(500).build();
		}
	}
}

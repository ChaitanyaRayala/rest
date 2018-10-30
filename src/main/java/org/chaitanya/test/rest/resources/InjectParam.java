package org.chaitanya.test.rest.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/injectParam")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class InjectParam {

	@GET
	@Path("/annotation")
	public String getValueByAnnotation(@MatrixParam("param") String matrixParam,
										@HeaderParam("authSessionId") String value,
										@CookieParam("name") String name) {
		return "returned matriex param value: "+matrixParam+"header param value is: "+value;
	}
	
	@GET
	@Path("/context")
	public String getValueByContext(@Context UriInfo uriInfo, @Context HttpHeaders httpHeaders) {
		
		String uri = uriInfo.getAbsolutePath().toString();
		String cookies = httpHeaders.getCookies().toString();
		return "uri: "+uri+" cookies: "+cookies;
	}
}

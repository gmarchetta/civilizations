package ar.com.civilizations.rest;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ar.com.civilizations.service.GalaxyService;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("galaxy")
public class GalaxyResource {
	
	@Inject
	private GalaxyService galaxyService;
	
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response initGalaxy() {
    	galaxyService.initModel();
        return Response.ok().build();
    }
}

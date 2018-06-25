package ar.com.civilizations.rest;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ar.com.civilizations.service.GalaxyService;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("galaxy")
public class GalaxyResource {
	
	@Inject
	private GalaxyService galaxyService;
	
    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String initGalaxy() {
    	galaxyService.initModel();
        return "Got it!";
    }
}

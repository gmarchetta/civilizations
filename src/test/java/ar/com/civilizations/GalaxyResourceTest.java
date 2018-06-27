package ar.com.civilizations;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ar.com.civilizations.model.DayWeather;

public class GalaxyResourceTest {

    private HttpServer server;
    private WebTarget target;

    @Before
    public void setUp() throws Exception {
        // start the server
        server = Main.startServer();
        // create the client
        Client c = ClientBuilder.newClient();

        // uncomment the following line if you want to enable
        // support for JSON in the client (you also have to uncomment
        // dependency on jersey-media-json module in pom.xml and Main.startServer())
        // --
        // c.configuration().enable(new org.glassfish.jersey.media.json.JsonJaxbFeature());

        target = c.target(Main.BASE_URI);
    }

    @After
    public void tearDown() throws Exception {
        server.shutdownNow();
    }

    /**
     * Test to see that the message "Got it!" is sent in the response.
     */
    @Test
    public void testGetIt() {
        Response responseMsg = target.path("galaxy").request().post(null);
        assertEquals(200, responseMsg.getStatus());
        
        responseMsg = target.path("galaxy/weather/dry").request().get();
        assertEquals(200, responseMsg.getStatus());
        
        responseMsg = target.path("galaxy/weather/rainy").request().get();
        assertEquals(200, responseMsg.getStatus());
        
        responseMsg = target.path("galaxy/weather/rainypeak").request().get();
        assertEquals(200, responseMsg.getStatus());
        
        responseMsg = target.path("galaxy/weather/optimal").request().get();
        assertEquals(200, responseMsg.getStatus());
    }
}

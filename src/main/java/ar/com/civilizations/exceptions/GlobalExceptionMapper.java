package ar.com.civilizations.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Exception> {

	@Override
	public Response toResponse(Exception exception) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage("There has been an internal server error. Please contact an administrator.");
		return Response.status(500).entity(errorResponse).type("application/json").build();
	}
}

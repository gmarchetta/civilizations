package ar.com.civilizations.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InternalServerErrorExceptionMapper implements ExceptionMapper<CivilizationsInternalServerErrorException> {

	@Override
	public Response toResponse(CivilizationsInternalServerErrorException exception) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(exception.getMessage());
		return Response.status(500).entity(errorResponse).type("application/json").build();
	}
}

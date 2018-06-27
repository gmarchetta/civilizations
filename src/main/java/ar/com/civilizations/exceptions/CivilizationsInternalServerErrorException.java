package ar.com.civilizations.exceptions;

public class CivilizationsInternalServerErrorException extends RuntimeException {
	private static final long serialVersionUID = 3202654495736858059L;

	public CivilizationsInternalServerErrorException() {
		super("There has been an error processing your request.");
	}

	public CivilizationsInternalServerErrorException(String string) {
		super(string);
	}
}

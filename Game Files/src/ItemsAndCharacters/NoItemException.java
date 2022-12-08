package ItemsAndCharacters;

public class NoItemException extends RuntimeException { // runtime exception, does not need to be explicitly handled.
	/* variables */
	private static final long serialVersionUID = 1L;
	private String message = "No such item";
	
	/* constructors */
	public NoItemException() {
		super();
	}
	public NoItemException(String message) {
		this();
		this.message = message;
	}
	
	/* methods */
	@Override
	public String getMessage() {
		return message;
	}
	
}

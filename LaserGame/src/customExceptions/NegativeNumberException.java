package customExceptions;

public class NegativeNumberException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NegativeNumberException() {
		super("data can not be negative");
	}

}

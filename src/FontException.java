/**
 * Thrown as an indication of null font or unexisting font
 */
public class FontException extends Throwable {

	private static final long serialVersionUID = 1L;
	String description;
	
	public FontException(String description) {
		this.description = description;
	}

}

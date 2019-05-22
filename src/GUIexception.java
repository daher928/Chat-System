/**
 * Thrown as an indication of GUI errors
 */
public class GUIexception extends Throwable{

	private static final long serialVersionUID = 1L;
	String description;
	
	public GUIexception(String description) {
		this.description = description;
	}
}
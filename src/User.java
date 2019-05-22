/**
 * User Interface
 * <p>
 * This interface represents a User
 * 
 */
public interface User {

	String getUsername();
	void login() throws Exception, GUIexception;
	void logout() throws Exception;
	void update(String sender, String messageReceived) throws Exception, GUIexception;
	void notify(User user, String messageSent) throws Exception;

}

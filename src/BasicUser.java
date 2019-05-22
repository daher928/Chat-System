import java.util.Random;

/**
 * BasicUser
 * <p>
 * A BasicUser is an implementation of a user, representing a user in the chat
 * room system
 * 
 * <p>
 * <b>Fields:</b>
 * <pre>
 * chatSystem: ChatSystem 	// the chat system that the user can log into and send
 * messages userName: String 	// the user's name userID: int // a unique ID for
 * the user chatGUI: ChatGUI 	// the GUI which the user interacts
 * 
 * 
 */

public class BasicUser implements User {

	/**
	 * Abstraction Function: represents a BasicUser which interacts with the chat
	 * room using chatSystem and the chatGUI username is the name the the user
	 * passes. username is "Anonymous" by default if empty username is passed userID
	 * is a random integer betweem 1 and 1000.
	 *
	 * representation invariant: chatSystem != null chatGUI != null
	 */
	private ChatSystem chatSystem;
	private String userName = "Anonymous";
	private int userID;
	private ChatGUI chatGUI;

	/**
	 * @effects creates a BasicUser
	 * @throws Exception 
	 * @throws GUIexception 
	 */
	public BasicUser(ChatSystem chatSystem, String userName, TextCustomizer customizer) throws Exception, GUIexception {
		this.chatSystem = chatSystem;

		if (!userName.equals(""))
			this.userName = userName;

		Random rand = new Random();

		userID = rand.nextInt(1000) + 1;

		ChatSystem.SystemUsersNumber++;

		chatGUI = new ChatGUI(this, customizer);

		checkRep();
	}

	/**
	 * @throws Exception 
	 * @throws GUIexception 
	 * @effects logs user into the chatSystem
	 */
	@Override
	public void login() throws Exception, GUIexception {
		checkRep();
		chatSystem.login(this);
	}

	/**
	 * @throws Exception 
	 * @effects logs user out of the chatSystem
	 */

	@Override
	public void logout() throws Exception {
		checkRep();
		chatSystem.logout(this);
	}

	/**
	 * @throws Exception 
	 * @throws GUIexception 
	 * @requires sender != null, messageReceived != null
	 * @effects updates the users GUI with a new message from a user with a username
	 *          of sender
	 */

	@Override
	public void update(String sender, String messageReceived) throws Exception, GUIexception {
		checkRep();
		chatGUI.append("<" + sender + ">:  " + messageReceived + "\n");

	}

	/**
	 * @return the userName
	 */

	@Override
	public String getUsername() {
		return userName;
	}

	/**
	 * @throws Exception 
	 * @requires user != null, messageSent != null
	 * @effects notifies the chatSystem of new message been sent
	 */
	@Override
	public void notify(User user, String messageSent) throws Exception {
		checkRep();
		chatSystem.notify(this, messageSent);
	}

	/**
	 * @throws Exception 
	 * @requires customizer != null
	 * @effects change the user's chatGUI textCustomizer
	 */
	public void changeFontStyle(TextCustomizer customizer) throws Exception {
		checkRep();
		try {
			this.chatGUI.setTextCustomizer(customizer);
		} catch (GUIexception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @effects Returns the hash code for this BasicUser.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + userID;
		return result;
	}

	/**
	 * @effects true if the specified Object is an instance of BasicUser and equals
	 *          this BasicUser; false otherwise.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BasicUser other = (BasicUser) obj;
		if (userID != other.userID)
			return false;
		return true;
	}

	private void checkRep() throws Exception {
		if (this.chatSystem == null)
			throw new Exception("No chat system specified");
		if (this.chatGUI == null)
			throw new Exception("No chat gui found");

	}
}

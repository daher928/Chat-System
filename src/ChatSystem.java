import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ChatSystem
 * <p>
 * A ChatSystem is the system which users interact with
 * 
 * <p>
 * <b>Fields:</b>
 * 
 * <pre>
 * usersList // a list of all the chat system's users who's logged in
 * 
 */
public class ChatSystem {
	/**
	 * Abstraction Function: represents a ChatSystem which users interact with.
	 * usersList is a list of all logged in users of the system. SystemUsersNumber
	 * is the total number of logged in users in the system
	 * 
	 * representation invariant: usersList != null, SystemUsersNumber >= 0
	 */
	
	private List<User> usersList;
	public static int SystemUsersNumber;

	/**
	 * @effects creates a new ChatSytem 
	 */
	public ChatSystem() {
		usersList = new ArrayList<>();
		checkRep();
	}

	/**
	 * @param user - the user to log in and add to the system
	 * @throws GUIexception 
	 * @effects logs user into the chatSystem by adding to the list
	 */
	void login(User user) throws GUIexception {
		checkRep();
		if (usersList.contains(user)) {
			try {
				user.update("System", "You are already logged in\n");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			usersList.add(user);
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			try {
				user.update("System", "Welcome to the chat room " + user.getUsername() + "!\nLogin time: "
						+ dateFormat.format(date) + "\n");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		checkRep();
	}

	/**
	 * @param user - the user to log out and remove from the system
	 * @effects log out user from the chatSystem by removing from the list
	 */
	void logout(User user) {
		checkRep();
		if (!usersList.contains(user)) {
			try {
				user.update("System", "You are not logged in");
			} catch (Exception e) {
				e.printStackTrace();
			} catch (GUIexception e) {
				e.printStackTrace();
			}
		} else {
			usersList.remove(user);
			try {
				user.update("System", "Logged out successfully");
			} catch (Exception e) {
				e.printStackTrace();
			} catch (GUIexception e) {
				e.printStackTrace();
			}
		}
		checkRep();
	}

	/**
	 * @param sender - the message sender
	 * @param message - the message that the user sent
	 * @effects notifies all users of the system (except the sender) of the message sent my the sender
	 */
	void notify(User sender, String message) {
		checkRep();
		if(!usersList.contains(sender))
			return;
		for (User user : usersList) {
			if (!user.equals(sender)) {
				try {
					user.update(sender.getUsername(), message);
				} catch (Exception e) {
					e.printStackTrace();
				} catch (GUIexception e) {
					e.printStackTrace();
				}
			}
		}
	}

	void checkRep() {
		assert (usersList != null);
		assert (SystemUsersNumber >= 0);
	}
}

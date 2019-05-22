
public class ChatRoomTest {
	
	public static void main(String[] args) throws Exception, GUIexception, FontException {

		ChatSystem chatSystem = new ChatSystem();

		BasicUser user1 = new BasicUser(chatSystem, "daher_1", new BoldStyle());
		user1.login();
		BasicUser user2 = new BasicUser(chatSystem, "daher2", new RegulatStyle());
		user2.login();
		BasicUser user3 = new BasicUser(chatSystem, "", new FontStyle("Brush Script MT"));
		user3.login();
		
		user1.changeFontStyle(new FontStyle("Arial"));
		
		user2.logout();
		
	}
}

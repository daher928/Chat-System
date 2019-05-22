import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
/*
 * A ChatGUI represents a graphical user interface for the chat system user.
 */
public class ChatGUI {
	
	/**
	 * Abstraction Function:
	 * frame represents the fram in which the whole graphical components are gathered and handled
	 * sendButton is the button which allows the message to be sent to the chat system.
	 * newMessageBox and chatBox are the boxes in which the user composes a new message and views other messages respectivly.
	 * textCustomizer allows the text in the gui to be customized
	 *
	 * Representation invariant:
	 * The frame is represented by JFrame. frame!=null
	 * sendButton is represented by JButton. sendButton!=null
	 * newMessageBox and chatBox are represented by JTextField and JTextArea respectivly and can't be null
	 * textCustomizer is a TextCustomizer. textCustomizer!=null
	 */
	
	private static JFrame frame = new JFrame("Chat Room");
	private JButton sendButton;
	private JTextField newMessageBox;
	private JTextArea chatBox;
	private TextCustomizer textCustomizer;
	
	/**
	 * @param user - the user which this GUI belongs to
	 * @param customizer - the text customizer for this GUI
	 * @requires user != null 
	 * @effects create a new ChatGui
	 * @throws GUIexception 
	 */
	public ChatGUI(User user, TextCustomizer customizer) throws GUIexception {
		createChatWindow(user);
		setTextCustomizer(customizer);
		checkRep();
	}
	
	/**
	 * @param user - the user which this chat Window belongs to
	 * @effects create a new chat window which is the frame and the inner components
	 */
	private void createChatWindow(User user) {
		JPanel newPanel = new JPanel();
		newPanel.setLayout(new BorderLayout());

		JPanel textBoxPanel = new JPanel();
		textBoxPanel.setLayout(new GridBagLayout());

		newMessageBox = new JTextField(80);
		newMessageBox.setBackground(Color.LIGHT_GRAY);
		newMessageBox.setFont(new Font("Courier", Font.ITALIC, 20));
		sendButton = new JButton("Send");
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (newMessageBox.getText().length() > 0) {
					String text = newMessageBox.getText();
					chatBox.append("<" + user.getUsername() + ">:  " + text + "\n");
					newMessageBox.setText("");
					try {
						user.notify(user, text);
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
				newMessageBox.requestFocusInWindow();
			}
		});

		chatBox = new JTextArea();
		chatBox.setEditable(false);
		chatBox.setLineWrap(true);

		newPanel.add(new JScrollPane(chatBox), BorderLayout.CENTER);

		GridBagConstraints left = new GridBagConstraints();
		left.anchor = GridBagConstraints.LINE_START;
		left.fill = GridBagConstraints.HORIZONTAL;
		left.weightx = 512.0D;
		left.weighty = 1.0D;

		GridBagConstraints right = new GridBagConstraints();
		right.insets = new Insets(0, 10, 0, 0);
		right.anchor = GridBagConstraints.LINE_END;
		right.fill = GridBagConstraints.NONE;
		right.weightx = 1.0D;
		right.weighty = 1.0D;

		textBoxPanel.add(newMessageBox, left);
		textBoxPanel.add(sendButton, right);

		newPanel.add(BorderLayout.SOUTH, textBoxPanel);

		frame.setSize(1920, 1024);
		frame.setLayout(new GridLayout(0, ChatSystem.SystemUsersNumber));
		frame.add(newPanel, BorderLayout.AFTER_LAST_LINE);
		frame.setVisible(true);

	}

	/**
	 * @param msg - the message to display to the chat box
	 * @effects displays the message to the users GUI chatBox
	 * @throws GUIexception 
	 */
	public void append(String msg) throws GUIexception {
		checkRep();
		chatBox.append(msg);
		checkRep();
	}
	
	/**
	 * @param customizer - a Text customizer
	 * @effects sets a new text customizer to the current GUI
	 * @throws GUIexception 
	 */
	void setTextCustomizer(TextCustomizer customizer) throws GUIexception {
		this.textCustomizer = customizer;
		textCustomizer.customize(chatBox);
		checkRep();
	}
	
	void checkRep() throws GUIexception {
		if (frame==null)
			throw new GUIexception("GUI frame is null");
		if (sendButton==null)
			throw new GUIexception("GUI sendButton is null");
		if (newMessageBox==null)
			throw new GUIexception("GUI newMessageBox is null");
		if (chatBox==null)
			throw new GUIexception("GUI chatBox is null");
		if (textCustomizer==null)
			throw new GUIexception("textCustomizer is null");
	}

}

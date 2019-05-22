import java.awt.Font;

import javax.swing.text.JTextComponent;
/**
 * BoldStyle
 * <p>
 * a TextCustomizer which customizes the text of a graphical text component with a <b>Bold</b> style
 */
public class BoldStyle implements TextCustomizer {

	/**
	 * @requires textComponent != null
	 * @effects customizes the text of the textComponent with a Bold style 
	 * @param textComponent - the JTextComponent to customize
	 */
	@Override
	public void customize(JTextComponent textComponent) {
		textComponent.setFont(new Font("Serif", Font.BOLD, 20));
		
	}

}

import java.awt.Font;

import javax.swing.text.JTextComponent;
/**
 * RegularStyle
 * <p>
 * a TextCustomizer which customizes the text of a graphical text component with a <b>regular plain</b> style
 */
public class RegulatStyle implements TextCustomizer {

	/**
	 * @requires textComponent != null
	 * @effects customizes the text of the textComponent with the regular style 
	 * @param textComponent - the JTextComponent to customize
	 */
	@Override
	public void customize(JTextComponent textComponent) {
		textComponent.setFont(new Font("Serif", Font.PLAIN, 20));
	}


}

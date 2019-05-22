import javax.swing.text.JTextComponent;
/**
 * TextCustomizer Interface
 * <p>
 * This interface represents a TextCustomizer
 * <p>
 * A TextCustomizer is a customizer which customizes font and/or the text's properties of a graphical text component
 * 
 */
public interface TextCustomizer {
	
	/**
	 * Customizing the text handled in the textComponent
	 * @param textComponent - the component which holds and handles the graphical text
	 */
	void customize(JTextComponent textComponent);
}

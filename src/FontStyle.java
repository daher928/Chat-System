import java.awt.Font;
import java.awt.GraphicsEnvironment;

import javax.swing.text.JTextComponent;

/**
 * RegularStyle
 * <p>
 * a TextCustomizer which customizes the text of a graphical text component with
 * a <b>font</b> style
 * <p>
 * <b>Fields:</b>
 * <p>
 * fontName : String // the font to customize the text with.
 */
public class FontStyle implements TextCustomizer {

	private String fontName;

	/**
	 * Abstraction Function: fontName is the font to customize the text with
	 *
	 * representation invariant: fontName != null and must be a valid existing font
	 * 
	 * @throws FontException
	 */
	FontStyle(String font) throws FontException {
		this.fontName = font;
		checkRep();
	}

	/**
	 * @requires textComponent != null
	 * @effects customizes the text of the textComponent with a specific font style
	 * @param textComponent
	 *            - the JTextComponent to customize
	 */
	@Override
	public void customize(JTextComponent textComponent) {
		textComponent.setFont(new Font(fontName, Font.PLAIN, 20));

	}

	void checkRep() throws FontException {
		if (!isFontValid(fontName))
			throw new FontException("Invalid font: Null or not found");
	}

	/**
	 * @param fontName
	 *            - the font
	 * @return False - if fontName is null or does not exist. otherwise True
	 */
	private boolean isFontValid(String fontName) {
		if (fontName == null)
			return false;
		GraphicsEnvironment g = GraphicsEnvironment.getLocalGraphicsEnvironment();
		String[] allFonts = g.getAvailableFontFamilyNames();
		for (String fontStr : allFonts) {
			if (fontStr.equals(this.fontName)) {
				return true;
			}
		}
		return false;
	}

}

package sh4j.model.highlight;

import sh4j.model.style.SStyle;

/**
 SDummy highligther Style Class.

 @author Gabriel De La Parra
 @version 1.0 */
public class SDummy implements SHighlighter {

  /**
   Parses a String and returns if it requires to be highlighted.

   @param text Input text that might be highlighted.
   @return Returns true if the text needs to be highlighted.
   */
  @Override
  public boolean needsHighLight(String text) {
    return true;
  }

  /**
   Highlights a String with a Style.
   
   @param text Input text to highlight.
   @param style Desired highlight style.
   @return Input text with highlighted style.
   */
  @Override
  public String highlight(String text, SStyle style) {
    return text;
  }
}

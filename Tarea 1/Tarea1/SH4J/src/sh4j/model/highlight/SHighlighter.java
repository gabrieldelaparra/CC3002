package sh4j.model.highlight;

import sh4j.model.style.SStyle;

/**
 SHighlighter Interface.

 @author Gabriel De La Parra
 @version 1.0 */
public interface SHighlighter {

  /**
   Parses a String and returns if it requires to be highlighted.

   @param text Input text that might be highlighted.
   @return Returns true if the text needs to be highlighted.
   */
  boolean needsHighLight(String text);

  /**
   Highlights a String with a Style.

   @param text Input text to highlight.
   @param style Desired highlight style.
   @return Input text with highlighted style.
   */
  String highlight(String text, SStyle style);
}

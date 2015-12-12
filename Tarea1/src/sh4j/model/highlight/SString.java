package sh4j.model.highlight;

import sh4j.model.style.SStyle;

import java.util.regex.Pattern;

/**
 SString highligther Style Class.

 @author Gabriel De La Parra
 @version 1.0 */
public class SString implements SHighlighter {
  /**
   Parses a String and returns if it requires to be highlighted.

   @param text Input text that might be highlighted.
   @return Returns true if the text needs to be highlighted.
   */
  @Override
  public boolean needsHighLight(String text) {
    return Pattern.matches("\"(.*)\"",text);
  }

  /**
   Highlights a String with a Style.

   @param text Input text to highlight.
   @param style Desired highlight style.
   @return Input text with highlighted style.
   */
  @Override
  public String highlight(String text, SStyle style) {
    String result;
    if (style.isStyle("eclipse")) {
      result = "<span style='color:#2a00ff; '>" + text + "</span>";
    } else if (style.isStyle("dark")) {
      result = "<span style='color:#00c4c4; '>" + text + "</span>";
    } else {
      result = "<span style='color:#e60000; '>" + text + "</span>";
    }
    return result;
  }
}

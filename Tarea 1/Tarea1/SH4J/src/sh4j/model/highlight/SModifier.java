package sh4j.model.highlight;

import sh4j.model.style.SStyle;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 SModifier highligther Style Class.

 @author Gabriel De La Parra
 @version 1.0 */
public class SModifier implements SHighlighter {
  /**
   Highlightable Keywords collection.
   First sentence should be present.
  */
  private final Set<String> javaKeywords =
      new HashSet<>(Arrays.asList(
          "private",
          "public",
          "protected"));

  /**
   Parses a String and returns if it requires to be highlighted.

   @param text Input text that might be highlighted.
   @return Returns true if the text needs to be highlighted.
   */
  @Override
  public boolean needsHighLight(String text) {
    return javaKeywords.contains(text);
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
      result = "<span style='color:#7f0055; font-weight:bold; '>" + text + "</span>";
    } else if (style.isStyle("dark")) {
      result = "<span style='color:#e66170; font-weight:bold; '>" + text + "</span>";
    } else {
      result = "<span style='color:#400000; font-weight:bold; '>" + text + "</span>";
    }
    return result;
  }
}

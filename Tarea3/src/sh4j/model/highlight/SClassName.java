package sh4j.model.highlight;

import sh4j.model.style.SStyle;

import java.util.regex.Pattern;

/**
 * ClassName highlighter.
 *
 * @author juampi
 */
public class SClassName implements SHighlighter {

  /**
   * Checks if a text needs highlight.
   *
   * @param text Text to check.
   * @return Returns true if the text needs highlight.
   */
  @Override
  public boolean needsHighLight(String text) {
    return Pattern.compile("[A-Z_$]+[a-zA-Z0-9_$]*").matcher(text).matches();
  }

  /**
   * Hightlights a text with a Style.
   *
   * @param text  Text to be highlighted.
   * @param style Style to be applied.
   * @return Returns the highlighted string.
   */
  @Override
  public String highlight(String text, SStyle style) {
    return style.formatClassName(text);
  }

}

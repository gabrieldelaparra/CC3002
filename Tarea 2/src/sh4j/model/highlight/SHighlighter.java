package sh4j.model.highlight;

import sh4j.model.style.SStyle;
/**
 * Highlighter Interface.
 *
 * @author juampi
 */
public interface SHighlighter {
  /**
   * Checks if a text needs highlight.
   *
   * @param text Text to check.
   * @return Returns true if the text needs highlight.
   */
  boolean needsHighLight(String text);

  /**
   * Hightlights a text with a Style.
   *
   * @param text  Text to be highlighted.
   * @param style Style to be applied.
   * @return Returns the highlighted string.
   */
  String highlight(String text, SStyle style);
}

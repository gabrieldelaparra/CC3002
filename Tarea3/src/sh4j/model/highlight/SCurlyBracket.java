package sh4j.model.highlight;

import sh4j.model.style.SStyle;

/**
 * CurlyBracket highlighter.
 *
 * @author juampi
 */
public class SCurlyBracket implements SHighlighter {

  /**
   * Checks if a text needs highlight.
   *
   * @param text Text to check.
   * @return Returns true if the text needs highlight.
   */
  @Override
  public boolean needsHighLight(String text) {
    return "{".equals(text) || "}".equals(text);
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
    return style.formatCurlyBracket(text);
  }

}

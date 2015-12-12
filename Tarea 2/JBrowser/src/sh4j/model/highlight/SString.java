package sh4j.model.highlight;

import sh4j.model.style.SStyle;

/**
 * String highlighter.
 *
 * @author juampi
 */
public class SString implements SHighlighter {

  @Override
  public boolean needsHighLight(String text) {
    return text.length() >= 2 && text.charAt(0) == '"' && text.charAt(text.length() - 1) == '"';
  }

  @Override
  public String highlight(String text, SStyle style) {
    return style.formatString(text);
  }

}

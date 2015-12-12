package sh4j.model.highlight;

import sh4j.model.style.SStyle;

/**
 * SemiColon highlighter.
 *
 * @author juampi
 */
public class SSemiColon implements SHighlighter {

  @Override
  public boolean needsHighLight(String text) {
    return ";".equals(text);
  }

  @Override
  public String highlight(String text, SStyle style) {
    return style.formatSemiColon(text);
  }

}

package sh4j.model.highlight;

import sh4j.model.style.SStyle;
/**
 * CurlyBracket highlighter.
 *
 * @author juampi
 */
public class SCurlyBracket implements SHighlighter {

  @Override
  public boolean needsHighLight(String text) {
    return "{".equals(text) || "}".equals(text);
  }

  @Override
  public String highlight(String text, SStyle style) {
    return style.formatCurlyBracket(text);
  }

}

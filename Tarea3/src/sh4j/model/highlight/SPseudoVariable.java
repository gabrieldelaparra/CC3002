package sh4j.model.highlight;

import sh4j.model.style.SStyle;

/**
 * PseudoVariable highlighter.
 *
 * @author juampi
 */
public class SPseudoVariable implements SHighlighter {

  @Override
  public boolean needsHighLight(String text) {
    return "super".equals(text) || "this".equals(text);
  }

  @Override
  public String highlight(String text, SStyle style) {
    return style.formatPseudoVariable(text);
  }

}

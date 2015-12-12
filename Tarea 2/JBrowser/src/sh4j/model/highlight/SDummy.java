package sh4j.model.highlight;

import sh4j.model.style.SStyle;
/**
 * Class Name highlighter.
 *
 * @author juampi
 */
public class SDummy implements SHighlighter {

  @Override
  public boolean needsHighLight(String text) {
    return true;
  }

  @Override
  public String highlight(String text, SStyle style) {
    return text;
  }

}

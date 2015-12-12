package sh4j.model.highlight;

import sh4j.model.style.SStyle;

import java.util.regex.Pattern;

/**
 * ClassName highlighter.
 *
 * @author juampi
 */
public class SClassName implements SHighlighter {

  @Override
  public boolean needsHighLight(String text) {

    return Pattern.compile("[A-Z_$]+[a-zA-Z0-9_$]*").matcher(text).matches();
  }

  @Override
  public String highlight(String text, SStyle style) {
    return style.formatClassName(text);
  }

}

package sh4j.model.highlight;

import sh4j.model.style.SStyle;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
/**
 * Modifier highlighter.
 *
 * @author juampi
 */
public class SModifier implements SHighlighter {

  /**
   * Creates a base Collection of keywords.
   */
  private static final Set<String> javaModifiers = new HashSet<String>(Arrays.asList("private",
      "protected", "public"));

  @Override
  public boolean needsHighLight(String text) {
    return javaModifiers.contains(text);
  }

  @Override
  public String highlight(String text, SStyle style) {
    return style.formatModifier(text);
  }

}

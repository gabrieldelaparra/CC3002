package sh4j.model.highlight;

import sh4j.model.style.SStyle;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * MainClass highlighter.
 *
 * @author juampi
 */
public class SMainClass implements SHighlighter {
  private static final Set<String> javaMainClasses = new HashSet<String>(Arrays.asList(
      "System", "Character", "String"
  ));

  /**
   * Checks if a text needs highlight.
   *
   * @param text Text to check.
   * @return Returns true if the text needs highlight.
   */
  @Override
  public boolean needsHighLight(String text) {
    return javaMainClasses.contains(text);
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
    return style.formatMainClass(text);
  }
}

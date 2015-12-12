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

  @Override
  public boolean needsHighLight(String text) {
    return javaMainClasses.contains(text);
  }

  @Override
  public String highlight(String text, SStyle style) {
    return style.formatMainClass(text);
  }
}

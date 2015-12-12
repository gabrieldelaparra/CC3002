package sh4j.model.highlight;

import sh4j.model.style.SStyle;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * KeyWord highlighter.
 *
 * @author juampi
 */
public class SKeyWord implements SHighlighter {

  /**
   * Creates a base Collection of keywords.
   */
  private static final Set<String> javaKeywords = new HashSet<String>(Arrays.asList(
      "abstract", "assert", "boolean", "break", "byte",
      "case", "catch", "char", "class", "const",
      "continue", "default", "do", "double", "else",
      "enum", "extends", "false", "final", "finally",
      "float", "for", "goto", "if", "implements",
      "import", "instanceof", "int", "interface", "long",
      "native", "new", "null", "package",
      "return", "short", "static",
      "strictfp", "super", "switch", "synchronized", "this",
      "throw", "throws", "transient", "true", "try",
      "void", "volatile", "while"
  ));

  /**
   * Checks if a text needs highlight.
   *
   * @param text Text to check.
   * @return Returns true if the text needs highlight.
   */
  @Override
  public boolean needsHighLight(String text) {
    return javaKeywords.contains(text);
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
    return style.formatKeyWord(text);
  }
}

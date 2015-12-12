package sh4j.model.highlight;

import sh4j.model.style.SStyle;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 SKeyWord highligther Style Class.

 @author Gabriel De La Parra
 @version 1.0 */
public class SKeyWord implements SHighlighter {
  /**
   Highlightable Keywords collection.
   First sentence should be present.
  */
  private final Set<String> javaKeywords =
      new HashSet<>(Arrays.asList("abstract", "assert", "boolean",
                                  "break", "byte", "case", "catch",
                                  "char", "class", "const", "continue",
                                  "default", "do", "double", "else",
                                  "enum", "extends", "false", "final",
                                  "finally", "float", "for", "goto",
                                  "if", "implements", "import",
                                  "instanceof", "int", "interface",
                                  "long", "native", "new", "null",
                                  "package", "return", "short",
                                  "static", "strictfp", "super",
                                  "switch", "synchronized", "this",
                                  "throw", "throws", "transient",
                                  "true", "try", "void", "volatile",
                                  "while"));

  /**
   Parses a String and returns if it requires to be highlighted.

   @param text Input text that might be highlighted.
   @return Returns true if the text needs to be highlighted.
   */
  @Override
  public boolean needsHighLight(String text) {
    return javaKeywords.contains(text);
  }

  /**
   Highlights a String with a Style.

   @param text Input text to highlight.
   @param style Desired highlight style.
   @return Input text with highlighted style.
   */
  @Override
  public String highlight(String text, SStyle style) {
    String result;
    if (style.isStyle("eclipse")) {
      result = "<span style='color:#7f0055; font-weight:bold; '>" + text + "</span>";
    } else if (style.isStyle("dark")) {
      result = "<span style='color:#bb7977; '>" + text + "</span>";
    } else {
      result = "<span style='color:#800040; '>" + text + "</span>";
    }
    return result;
  }
}

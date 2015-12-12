package sh4j.model.style;

import static sh4j.model.format.SHTMLFormatter.tag;

/**
 * Bred Style Highlighter Class.
 */
public class SBredStyle implements SStyle {

  @Override
  public String toString() {
    return "bred";
  }

  @Override
  public String formatClassName(String text) {
    return text;
  }

  @Override
  public String formatCurlyBracket(String text) {
    return tag("span", text, "color:#806030; ");
  }

  @Override
  public String formatKeyWord(String text) {
    return tag("span", text, "color:#800040; ");
  }

  @Override
  public String formatPseudoVariable(String text) {
    return tag("span", text, "color:#400000; font-weight:bold;  ");
  }

  @Override
  public String formatSemiColon(String text) {
    return tag("span", text, "color:#806030; ");
  }

  @Override
  public String formatString(String text) {
    return tag("span", text, "color:#e60000; ");
  }

  @Override
  public String formatMainClass(String text) {
    return tag("span", text, "color:#800040; ");
  }

  @Override
  public String formatBody(String text) {
    return tag("pre", text, "color:#000000;background:#f1f0f0;");
  }

  @Override
  public String formatModifier(String text) {
    return tag("span", text, "color:#400000; font-weight:bold; ");
  }

}

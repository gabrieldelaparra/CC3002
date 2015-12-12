package sh4j.model.style;

import static sh4j.model.format.SHTMLFormatter.tag;

/**
 * Dark Style Highlighter Class.
 */
public class SDarkStyle implements SStyle {

  @Override
  public String toString() {
    return "dark";
  }

  @Override
  public String formatClassName(String text) {
    return text;
  }

  @Override
  public String formatCurlyBracket(String text) {
    return tag("span", text, "color:#b060b0; ");
  }

  @Override
  public String formatKeyWord(String text) {
    return tag("span", text, "color:#bb7977; ");
  }

  @Override
  public String formatPseudoVariable(String text) {
    return tag("span", text, "color:#e66170; font-weight:bold; ");
  }

  @Override
  public String formatSemiColon(String text) {
    return tag("span", text, "color:#b060b0; ");
  }

  @Override
  public String formatString(String text) {
    return tag("span", text, "color:#00c4c4; ");
  }

  @Override
  public String formatMainClass(String text) {
    return tag("span", text, "color:#bb7977; font-weight:bold; ");
  }

  @Override
  public String formatBody(String text) {
    return tag("pre", text, "color:#d1d1d1;background:#000000;");
  }

  @Override
  public String formatModifier(String text) {
    return tag("span", text, "color:#e66170; font-weight:bold; ");
  }

}

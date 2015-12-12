package sh4j.model.style;

import static sh4j.model.format.SHTMLFormatter.tag;

/**
 * Eclipse Style Highlighter Class.
 */
public class SEclipseStyle implements SStyle {

  @Override
  public String toString() {
    return "eclipse";
  }

  @Override
  public String formatClassName(String text) {
    return text;
  }

  @Override
  public String formatCurlyBracket(String text) {
    return tag("span", text, "font-weight:bold; ");
  }

  @Override
  public String formatKeyWord(String text) {
    return tag("span", text, "color:#7f0055; font-weight:bold; ");
  }

  @Override
  public String formatPseudoVariable(String text) {
    return tag("span", text, "color:#7f0055; font-weight:bold; ");
  }

  @Override
  public String formatSemiColon(String text) {
    return tag("span", text, "font-weight:bold; ");
  }

  @Override
  public String formatString(String text) {
    return tag("span", text, "color:#2a00ff; ");
  }

  @Override
  public String formatMainClass(String text) {
    return tag("span", text, "color:#7f0055; font-weight:bold; ");
  }

  @Override
  public String formatBody(String text) {
    return tag("pre", text, "color:#000000;background:#ffffff;");
  }

  @Override
  public String formatModifier(String text) {
    return tag("span", text, "color:#7f0055; font-weight:bold; ");
  }
}

package sh4j.model.style;

/**
 * Base Style Highlighter Interace.
 */
public interface SStyle {

  String toString();

  String formatClassName(String text);

  String formatCurlyBracket(String text);

  String formatKeyWord(String text);

  String formatPseudoVariable(String text);

  String formatSemiColon(String text);

  String formatString(String text);

  String formatMainClass(String text);

  String formatModifier(String text);

  String formatBody(String text);

}

package sh4j.model.format;

import sh4j.parser.model.SBlock;

/**
 * Base Formatter Interface.
 */
public interface SFormatter {
  void styledWord(String word);

  void styledChar(char character);

  void styledSpace();

  void styledCR();

  void styledBlock(SBlock block);

  String formattedText();
}

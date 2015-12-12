package sh4j.model.format;

import sh4j.parser.model.SBlock;

/**
 Formatter Interface.

 @author Gabriel De La Parra
 @version 1.0 */
public interface SFormatter {
  /**
   Appends a word to the buffer.

   @param word string to append.
   */
  void styledWord(String word);

  /**
   Appends a character to the buffer.

   @param character char to append.
   */
  void styledChar(char character);

  /**
   Appends a empty string to the buffer.
   */
  void styledSpace();

  /**
   Appends a Carrier Return to the buffer.
   */
  void styledCarrierReturn();

  /**
   Reads a block.

   @param sblock stringBlock to read.
   */
  void styledBlock(SBlock sblock);

  /**
   Applies an HTML header to the buffer.

   @return bufferString with style-based header;
   */
  String formattedText();
}

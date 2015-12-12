package sh4j.model.format;

import sh4j.parser.model.SBlock;
import sh4j.parser.model.SText;

/**
 * Plain Formatter for texts.
 */
public class SPlainFormatter implements SFormatter {

  private final StringBuffer buffer;
  private int level;

  /**
   * Constructor for PlainFormatter.
   */
  public SPlainFormatter() {
    buffer = new StringBuffer();
  }

  /**
   * Appends a word.
   *
   * @param word Word to be appended.
   */
  @Override
  public void styledWord(String word) {
    buffer.append(word);
  }

  /**
   * Appends a Character.
   *
   * @param character Character to be appended.
   */
  @Override
  public void styledChar(char character) {
    buffer.append(character);
  }

  /**
   * Appends a space.
   */
  @Override
  public void styledSpace() {
    buffer.append(' ');
  }

  /**
   * Appends a CarrierReturn (New Line).
   */
  @Override
  public void styledCR() {
    buffer.append('\n');
    indent();
  }

  /**
   * Sets indent level definitions for a Block.
   *
   * @param block Block to be intended.
   */
  @Override
  public void styledBlock(SBlock block) {
    level++;
    for (SText text : block.texts()) {
      text.export(this);
    }
    level--;
  }

  /**
   * Adds the required spaces for indentation.
   */
  private void indent() {
    for (int i = 0; i < level; i++) {
      buffer.append("  ");
    }
  }

  /**
   * Returns the formatted text.
   *
   * @return Formats the texts and returns it.
   */
  @Override
  public String formattedText() {
    return buffer.toString();
  }

}

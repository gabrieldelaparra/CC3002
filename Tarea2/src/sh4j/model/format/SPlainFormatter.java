package sh4j.model.format;

import sh4j.parser.model.SBlock;
import sh4j.parser.model.SText;

/**
 * Plain Formatter for texts.
 */
public class SPlainFormatter implements SFormatter {

  private final StringBuffer buffer;
  private int level;

  public SPlainFormatter() {
    buffer = new StringBuffer();
  }

  @Override
  public void styledWord(String word) {
    buffer.append(word);
  }

  @Override
  public void styledChar(char character) {
    buffer.append(character);
  }

  @Override
  public void styledSpace() {
    buffer.append(' ');
  }

  @Override
  public void styledCR() {
    buffer.append('\n');
    indent();
  }

  @Override
  public void styledBlock(SBlock block) {
    level++;
    for (SText text : block.texts()) {
      text.export(this);
    }
    level--;
  }

  private void indent() {
    for (int i = 0; i < level; i++) {
      buffer.append("  ");
    }
  }

  @Override
  public String formattedText() {
    return buffer.toString();
  }

}

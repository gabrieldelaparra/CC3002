package sh4j.model.format;

import sh4j.model.highlight.SDummy;
import sh4j.model.highlight.SHighlighter;
import sh4j.model.style.SStyle;
import sh4j.parser.model.SBlock;
import sh4j.parser.model.SText;

/**
 * HTML Formatter for texts.
 */
public class SHTMLFormatter implements SFormatter {
  private final StringBuffer buffer;
  private final SStyle style;
  private final SHighlighter[] highlighters;
  public boolean showLineNumbers = false;
  private int level;
  private int line = 1;
  private int lineCount;

  /**
   * Constructor.
   *
   * @param style        Highlight Style.
   * @param highlighters Items to Highlight.
   */
  public SHTMLFormatter(SStyle style, SHighlighter... highlighters) {
    this.style = style;
    this.highlighters = highlighters;
    buffer = new StringBuffer();
    lineCount = 0;
  }

  public SHTMLFormatter(boolean showLineNumbers, SStyle style, SHighlighter... highlighters) {
    this(style, highlighters);
    this.showLineNumbers = showLineNumbers;
  }

  /**
   * HTML Highlighter tag.
   *
   * @param name    Tag name.
   * @param content Tag content.
   * @param style   Tag Style.
   * @return Formatted HTML Tag.
   */
  public static String tag(String name, String content, String style) {
    return "<" + name + " style='" + style + "'>" + content + "</" + name + ">";
  }

  private SHighlighter lookup(String text) {
    for (SHighlighter h : highlighters) {
      if (h.needsHighLight(text)) {
        return h;
      }
    }
    return new SDummy();
  }

  /**
   * Appends a styled word.
   *
   * @param word Word to be appended.
   */
  @Override
  public void styledWord(String word) {
    buffer.append(lookup(word).highlight(word, style));
  }

  /**
   * Appends a styled Character.
   * @param character Character to be styled and appended.
   */
  @Override
  public void styledChar(char character) {
    buffer.append(lookup(character + "").highlight(character + "", style));
  }

  /**
   * Appends a styled Space.
   */
  @Override
  public void styledSpace() {
    buffer.append(' ');
  }

  /**
   * Appends a Carrier Return.
   * For Tarea3, it also adds optionally the line numbers.
   */
  @Override
  public void styledCR() {
    lineCount++;
    String number = lineCount < 10 ? " " + lineCount : "" + lineCount;
    buffer.append("\n");
    if (showLineNumbers) {
      buffer.append("<span style='background:#f1f0f0;'> " + number + " </span>");
    }
    indent();
  }

  /**
   * Sets indent level definitions for a Block.
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
   * @return Formats the texts and returns it.
   */
  @Override
  public String formattedText() {
    return style.formatBody(buffer.toString());
  }
}

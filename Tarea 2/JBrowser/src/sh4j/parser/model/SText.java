package sh4j.parser.model;

import sh4j.model.format.SFormatter;
import sh4j.model.format.SHTMLFormatter;
import sh4j.model.format.SPlainFormatter;
import sh4j.model.highlight.SHighlighter;
import sh4j.model.style.SStyle;

public abstract class SText {

  public abstract void export(SFormatter format);

  public String toString() {
    SFormatter format = new SPlainFormatter();
    this.export(format);
    return format.formattedText();
  }

  public String toHTML(SStyle style, SHighlighter... lighters) {
    SFormatter format = new SHTMLFormatter(style, lighters);
    this.export(format);
    return format.formattedText();
  }
}
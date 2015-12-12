package sh4j.parser.model;


import sh4j.model.format.SFormatter;
import sh4j.model.format.SHtmlFormatter;
import sh4j.model.highlight.SHighlighter;
import sh4j.model.style.SStyle;

public abstract class SText {

  public abstract void export(SFormatter format);

  public String toHTML(SStyle style, SHighlighter... lighters) {
    SFormatter format = new SHtmlFormatter(style, lighters);
    this.export(format);
    return format.formattedText();
  }
}
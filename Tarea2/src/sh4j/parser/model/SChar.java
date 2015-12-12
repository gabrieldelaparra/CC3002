package sh4j.parser.model;

import sh4j.model.format.SFormatter;

public class SChar extends SText {
  private final char c;

  public SChar(char c) {
    this.c = c;
  }

  @Override
  public void export(SFormatter format) {
    format.styledChar(c);
  }

}

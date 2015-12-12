package sh4j.parser.model;

import sh4j.model.format.SFormatter;

public class SWord extends SText {
  private String word;

  public SWord(String word) {
    this.word = word;
  }

  @Override
  public void export(SFormatter format) {
    format.styledWord(word);
  }
}

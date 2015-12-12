package sh4j.parser.model;

import sh4j.model.format.SFormatter;

public class SSpace extends SText {

  @Override
  public void export(SFormatter format) {
    format.styledSpace();
  }
}

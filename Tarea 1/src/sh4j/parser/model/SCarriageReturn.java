package sh4j.parser.model;

import sh4j.model.format.SFormatter;

public class SCarriageReturn extends SText {

  public String toString() {
    return "\n";
  }

  @Override
  public void export(SFormatter format) {
    format.styledCarrierReturn();
  }
}

package sh4j.parser.model;

import sh4j.model.format.SFormatter;

import java.util.ArrayList;
import java.util.List;

public class SBlock extends SText {
  private List<SText> texts;

  public SBlock() {
    texts = new ArrayList<SText>();
  }

  public void space() {
    texts.add(new SSpace());
  }

  public void enter() {
    texts.add(new SCarriageReturn());
  }

  public void word(String word) {
    texts.add(new SWord(word));
  }

  public void symbol(char c) {
    texts.add(new SChar(c));
  }

  public SBlock block() {
    SBlock block = new SBlock();
    texts.add(block);
    return block;
  }

  @Override
  public void export(SFormatter format) {
    format.styledBlock(this);
  }

  public List<SText> texts() {
    return texts;
  }
}

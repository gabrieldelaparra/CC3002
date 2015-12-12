package test;

import org.junit.Before;
import org.junit.Test;
import sh4j.model.highlight.SPseudoVariable;
import sh4j.model.highlight.SSemiColon;
import sh4j.model.style.SBredStyle;
import sh4j.model.style.SDarkStyle;
import sh4j.model.style.SEclipseStyle;
import sh4j.parser.SParser;
import sh4j.parser.model.SBlock;

import static org.junit.Assert.assertEquals;

/**
SStyle Test Class.
*/
public class SStyleNameTest {

  /**
  Test SDarkStyle's toString Method.
  */
  @Test
  public void testDarkStyleName() {
    assertEquals(new SDarkStyle().toString(), "dark");
  }

  /**
  Test SBredStyle's toString Method.
  */
  @Test
  public void testBredStyleName() {
    assertEquals(new SBredStyle().toString(), "bred");
  }

  /**
  Test SEclipseStyle's toString Method.
  */
  @Test
  public void testEclipseStyleName() {
    assertEquals(new SEclipseStyle().toString(), "eclipse");
  }
}

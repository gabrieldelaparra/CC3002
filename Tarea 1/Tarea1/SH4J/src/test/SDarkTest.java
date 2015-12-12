package test;

import org.junit.Before;
import org.junit.Test;
import sh4j.model.highlight.SPseudoVariable;
import sh4j.model.highlight.SSemiColon;
import sh4j.model.style.SDarkStyle;
import sh4j.parser.SParser;
import sh4j.parser.model.SBlock;

import static org.junit.Assert.assertEquals;

/**
Dark Style Test Class.
*/
public class SDarkTest {
  /**
  Formatter field Instace. 
  Provides toHTML method.
  */
  private SBlock source;

  /**
  Creates a String to be tested.
  */
  @Before
  public void setUp() {
    source = SParser.parse("public class Foo{ "
                           + "public boolean bar(){ super.foo(); "
                           + "return false;}}");
  }

  /**
  Tests SPseudoVariable Highlighter with SDarkStyle Style.
  */
  @Test
  public void testPseudoVariable() {
    String html = source.toHTML(new SDarkStyle(), new SPseudoVariable());
    assertEquals(html, "<pre "
                       + "style='color:"
                       + "#d1d1d1;background:"
                       + "#000000;'>"
                       + "public class Foo {\n    \n    public boolean bar(){\n      "
                       + "<span style='color:#e66170; font-weight:bold; '>super"
                       + "</span>.foo();\n      return false;\n      \n    }\n  }</pre>");
  }

  /**
  Tests SSemiColon Highlighter with SDarkStyle Style.
  */
  @Test
  public void testSemiColon() {
    String html = source.toHTML(new SDarkStyle(), new SSemiColon());
    assertEquals(html, "<pre style="
                       + "'color:#d1d1d1;background:#000000;'>"
                       + "public class Foo {\n    \n    public boolean bar()"
                       + "{\n      super.foo()<span style='color:#b060b0; '>;"
                       + "</span>\n      return false<span style='color:#b060b0; '>"
                       + ";</span>\n      \n    }\n  }</pre>");
  }
}

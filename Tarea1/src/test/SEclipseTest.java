package test;

import org.junit.Before;
import org.junit.Test;
import sh4j.model.highlight.SCurlyBracket;
import sh4j.model.highlight.SKeyWord;
import sh4j.model.highlight.SMainClass;
import sh4j.model.highlight.SModifier;
import sh4j.model.highlight.SPseudoVariable;
import sh4j.model.highlight.SString;
import sh4j.model.style.SEclipseStyle;
import sh4j.parser.SParser;
import sh4j.parser.model.SBlock;

import static org.junit.Assert.assertEquals;

/**
Eclipse Style Test Class.
*/
public class SEclipseTest {
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
    source = SParser.parse(
        "public class Foo{ public boolean bar()"
        + "{ return false;}}");
  }

  /**
  Tests SMainClass Highlighter with SEclipseStyle Style.
  */
  @Test
  public void testMainClass() {
    String html = source.toHTML(new SEclipseStyle(), new SMainClass());
    assertEquals(html, "<pre style='color:#000000;background"
                       + ":#ffffff;'>"
                       + "public class Foo {\n    \n    "
                       + "public boolean bar(){\n      "
                       + "return false;\n      \n    }\n  }</pre>");
  }

  /**
  Tests SMainClass Highlighter with SEclipseStyle Style.
  */
  @Test
  public void testCurlyBracket() {
    String html = source.toHTML(new SEclipseStyle(), new SCurlyBracket());
    assertEquals(html, "<pre "
                       + "style='color:#000000;"
                       + "background:#ffffff;'>"
                       + "public class Foo {\n    \n    public boolean bar()"
                       + "{\n      return false;\n      \n    }\n  }</pre>");
  }

  /**
  Tests SKeyWord Highlighter with SEclipseStyle Style.
  */
  @Test
  public void testKeyWord() {
    String html = source.toHTML(new SEclipseStyle(), new SKeyWord());
    assertEquals(html, "<pre style='color:"
                       + "#000000;"
                       + "background:"
                       + "#ffffff;'>public "
                       + "<span style='color:#7f0055; font-weight:bold; '>class"
                       + "</span> Foo {\n    \n    public <span style='color:#7f0055;"
                       + " font-weight:bold; '>boolean</span> bar(){\n      "
                       + "<span style='color:#7f0055; font-weight:bold; '>return"
                       + "</span> <span style='color:#7f0055; font-weight:bold; '>"
                       + "false</span>;\n      \n    }\n  }</pre>");
  }

  /**
  Tests SModifier Highlighter with SEclipseStyle Style.
  */
  @Test
  public void testModifier() {
    String html = source.toHTML(new SEclipseStyle(), new SModifier());
    assertEquals(html, "<pre style="
                       + "'color:#000000;"
                       + "background:#ffffff;'>"
                       + "<span style='color:#7f0055; font-weight:bold; '>"
                       + "public</span> class Foo {\n    \n    <span style='color:#7f0055; "
                       + "font-weight:bold; '>public</span> boolean bar(){\n      "
                       + "return false;\n      \n    }\n  }</pre>");
  }

  /**
  Tests SPseudoVariable Highlighter with SEclipseStyle Style.
  */
  @Test
  public void testPseudoVariable() {
    String html = source.toHTML(new SEclipseStyle(), new SPseudoVariable());
    assertEquals(html, "<pre style="
                       + "'color:#000000;background:#ffffff;'>"
                       + "public class Foo {\n    \n    public boolean bar()"
                       + "{\n      return false;\n      \n    }\n  }</pre>");
  }

  /**
  Tests SString Highlighter with SEclipseStyle Style.
  */
  @Test
  public void testString() {
    String html = source.toHTML(new SEclipseStyle(), new SString());
    assertEquals(html, "<pre style='color:#000000;"
                       + "background:#ffffff;'>"
                       + "public class Foo {\n    \n    public boolean bar()"
                       + "{\n      return false;\n      \n    }\n  }</pre>");
  }
}

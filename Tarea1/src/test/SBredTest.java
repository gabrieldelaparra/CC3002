package test;

import org.junit.Before;
import org.junit.Test;
import sh4j.model.highlight.SClassName;
import sh4j.model.highlight.SCurlyBracket;
import sh4j.model.highlight.SKeyWord;
import sh4j.model.highlight.SMainClass;
import sh4j.model.highlight.SModifier;
import sh4j.model.highlight.SPseudoVariable;
import sh4j.model.highlight.SSemiColon;
import sh4j.model.highlight.SString;
import sh4j.model.style.SDarkStyle;
import sh4j.parser.SParser;
import sh4j.parser.model.SBlock;

import static org.junit.Assert.assertEquals;

/**
Bred Style Test Class.
*/
public class SBredTest {
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
    source = SParser.parse("public class MainClass\n{\n   "
                           + "public static void main( String args[] )\n   "
                           + "{ \n      GradeBook myGradeBook = new GradeBook(); "
                           + "\n\n      String courseName = \"Java \";\n      "
                           + "myGradeBook.displayMessage( courseName );\n   }\n\n}");
  }

  /**
  Tests several Highlighters with SDarkStyle Style.
  */
  @Test
  public void testPseudoVariable() {
    String html = source.toHTML(new SDarkStyle(),
                                new SClassName(),
                                new SCurlyBracket(),
                                new SKeyWord(),
                                new SMainClass(),
                                new SModifier(),
                                new SPseudoVariable(),
                                new SSemiColon(),
                                new SString());
    assertEquals(html, "<pre style='color:#d1d1d1;"
                       + "background:#000000;'>"
                       + "<span style='color:#e66170;"
                       + " font-weight:bold; '>"
                       + "public</span> "
                       + "<span style='color:#bb7977; '>class"
                       + "</span> MainClass "
                       + "<span style='color:#b060b0; '>{</span>"
                       + "\n    \n    <span style='color:#e66170; font-weight:bold; '>"
                       + "public</span> <span style='color:#bb7977; '>static</span> "
                       + "<span style='color:#bb7977; '>"
                       + "void</span> main(String args[])"
                       + "<span style='color:#b060b0; '>{</span>\n      "
                       + "GradeBook myGradeBook="
                       + "<span style='color:#bb7977; '>new</span> "
                       + "GradeBook()<span style="
                       + "'color:#b060b0; '>;</span>\n      "
                       + "String courseName=<span style='color:#00c4c4; '>\"Java \""
                       + "</span><span style="
                       + "'color:#b060b0; '>;</span>\n      "
                       + "myGradeBook.displayMessage(courseName"
                       + ")<span style='color:#b060b0; '>"
                       + ";</span>\n      \n    "
                       + "<span style='color:#b060b0; '>}"
                       + "</span>\n  "
                       + "<span style='color:#b060b0; '>}"
                       + "</span></pre>");
  }
}
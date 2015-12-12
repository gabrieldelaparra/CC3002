package test;

import org.junit.Before;
import org.junit.Test;
import sh4j.model.highlight.SClassName;
import sh4j.model.highlight.SCurlyBracket;
import sh4j.model.highlight.SDummy;
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
Dark Style Test Class.
*/
public class SHtmlDarkTest {
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
                           + "myGradeBook.displayMessage( courseName );\n   "
                           + "}\n\n}\npublic class Foo{ public boolean bar()"
                           + "{ super.foo(); return false;}}");
  }

  /**
  Test SClassName Highlight with SDarkStyle Style.
  */
  @Test
  public void testClassName() {
    String html = source.toHTML(new SDarkStyle(), new SClassName());
    assertEquals(html, "<pre style='color:#d1d1d1;background:"
                       + "#000000;'>"
                       + "public class MainClass {\n"
                       + "    \n"
                       + "    public static void main(String args[]){\n"
                       + "      GradeBook myGradeBook=new GradeBook();\n"
                       + "      String courseName=\"Java \";\n"
                       + "      myGradeBook.displayMessage(courseName);\n"
                       + "      \n"
                       + "    }\n"
                       + "  }public class Foo {\n"
                       + "    \n"
                       + "    public boolean bar(){\n"
                       + "      super.foo();\n"
                       + "      return false;\n"
                       + "      \n"
                       + "    }\n"
                       + "  }</pre>");
  }

  /**
  Test SCurlyBracket Highlight with SDarkStyle Style.
  */
  @Test
  public void testCurlyBracket() {
    String html = source.toHTML(new SDarkStyle(), new SCurlyBracket());
    assertEquals(html,
                 "<pre style='color:#d1d1d1;"
                 + "background:#000000;'>"
                 + "public class MainClass <span style='color:"
                 + "#b060b0; '>"
                 + "{</span>\n"
                 + "    \n"
                 + "    public static void main(String args[])"
                 + "<span style='color:#b060b0; '>{</span>\n"
                 + "      GradeBook myGradeBook=new GradeBook();\n"
                 + "      String courseName=\"Java \";\n"
                 + "      myGradeBook.displayMessage(courseName);\n"
                 + "      \n"
                 + "    <span style='color:#b060b0; '>}</span>\n"
                 + "  <span style='color:#b060b0; '>}</span>public class Foo "
                 + "<span style='color:#b060b0; '>{</span>\n"
                 + "    \n"
                 + "    public boolean bar()<span style='col"
                 + "or:#b060b0; '>{</span>\n"
                 + "      super.foo();\n"
                 + "      return false;\n"
                 + "      \n"
                 + "    <span style='color:#b060b0; '>}</span>\n"
                 + "  <span style='color:#b060b0; '>}</span></pre>");
  }

  /**
  Test SDummy Highlight with SDarkStyle Style.
  */
  @Test
  public void testDummy() {
    String html = source.toHTML(new SDarkStyle(), new SDummy());
    assertEquals(html, "<pre style='color:#d1d1d1;backg"
                       + "round:#000000;'>"
                       + "public class MainClass {\n"
                       + "    \n"
                       + "    public static void main(String args[]){\n"
                       + "      GradeBook myGradeBook=new GradeBook();\n"
                       + "      String courseName=\"Java \";\n"
                       + "      myGradeBook.displayMessage(courseName);\n"
                       + "      \n"
                       + "    }\n"
                       + "  }public class Foo {\n"
                       + "    \n"
                       + "    public boolean bar(){\n"
                       + "      super.foo();\n"
                       + "      return false;\n"
                       + "      \n"
                       + "    }\n"
                       + "  }</pre>");
  }

  /**
  Test SKeyWord Highlight with SDarkStyle Style.
  */
  @Test
  public void testKeyWord() {
    String html = source.toHTML(new SDarkStyle(), new SKeyWord());
    assertEquals(html,
                 "<pre style='color:#d1d1d1;background:#000000;'>"
                 + "public <span style='color:#bb7977; '>class</span> MainClass {\n"
                 + "    \n"
                 + "    public <span style='color:#bb7977; '>"
                 + "static</span> <span style='color:#bb7977; '>"
                 + "void</span> main(String args[]){\n"
                 + "      GradeBook myGradeBook="
                 + "<span style='color:#bb7977; '>new</span> GradeBook();\n"
                 + "      String courseName=\"Java \";\n"
                 + "      myGradeBook.displayMessage(courseName);\n"
                 + "      \n"
                 + "    }\n"
                 + "  }public <span style='color:#bb7977; '>class</span> Foo {\n"
                 + "    \n"
                 + "    public <span style='color:#bb7977; '>"
                 + "boolean</span> bar(){\n"
                 + "      <span style='color:#bb7977; '>"
                 + "super</span>.foo();\n"
                 + "      <span style='color:#bb7977; '>"
                 + "return</span> <span style='color:#bb7977; '>false</span>;\n"
                 + "      \n"
                 + "    }\n"
                 + "  }</pre>");
  }

  /**
  Test SMainClass Highlight with SDarkStyle Style.
  */
  @Test
  public void testMainClass() {
    String html = source.toHTML(new SDarkStyle(), new SMainClass());
    assertEquals(html, "<pre style='color:#d1d1d1;backgroun"
                       + "d:#000000;'>"
                       + "public class MainClass {\n"
                       + "    \n"
                       + "    public static void main(<span style='color:#bb7977; "
                       + "font-weight:bold; '>String</span> args[]){\n"
                       + "      GradeBook myGradeBook=new GradeBook();\n"
                       + "      <span style='colo"
                       + "r:#bb7977; font-weight:bold; '>"
                       + "String</span> courseName=\"Java \";\n"
                       + "      myGradeBook.displayMessage(courseName);\n"
                       + "      \n"
                       + "    }\n"
                       + "  }public class Foo {\n"
                       + "    \n"
                       + "    public boolean bar(){\n"
                       + "      super.foo();\n"
                       + "      return false;\n"
                       + "      \n"
                       + "    }\n"
                       + "  }</pre>");
  }

  /**
  Test SModifier Highlight with SDarkStyle Style.
  */
  @Test
  public void testModifier() {
    String html = source.toHTML(new SDarkStyle(), new SModifier());
    assertEquals(html,
                 "<pre style='color:#d1d1d1;backgr"
                 + "ound:#000000;'>"
                 + "<span style='color:#e66170; font-weight:bold; '>public</span>"
                 + " class MainClass {\n"
                 + "    \n"
                 + "    <span style='color:"
                 + "#e66170; font-weight:bold; '>"
                 + "public</span> static void main(String args[]){\n"
                 + "      GradeBook myGradeBook=new GradeBook();\n"
                 + "      String courseName=\"Java \";\n"
                 + "      myGradeBook.displayMessage(courseName);\n"
                 + "      \n"
                 + "    }\n"
                 + "  }<span style='color:#e66170; font-weig"
                 + "ht:bold; '>"
                 + "public</span> class Foo {\n"
                 + "    \n"
                 + "    <span style='c"
                 + "olor:#e66170; font-weight:bold; '>"
                 + "public</span> boolean bar(){\n"
                 + "      super.foo();\n"
                 + "      return false;\n"
                 + "      \n"
                 + "    }\n"
                 + "  }</pre>");
  }

  /**
  Test SPseudoVariable Highlight with SDarkStyle Style.
  */
  @Test
  public void testPseudoVariable() {
    String html = source.toHTML(new SDarkStyle(), new SPseudoVariable());
    assertEquals(html, "<pre style='color:#d1d1d1;back"
                       + "ground:#000000;'>"
                       + "public class MainClass {\n"
                       + "    \n"
                       + "    public static void main(String args[]){\n"
                       + "      GradeBook myGradeBook=new GradeBook();\n"
                       + "      String courseName=\"Java \";\n"
                       + "      myGradeBook.displayMessage(courseName);\n"
                       + "      \n"
                       + "    }\n"
                       + "  }public class Foo {\n"
                       + "    \n"
                       + "    public boolean bar(){\n"
                       + "      <span style='color:#e"
                       + "66170; font-weight:bold; '>"
                       + "super</span>.foo();\n"
                       + "      return false;\n"
                       + "      \n"
                       + "    }\n"
                       + "  }</pre>");
  }

  /**
  Test SSemiColon Highlight with SDarkStyle Style.
  */
  @Test
  public void testSemiColon() {
    String html = source.toHTML(new SDarkStyle(), new SSemiColon());
    assertEquals(html, "<pre style='color:#d1d1d1;back"
                       + "ground:#000000;'>"
                       + "public class MainClass {\n"
                       + "    \n"
                       + "    public static void main(String args[]){\n"
                       + "      GradeBook myGradeBook=new GradeBook()"
                       + "<span style='color:#b060b0; '>;</span>\n"
                       + "      String courseName=\"Java \"<span style="
                       + "'color:#b060b0; '>"
                       + ";</span>\n"
                       + "      myGradeBook.displayMessage(courseName)"
                       + "<span style='color:#b060b0; '>;</span>\n"
                       + "      \n"
                       + "    }\n"
                       + "  }public class Foo {\n"
                       + "    \n"
                       + "    public boolean bar(){\n"
                       + "      super.foo()<span s"
                       + "tyle='color:#b060b0; '>;</span>\n"
                       + "      return false<span style='color:"
                       + "#b060b0; '>;</span>\n"
                       + "      \n"
                       + "    }\n"
                       + "  }</pre>");
  }

  /**
  Test SString Highlight with SDarkStyle Style.
  */
  @Test
  public void testString() {
    String html = source.toHTML(new SDarkStyle(), new SString());
    assertEquals(html, "<pre style='color:#d1"
                       + "d1d1;background:#000000;'>"
                       + "public class MainClass {\n"
                       + "    \n"
                       + "    public static void main(String args[]){\n"
                       + "      GradeBook myGradeBook=new GradeBook();\n"
                       + "      String courseName=<span style='color:#00c4c4; '>"
                       + "\"Java \"</span>;\n"
                       + "      myGradeBook.displayMessage(courseName);\n"
                       + "      \n"
                       + "    }\n"
                       + "  }public class Foo {\n"
                       + "    \n"
                       + "    public boolean bar(){\n"
                       + "      super.foo();\n"
                       + "      return false;\n"
                       + "      \n"
                       + "    }\n"
                       + "  }</pre>");
  }
}
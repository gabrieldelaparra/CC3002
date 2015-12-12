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
import sh4j.model.style.SBredStyle;
import sh4j.parser.SParser;
import sh4j.parser.model.SBlock;

import static org.junit.Assert.assertEquals;

/**
Bred Style Test Class.
*/
public class SHtmlBredTest {
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
                           + "{ \n      GradeBook myGradeBook = new GradeBook();"
                           + " \n\n      String courseName = \"Java \";\n     "
                           + " myGradeBook.displayMessage( courseName );\n   "
                           + "}\n\n}\npublic class Foo{ public boolean bar(){ "
                           + "super.foo(); return false;}}");
  }

  /**
  Test SClassName Highlight with Bred Style.
  */
  @Test
  public void testClassName() {
    String html = source.toHTML(new SBredStyle(), new SClassName());
    assertEquals(html, "<pre style='co"
                       + "lor:#000000;background:#f1f0f0;'>"
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
  Test SCurlyBracket Highlight with Bred Style.
  */
  @Test
  public void testCurlyBracket() {
    String html = source.toHTML(new SBredStyle(), new SCurlyBracket());
    assertEquals(html,
                 "<pre style='color:#000000;backg"
                 + "round:#f1f0f0;'>"
                 + "public class MainClass <span style='co"
                 + "lor:#806030; '>{</span>\n"
                 + "    \n"
                 + "    public static void main(String args[])"
                 + "<span style='color:#806030; '>{</span>\n"
                 + "      GradeBook myGradeBook=new GradeBook();\n"
                 + "      String courseName=\"Java \";\n"
                 + "      myGradeBook.displayMessage(courseName);\n"
                 + "      \n"
                 + "    <span style='color:#"
                 + "806030; '>}</span>\n"
                 + "  <span style='color:#806030; '>}</s"
                 + "pan>public class Foo "
                 + "<span style='color:#806030; '>{</span>\n"
                 + "    \n"
                 + "    public boolean bar()<span style='color:#806030; '>{</span>\n"
                 + "      super.foo();\n"
                 + "      return false;\n"
                 + "      \n"
                 + "    <span style='color:#8"
                 + "06030; '>}</span>\n"
                 + "  <span style='color:#806030; '>"
                 + "}</span></pre>");
  }

  /**
  Test SDummy Highlight with Bred Style.
  */
  @Test
  public void testDummy() {
    String html = source.toHTML(new SBredStyle(), new SDummy());
    assertEquals(html, "<pre style='color:#000000;back"
                       + "ground:#f1f0f0;'>"
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
  Test SKeyWord Highlight with Bred Style.
  */
  @Test
  public void testKeyWord() {
    String html = source.toHTML(new SBredStyle(), new SKeyWord());
    assertEquals(html,
                 "<pre style='co"
                 + "lor:#000000;background:#f1f0f0;'>"
                 + "public <span style='color:#800040; '>"
                 + "class</span> MainClass {\n"
                 + "    \n"
                 + "    public <span style='color:#8"
                 + "00040; '>static</span> "
                 + "<span style='color:#800040; '>void</span> ma"
                 + "in(String args[]){\n"
                 + "      GradeBook myGradeBook=<span style='c"
                 + "olor:#800040; '>"
                 + "new</span> GradeBook();\n"
                 + "      String courseName=\"Java \";\n"
                 + "      myGradeBook.displayMessage(courseName);\n"
                 + "      \n"
                 + "    }\n"
                 + "  }public <span style='color:#800040; '>class</span> Foo {\n"
                 + "    \n"
                 + "    public <span style='color:"
                 + "#800040; '>boolean</span> bar(){\n"
                 + "      <span style='color:#8000"
                 + "40; '>super</span>.foo();\n"
                 + "      <span style='color:#8000"
                 + "40; '>return</span> "
                 + "<span style='color:#800040; '>false</span>;\n"
                 + "      \n"
                 + "    }\n"
                 + "  }</pre>");
  }

  /**
  Test SMainClass Highlight with Bred Style.
  */
  @Test
  public void testMainClass() {
    String html = source.toHTML(new SBredStyle(), new SMainClass());
    assertEquals(html, "<pre style='col"
                       + "or:#000000;background:#f1f0f0;'>"
                       + "public class MainClass {\n"
                       + "    \n"
                       + "    public static void main(<span style='color:#800040; '>"
                       + "String</span> args[]){\n"
                       + "      GradeBook myGradeBook=new GradeBook();\n"
                       + "      <span style='color:#8"
                       + "00040; '>String</span> "
                       + "courseName=\"Java \";\n"
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
  Test SModifier Highlight with Bred Style.
  */
  @Test
  public void testModifier() {
    String html = source.toHTML(new SBredStyle(), new SModifier());
    assertEquals(html,
                 "<pre style='color:#000"
                 + "000;background:#f1f0f0;'>"
                 + "<span style='color:#400000; font-weight:bold; '>"
                 + "public</span> class MainClass {\n"
                 + "    \n"
                 + "    <span style='color:#400"
                 + "000; font-weight:bold; '>"
                 + "public</span> static void main(String args[]){\n"
                 + "      GradeBook myGradeBook=new GradeBook();\n"
                 + "      String courseName=\"Java \";\n"
                 + "      myGradeBook.displayMessage(courseName);\n"
                 + "      \n"
                 + "    }\n"
                 + "  }<span style='color:#4000"
                 + "00; font-weight:bold; '>"
                 + "public</span> class Foo {\n"
                 + "    \n"
                 + "    <span style='color:#400000; font-w"
                 + "eight:bold; '>"
                 + "public</span> boolean bar(){\n"
                 + "      super.foo();\n"
                 + "      return false;\n"
                 + "      \n"
                 + "    }\n"
                 + "  }</pre>");
  }

  /**
  Test SPseudoVariable Highlight with Bred Style.
  */
  @Test
  public void testPseudoVariable() {
    String html = source.toHTML(new SBredStyle(), new SPseudoVariable());
    assertEquals(html, "<pre style='color:#000000;backgro"
                       + "und:#f1f0f0;'>"
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
                       + "      <span style='color:"
                       + "#400000; font-weight:bold; '>"
                       + "super</span>.foo();\n"
                       + "      return false;\n"
                       + "      \n"
                       + "    }\n"
                       + "  }</pre>");
  }

  /**
  Test SSemiColon Highlight with Bred Style.
  */
  @Test
  public void testSemiColon() {
    String html = source.toHTML(new SBredStyle(), new SSemiColon());
    assertEquals(html, "<pre style='color:#000000;b"
                       + "ackground:#f1f0f0;'>"
                       + "public class MainClass {\n"
                       + "    \n"
                       + "    public static void main(String args[]){\n"
                       + "      GradeBook myGradeBook=new GradeBook()"
                       + "<span style='color:#806030; '>;</span>\n"
                       + "      String courseName=\"Java \""
                       + "<span style='color:#806030; '>;</span>\n"
                       + "      myGradeBook.displayMessage(courseName)"
                       + "<span style='color:#806030; '>;</span>\n"
                       + "      \n"
                       + "    }\n"
                       + "  }public class Foo {\n"
                       + "    \n"
                       + "    public boolean bar(){\n"
                       + "      super.foo()<span style='color:#806"
                       + "030; '>;</span>\n"
                       + "      return false<span"
                       + " style='color:#806030; '>;</span>\n"
                       + "      \n"
                       + "    }\n"
                       + "  }</pre>");
  }

  /**
  Test SString Highlight with Bred Style.
  */
  @Test
  public void testString() {
    String html = source.toHTML(new SBredStyle(), new SString());
    assertEquals(html, "<pre style='color:#000000;background:"
                       + "#f1f0f0;'>"
                       + "public class MainClass {\n"
                       + "    \n"
                       + "    public static void main(String args[]){\n"
                       + "      GradeBook myGradeBook=new GradeBook();\n"
                       + "      String courseName=<span style='"
                       + "color:#e60000; '>"
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
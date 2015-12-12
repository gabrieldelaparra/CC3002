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
import sh4j.model.style.SEclipseStyle;
import sh4j.parser.SParser;
import sh4j.parser.model.SBlock;

import static org.junit.Assert.assertEquals;

/**
Eclipse Style Test Class.
*/
public class SHtmlEclipseTest {
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
                           + " myGradeBook.displayMessage( courseName );\n  "
                           + " }\n\n}\npublic class Foo{ public boolean bar(){"
                           + " super.foo(); return false;}}");
  }



  /**
  Test SClassName Highlight with SEclipseStyle Style.
  */
  @Test
  public void testClassName() {
//    assertEquals(SParser.parse("HardDisk").toHTML(new SEclipseStyle(), new SClassName()),"<pre style='color:#000000;background:#ffffff;'></pre>"); //ClassName
//    assertEquals("{",""); //CurlyBracket
//    assertEquals("class","");//KeyWord
//    assertEquals("String","");//MainClass
//    assertEquals("String string","");//MainClass
//    assertEquals("public","");//Modifier
//    assertEquals("this",""); //PseudoVariable
//    assertEquals(";",""); //Semicolon
//    assertEquals("\"string\"",""); //String
//    assertEquals("",""); //Body Alone

//    String html = source.toHTML(new SEclipseStyle(), new SClassName());
//    assertEquals(html, "<pre style="
//                       + "'color:#000000;background:#ffffff;'>"
//                       + "public class MainClass {\n"
//                       + "    \n"
//                       + "    public static void main(String args[]){\n"
//                       + "      GradeBook myGradeBook=new GradeBook();\n"
//                       + "      String courseName=\"Java \";\n"
//                       + "      myGradeBook.displayMessage(courseName);\n"
//                       + "      \n"
//                       + "    }\n"
//                       + "  }public class Foo {\n"
//                       + "    \n"
//                       + "    public boolean bar(){\n"
//                       + "      super.foo();\n"
//                       + "      return false;\n"
//                       + "      \n"
//                       + "    }\n"
//                       + "  }</pre>");
  }

  /**
  Test SCurlyBracket Highlight with SEclipseStyle Style.
  */
  @Test
  public void testCurlyBracket() {
    String html = source.toHTML(new SEclipseStyle(), new SCurlyBracket());
    assertEquals(html, "<pre style='color:"
                       + "#000000;background:#ffffff;'>"
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
  Test SDummy Highlight with SEclipseStyle Style.
  */
  @Test
  public void testDummy() {
    String html = source.toHTML(new SEclipseStyle(), new SDummy());
    assertEquals(html, "<pre style='color:#000000"
                       + ";background:#ffffff;'>"
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
  Test SKeyWord Highlight with SEclipseStyle Style.
  */
  @Test
  public void testKeyWord() {
    String html = source.toHTML(new SEclipseStyle(), new SKeyWord());
    assertEquals(html, "<pre style='color:#000000;background"
                       + ":#ffffff;'>"
                       + "public <span style='color:#7f0055; font-weight:bold;"
                       + " '>class</span> MainClass {\n"
                       + "    \n"
                       + "    public <span style='color:#7f0055; "
                       + "font-weight:bold; '>static</span> <span style='color:#7f0055;"
                       + " font-weight:bold; '>void</span> main(String args[]){\n"
                       + "      GradeBook myGradeBook=<span style='color:#7f0055;"
                       + " font-weight:bold; '>new</span> GradeBook();\n"
                       + "      String courseName=\"Java \";\n"
                       + "      myGradeBook.displayMessage(courseName);\n"
                       + "      \n"
                       + "    }\n"
                       + "  }public <span style='color:#7f0055; font-weight:bold; "
                       + "'>class</span> Foo {\n"
                       + "    \n"
                       + "    public <span style='color:#7f0055; font-weight:bold;"
                       + " '>boolean</span> bar(){\n"
                       + "      <span style='color:#7f0055; font-weight:"
                       + "bold; '>"
                       + "super</span>.foo();\n"
                       + "      <span style='color:#7f0055; font-"
                       + "weight:bold; '>"
                       + "return</span> <span style='color:"
                       + "#7f0055; font-weight:bold;"
                       + " '>false</span>;\n"
                       + "      \n"
                       + "    }\n"
                       + "  }</pre>");
  }

  /**
  Test SMainClass Highlight with SEclipseStyle Style.
  */
  @Test
  public void testMainClass() {
    String html = source.toHTML(new SEclipseStyle(), new SMainClass());
    assertEquals(html, "<pre style='color:#000000;back"
                       + "ground:#ffffff;'>public "
                       + "class MainClass {\n"
                       + "    \n"
                       + "    public static void main(<span style='color:#7f0055; "
                       + "font-weight:bold; '>String</span> args[]){\n"
                       + "      GradeBook myGradeBook=new GradeBook();\n"
                       + "      <span style='"
                       + "color:#7f0055; font-weight:bold; '>"
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
  Test SModifier Highlight with SEclipseStyle Style.
  */
  @Test
  public void testModifier() {
    String html = source.toHTML(new SEclipseStyle(), new SModifier());
    assertEquals(html, "<pre style='color:#000000;background"
                       + ":#ffffff;'>"
                       + "<span style='color:#7f0055; font-weight:bold; '>"
                       + "public</span> class MainClass {\n"
                       + "    \n"
                       + "    <span style='color:#7f0055; font-weight:bold; '>"
                       + "public</span> static void main(String args[]){\n"
                       + "      GradeBook myGradeBook=new GradeBook();\n"
                       + "      String courseName=\"Java \";\n"
                       + "      myGradeBook.displayMessage(courseName);\n"
                       + "      \n"
                       + "    }\n"
                       + "  }<span style="
                       + "'color:#7f0055; font-weight:bold; '>"
                       + "public</span> class Foo {\n"
                       + "    \n"
                       + "    <span style='color"
                       + ":#7f0055; font-weight:bold; '>"
                       + "public</span> boolean bar(){\n"
                       + "      super.foo();\n"
                       + "      return false;\n"
                       + "      \n"
                       + "    }\n"
                       + "  }</pre>");
  }

  /**
  Test SPseudoVariable Highlight with SEclipseStyle Style.
  */
  @Test
  public void testPseudoVariable() {
    String html = source.toHTML(new SEclipseStyle(), new SPseudoVariable());
    assertEquals(html, "<pre style='color:#000000"
                       + ";background:#ffffff;'>"
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
                       + "      <span style='color:#7f0055;"
                       + " font-weight:bold; '>"
                       + "super</span>.foo();\n"
                       + "      return false;\n"
                       + "      \n"
                       + "    }\n"
                       + "  }</pre>");
  }

  /**
  Test SSemiColon Highlight with SEclipseStyle Style.
  */
  @Test
  public void testSemiColon() {
    String html = source.toHTML(new SEclipseStyle(), new SSemiColon());
    assertEquals(html, "<pre style='color:#000000;background:#ffffff;'>"
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
  Test SString Highlight with SEclipseStyle Style.
  */
  @Test
  public void testString() {
    String html = source.toHTML(new SEclipseStyle(), new SString());
    assertEquals(html, "<pre style='color:"
                       + "#000000;background:#ffffff;'>"
                       + "public class MainClass {\n"
                       + "    \n"
                       + "    public static void main(String args[]){\n"
                       + "      GradeBook myGradeBook=new GradeBook();\n"
                       + "      String courseName=<span style='color:#2a00ff; '>"
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
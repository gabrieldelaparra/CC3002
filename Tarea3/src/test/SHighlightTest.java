package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;
import sh4j.model.browser.SClass;
import sh4j.model.browser.SMethod;
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
import sh4j.model.style.SDarkStyle;
import sh4j.model.style.SEclipseStyle;
import sh4j.parser.SClassParser;
import sh4j.parser.model.SCarriageReturn;

import java.util.List;

public class SHighlightTest {

  @Test
  public void eclipseNumbers() {
    List<SClass> cls = SClassParser.parse("class A{ public int foo(){\n int a;\n int b;\n int " +
        "c=a+b; return c; }}");
    SMethod method = cls.get(0).methods().get(0);
    String html = method.body().toHTML(new SEclipseStyle(), new SClassName(), new SCurlyBracket()
        , new SKeyWord(), new SMainClass(), new SModifier(), new SPseudoVariable(), new
        SSemiColon(), new SString());
    assertEquals("<pre style='color:#000000;background:#ffffff;'>\n<span " +
        "style='background:#f1f0f0;'>  1 </span>  <span style='color:#7f0055; font-weight:bold; " +
        "'>public</span> <span style='color:#7f0055; font-weight:bold; '>int</span> foo()<span " +
        "style='font-weight:bold; '>{</span>\n<span style='background:#f1f0f0;'>  2 </span>    " +
        "<span style='color:#7f0055; font-weight:bold; '>int</span> a<span " +
        "style='font-weight:bold; '>;</span>\n<span style='background:#f1f0f0;'>  3 </span>    " +
        "<span style='color:#7f0055; font-weight:bold; '>int</span> b<span " +
        "style='font-weight:bold; '>;</span>\n<span style='background:#f1f0f0;'>  4 </span>    " +
        "<span style='color:#7f0055; font-weight:bold; '>int</span> c=a + b<span " +
        "style='font-weight:bold; '>;</span>\n<span style='background:#f1f0f0;'>  5 </span>    " +
        "<span style='color:#7f0055; font-weight:bold; '>return</span> c<span " +
        "style='font-weight:bold; '>;</span>\n<span style='background:#f1f0f0;'>  6 </span>    " +
        "\n<span style='background:#f1f0f0;'>  7 </span>  <span style='font-weight:bold; " +
        "'>}</span></pre>", html);
  }

  @Test
  public void darkNumbers() {
    List<SClass> cls = SClassParser.parse("class A{ public int foo(){\n int a;\n int b;\n int " +
        "c=a+b; return c; }}");
    SMethod method = cls.get(0).methods().get(0);
    String html = method.body().toHTML(new SDarkStyle(), new SClassName(), new SCurlyBracket(),
        new SKeyWord(), new SMainClass(), new SModifier(), new SPseudoVariable(), new SSemiColon
            (), new SString());
    assertEquals("<pre style='color:#d1d1d1;background:#000000;'>\n<span " +
        "style='background:#f1f0f0;'>  1 </span>  <span style='color:#e66170; font-weight:bold; " +
        "'>public</span> <span style='color:#bb7977; '>int</span> foo()<span " +
        "style='color:#b060b0; '>{</span>\n<span style='background:#f1f0f0;'>  2 </span>    <span" +
        " style='color:#bb7977; '>int</span> a<span style='color:#b060b0; '>;</span>\n<span " +
        "style='background:#f1f0f0;'>  3 </span>    <span style='color:#bb7977; '>int</span> " +
        "b<span style='color:#b060b0; '>;</span>\n<span style='background:#f1f0f0;'>  4 </span>  " +
        "  <span style='color:#bb7977; '>int</span> c=a + b<span style='color:#b060b0; '>;" +
        "</span>\n<span style='background:#f1f0f0;'>  5 </span>    <span style='color:#bb7977; " +
        "'>return</span> c<span style='color:#b060b0; '>;</span>\n<span " +
        "style='background:#f1f0f0;'>  6 </span>    \n<span style='background:#f1f0f0;'>  7 " +
        "</span>  <span style='color:#b060b0; '>}</span></pre>", html);
  }

  @Test
  public void bredNumbers() {
    List<SClass> cls = SClassParser.parse("class A{ public int foo(){\n int a;\n int b;\n int " +
        "c=a+b; return c; }}");
    SMethod method = cls.get(0).methods().get(0);
    String html = method.body().toHTML(new SBredStyle(), new SClassName(), new SCurlyBracket(),
        new SKeyWord(), new SMainClass(), new SModifier(), new SPseudoVariable(), new SSemiColon
            (), new SString());
    assertEquals("<pre style='color:#000000;background:#f1f0f0;'>\n<span " +
        "style='background:#f1f0f0;'>  1 </span>  <span style='color:#400000; font-weight:bold; " +
        "'>public</span> <span style='color:#800040; '>int</span> foo()<span " +
        "style='color:#806030; '>{</span>\n<span style='background:#f1f0f0;'>  2 </span>    <span" +
        " style='color:#800040; '>int</span> a<span style='color:#806030; '>;</span>\n<span " +
        "style='background:#f1f0f0;'>  3 </span>    <span style='color:#800040; '>int</span> " +
        "b<span style='color:#806030; '>;</span>\n<span style='background:#f1f0f0;'>  4 </span>  " +
        "  <span style='color:#800040; '>int</span> c=a + b<span style='color:#806030; '>;" +
        "</span>\n<span style='background:#f1f0f0;'>  5 </span>    <span style='color:#800040; " +
        "'>return</span> c<span style='color:#806030; '>;</span>\n<span " +
        "style='background:#f1f0f0;'>  6 </span>    \n<span style='background:#f1f0f0;'>  7 " +
        "</span>  <span style='color:#806030; '>}</span></pre>", html);
  }

  @Test
  public void anotherComplianceTest() {
    assertFalse(new SPseudoVariable().needsHighLight("aasdfae"));
    assertFalse(new SString().needsHighLight("sadfaedsa"));

    assertEquals("<span style='color:#e60000; '>a</span>",
        new SString().highlight("a", new SBredStyle()));
    assertEquals("<span style='color:#00c4c4; '>a</span>",
        new SString().highlight("a", new SDarkStyle()));
    assertEquals("<span style='color:#2a00ff; '>a</span>",
        new SString().highlight("a", new SEclipseStyle()));
  }

  @Test
  public void complianceTests() {
    assertEquals("<span style='color:#800040; '>a</span>",
        new SMainClass().highlight("a", new SBredStyle()));
    assertEquals("<span style='color:#bb7977; font-weight:bold; '>a</span>",
        new SMainClass().highlight("a", new SDarkStyle()));
    assertEquals("<span style='color:#7f0055; font-weight:bold; '>a</span>",
        new SMainClass().highlight("a", new SEclipseStyle()));

    assertEquals(true, new SDummy().needsHighLight("a"));

    assertEquals("a", new SClassName().highlight("a", new SBredStyle()));
    assertEquals("a", new SClassName().highlight("a", new SDarkStyle()));
    assertEquals("a", new SClassName().highlight("a", new SEclipseStyle()));

    assertEquals("<span style='color:#400000; font-weight:bold;  '>a</span>",
        new SPseudoVariable().highlight("a", new SBredStyle()));
    assertEquals("<span style='color:#e66170; font-weight:bold; '>a</span>",
        new SPseudoVariable().highlight("a", new SDarkStyle()));
    assertEquals("<span style='color:#7f0055; font-weight:bold; '>a</span>",
        new SPseudoVariable().highlight("a", new SEclipseStyle()));

    assertEquals("bred", new SBredStyle().toString());
    assertEquals("dark", new SDarkStyle().toString());
    assertEquals("eclipse", new SEclipseStyle().toString());

    assertEquals("\n", new SCarriageReturn().toString());

  }

}

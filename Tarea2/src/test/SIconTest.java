package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import sh4j.model.browser.SClass;
import sh4j.model.browser.SMethod;
import sh4j.model.browser.SPackage;
import sh4j.model.browser.SProject;
import sh4j.parser.SClassParser;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

public class SIconTest {

  @Test
  public void packageIcons() {
    SProject project = new SProject();
    SPackage pkg = new SPackage("pkg");
    project.addPackage(pkg);
    System.out.println(pkg.icon());
    assertEquals(pkg.icon(), "./resources/pack_empty_co.gif");
    List<SClass> cls = SClassParser.parse("class A{} interface B{} ");
    pkg.addClass(cls.get(0));
    assertEquals(pkg.icon(), "./resources/package_mode.gif");
  }

  @Test
  public void complianceTests() {
    SProject project = new SProject();
    SPackage pkg = new SPackage("pkg");
    project.addPackage(pkg);
    assertEquals(new Font("Helvetica", Font.PLAIN, 12), pkg.font());
    assertEquals(Color.WHITE, pkg.background());
    assertEquals("./resources/package_mode.gif", project.icon());
    assertEquals(new Font("Helvetica", Font.PLAIN, 12), project.font());
    assertEquals(Color.WHITE, project.background());

    List<SClass> classes = SClassParser.parse("class A{}");
    SClass A = classes.get(0);
    assertEquals(Color.WHITE, A.background());

    List<SClass> cls = SClassParser.parse("class A{ void d(){} asd void e(){}}");
    A = cls.get(0);
    SMethod d = A.methods().get(0);
    assertEquals("d", d.toString());
    assertEquals(new Font("Helvetica", Font.PLAIN, 12), d.font());
  }


  @Test
  public void classIcons() {
    List<SClass> cls = SClassParser.parse("class A{} interface B{} ");
    SClass A = cls.get(0);
    SClass B = cls.get(1);
    assertEquals(A.font(), new Font("Helvetica", Font.PLAIN, 12));
    assertEquals(B.font(), new Font("Helvetica", Font.ITALIC, 12));
    assertEquals(A.icon(), "./resources/class_obj.gif");
    assertEquals(B.icon(), "./resources/int_obj.gif");

  }

  @Test
  public void methodColor() {
    List<SClass> cls = SClassParser.parse("class A{ public void a(){} private void b(){} " +
        "protected void c(){} void d(){}}");
    SClass A = cls.get(0);
    SMethod a = A.methods().get(0);
    SMethod b = A.methods().get(1);
    SMethod c = A.methods().get(2);
    SMethod d = A.methods().get(3);
    assertEquals("./resources/public_co.gif", a.icon());
    assertEquals("./resources/private_co.gif", b.icon());
    assertEquals("./resources/protected_co.gif", c.icon());
    assertEquals("./resources/default_co.gif", d.icon());

  }

  @Test
  public void testMethodBackgroundColor() {
    List<SClass> cls = SClassParser.parse("class A{ public void a(){int x=0;x++;x++;x++;x++;x++;" +
        "x++;x++;x++;x++;x++;x++;x++;x++;x++;x++;x++;x++;x++;x++;x++;x++;x++;x++;x++;x++;x++;x++;" +
        "x++;x++;x++;x++;x++;} public void b(){int x=1;x++;x++;x++;x++;x++;x++;x++;x++;x++;x++;" +
        "x++;x++;x++;x++;x++;x++;x++;x++;x++;x++;x++;x++;x++;x++;x++;x++;x++;x++;x++;x++;x++;x++;" +
        "x++;x++;x++;x++;x++;x++;x++;x++;x++;x++;x++;x++;x++;x++;x++;x++;x++;x++;x++;x++;x++;x++;" +
        "x++;x++;x++;x++;x++;x++;}public void c(){int x;}}");
    SClass A = cls.get(0);
    SMethod a = A.methods().get(0);
    SMethod b = A.methods().get(1);
    SMethod c = A.methods().get(2);
    assertEquals(Color.YELLOW, a.background());
    assertEquals(Color.RED, b.background());
    assertEquals(Color.WHITE, c.background());
  }
}

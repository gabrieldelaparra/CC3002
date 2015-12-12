package test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import sh4j.model.browser.SClass;
import sh4j.model.browser.SPackage;
import sh4j.model.browser.SProject;
import sh4j.model.command.SSortClassesByHierarchy;
import sh4j.model.command.SSortClassesByName;
import sh4j.model.command.SSortPackagesByName;
import sh4j.parser.SClassParser;

import java.util.List;

public class SSortTest {

  @Test
  public void packagesByName() {
    SProject project = new SProject();
    project.addPackage(new SPackage("b"));
    project.addPackage(new SPackage("a"));
    project.addPackage(new SPackage("d"));
    project.addPackage(new SPackage("c"));
    String[] expected = new String[]{"a", "b", "c", "d"};
    (new SSortPackagesByName()).executeOn(project);
    String[] result = toStringArray(project.packages().toArray());
    assertArrayEquals(expected, result);
  }

  @Test
  public void classesByName() {
    SProject project = new SProject();
    SPackage pkg = new SPackage("pack");
    project.addPackage(pkg);
    List<SClass> cls = SClassParser.parse(" class A{} class B{} class C{}");
    for (SClass c : cls) {
      pkg.addClass(c);
    }
    (new SSortClassesByName()).executeOn(project);
    String[] result = toStringArray(pkg.classes().toArray());
    String[] expected = new String[]{"A", "B", "C"};
    assertArrayEquals(expected, result);
  }

  @Test
  public void classesByHierarchy() {
    SProject project = new SProject();
    SPackage pkg = new SPackage("pack");
    project.addPackage(pkg);
    List<SClass> cls = SClassParser.parse(" class Z{} class B extends Z{} class A{}");
    for (SClass c : cls) {
      pkg.addClass(c);
    }
    (new SSortClassesByHierarchy()).executeOn(project);
    String[] result = toStringArray(pkg.classes().toArray());
    print(result);
    String[] expected = new String[]{"A", "Z", "B"};
    assertArrayEquals(expected, result);
  }

  @Test
  public void anotherClassesByHierarchy() {
    SProject project = new SProject();
    SPackage pkg = new SPackage("pack");
    project.addPackage(pkg);
    List<SClass> cls = SClassParser.parse(" class Z{} class F extends Z{} class W extends " +
        "Z{} class A{} class G extends A{}");
    for (SClass c : cls) {
      pkg.addClass(c);
    }
    (new SSortClassesByHierarchy()).executeOn(project);
    String[] result = toStringArray(pkg.classes().toArray());
    print(result);
    String[] expected = new String[]{"A", "G", "Z", "F", "W"};
    assertArrayEquals(expected, result);
    assertEquals("Object", cls.get(0).superClass());
    assertEquals("Z", cls.get(1).superClass());

    assertEquals("sh4j.model.command.SSortPackagesByName", new SSortPackagesByName().name());

  }

  private String[] toStringArray(Object[] array) {
    String[] result = new String[array.length];
    for (int i = 0; i < result.length; i++) {
      result[i] = array[i].toString();
    }
    return result;
  }

  private void print(Object[] objs) {
    for (Object o : objs) {
      System.out.print("\"" + o.toString() + "\",");
    }
    System.out.println();
  }
}

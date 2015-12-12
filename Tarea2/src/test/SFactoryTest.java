package test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import sh4j.model.browser.SFactory;
import sh4j.model.browser.SPackage;
import sh4j.model.browser.SProject;

import java.io.File;
import java.io.IOException;

public class SFactoryTest {

  private SProject project;

  @Before
  public void setUp() throws IOException {
    project = (new SFactory()).create("."+File.separator+"src");
  }

  @Test
  public void testPackage() {
    assertTrue(project.packages().size() > 0);
  }

  @Test
  public void testClassses() {
    SPackage pkg = project.get(File.separator+"test");
    assertTrue(pkg.classes().size() > 0);
  }

  @Test
  public void testClasses3() {
    SPackage pkg = project.get(File.separator+"testa");
    assertNull(pkg);
  }
}

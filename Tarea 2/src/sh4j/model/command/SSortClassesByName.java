package sh4j.model.command;

import sh4j.model.browser.SClass;
import sh4j.model.browser.SPackage;
import sh4j.model.browser.SProject;

import java.util.Comparator;

/**
 * Created by CLGADEL on 15/11/2015.
 */
public class SSortClassesByName extends SCommand {
  @Override
  public void executeOn(SProject project) {
    for (SPackage pkg : project.packages()) {
      pkg.classes().sort(ClassNameComparator);
    }
  }

  private static final Comparator<SClass> ClassNameComparator = new Comparator<SClass>() {
    @Override
    public int compare(final SClass s1, final SClass s2) {
      String s1Name = s1.className();
      String s2Name = s2.className();
      return s1Name.compareToIgnoreCase(s2Name);
    }
  };
}

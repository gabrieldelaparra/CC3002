package sh4j.model.command;

import sh4j.model.browser.SClass;
import sh4j.model.browser.SPackage;
import sh4j.model.browser.SProject;

import java.util.Comparator;
import java.util.List;

/**
 * Created by CLGADEL on 15/11/2015.
 */
public class SSortClassesByHierarchy extends SCommand {
  @Override
  public void executeOn(SProject project) {
    for (SPackage pkg : project.packages()) {
      pkg.classes().sort(ClassSuperNameComparator);
    }
  }

  private static final Comparator<SClass> ClassSuperNameComparator = new Comparator<SClass>() {
    @Override
    public int compare(final SClass s1, final SClass s2) {
      String s1SuperName = s1.superClassName();
      String s2SuperName = s2.superClassName();
      return s1SuperName.compareToIgnoreCase(s2SuperName);
    }
  };
}


package sh4j.model.command;

import sh4j.model.browser.SPackage;
import sh4j.model.browser.SProject;

import java.util.Comparator;

/**
 * Created by CLGADEL on 15/11/2015.
 */
public class SSortPackagesByName extends SCommand {
  @Override
  public void executeOn(SProject project) {
    project.packages().sort(PackageNameComparator);
  }

  private static final Comparator<SPackage> PackageNameComparator = new Comparator<SPackage>() {
    @Override
    public int compare(final SPackage s1, final SPackage s2) {
      String s1Name = s1.toString();
      String s2Name = s2.toString();
      return s1Name.compareToIgnoreCase(s2Name);
    }
  };
}

package sh4j.model.command;

import sh4j.model.browser.SPackage;
import sh4j.model.browser.SProject;

import java.util.Collections;
import java.util.Comparator;

/**
 * Created by CLGADEL on 15/11/2015.
 */
public class SSortPackagesByName extends SCommand {
  @Override
  public void executeOn(SProject project) {
    Collections.sort(project.packages(),
        new Comparator<SPackage>() {
          @Override
          public int compare(SPackage o1, SPackage o2) {
            return o1.toString().compareTo(o2.toString());
          }
        });
    project.changed();
  }
}

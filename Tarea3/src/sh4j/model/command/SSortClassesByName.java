package sh4j.model.command;

import sh4j.model.browser.SClass;
import sh4j.model.browser.SPackage;
import sh4j.model.browser.SProject;

import java.util.Collections;
import java.util.Comparator;

/**
 * Created by CLGADEL on 15/11/2015.
 */
public class SSortClassesByName extends SCommand {
  /**
   * Sorts the current package classes by Name.
   *
   * @param project Project which calls the Command.
   */
  @Override
  public void executeOn(SProject project) {

    for(SPackage pkg: project.packages()){
      Collections.sort(pkg.classes(),
          new Comparator<SClass>() {
            @Override
            public int compare(SClass o1, SClass o2) {
              return o1.toString().compareTo(o2.toString());
            }
          });
    }
    project.changed();
  }
}

package sh4j.model.command;

import sh4j.model.browser.SClass;
import sh4j.model.browser.SPackage;
import sh4j.model.browser.SProject;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by CLGADEL on 15/11/2015.
 */
public class SSortClassesByHierarchy extends SCommand {

  /**
   * Overriden method, takes True as Indentation. For UI use.
   *
   * @param project Project which calls the Command.
   */
  @Override
  public void executeOn(SProject project) {
    executeOn(project, true);
  }

  /**
   * Applies Sorting to the current class.
   *
   * @param project Project to be sorted.
   * @param indent  If the classes need to be indented.
   */
  public void executeOn(SProject project, boolean indent) {
    (new SSortClassesByName()).executeOn(project);
    for (final SPackage pkg : project.packages()) {
      Collections.sort(pkg.classes(),
          new Comparator<SClass>() {
            @Override
            public int compare(SClass o1, SClass o2) {
              String path1 = path(pkg.classes(), o1);
              String path2 = path(pkg.classes(), o2);
              return path1.compareTo(path2);
            }
          });
      for (SClass parent : pkg.classes()) {
        if (parent.isParent()) {
          for (SClass child : pkg.classes()) {
            if (!child.isParent()
                && path(pkg.classes(), child).contains(parent.className())
                && indent) {
              child.indent(true);
            }
          }
        }
      }
    }
    project.changed();
  }

  /**
   * Gets the Class Hierarchy as a String.
   *
   * @param cls      Package classes collection.
   * @param theClass Class whose Hierarchy is required.
   * @return Returns the Class Hierarchy.
   */
  private String path(List<SClass> cls, SClass theClass) {
    SClass parent = get(cls, theClass.superClass());
    if (parent == null) {
      return theClass.toString();
    } else {
      return path(cls, parent) + theClass.toString();
    }
  }

  /**
   * Gets the Class' parent Class.
   *
   * @param cls      Package Classes Collection.
   * @param theClass Class whose Parent Class is required.
   * @return Returns theClass parent's class.
   */
  private SClass get(List<SClass> cls, String theClass) {
    for (SClass c : cls) {
      if (c.toString().equals(theClass)) {
        c.setParent(true);
        return c;
      }
    }
    return null;
  }

}


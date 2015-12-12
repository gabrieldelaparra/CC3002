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

  private boolean indent = true;

  @Override
  public void executeOn(SProject project) {
    executeOn(project, indent);
  }

  public void executeOn(SProject project, boolean indent) {
    (new SSortClassesByName()).executeOn(project);
    for (final SPackage pkg : project.packages()) {
      Collections.sort(pkg.classes(),
          new Comparator<SClass>() {
            @Override
            public int compare(SClass o1, SClass o2) {
              String path1 = path(pkg.classes(), o1);
              String path2 = path(pkg.classes(), o2);
              int comp = path1.compareTo(path2);
              return comp;
            }
          });
      for (SClass parent : pkg.classes()) {
        if (parent.isParent()) {
          for (SClass child : pkg.classes()) {
            if (!child.isParent() && path(pkg.classes(), child).contains(parent.className()) &&
                indent) {
              child.indent(true);
            }
          }
        }
      }
    }
    project.changed();
  }

  public String path(List<SClass> cls, SClass theClass) {
    SClass parent = get(cls, theClass.superClass());
    if (parent == null) {
      return theClass.toString();
    } else {
      return path(cls, parent) + theClass.toString();
    }
  }

  public SClass get(List<SClass> cls, String theClass) {
    for (SClass c : cls) {
      if (c.toString().equals(theClass)) {
        c.setParent(true);
        return c;
      }
    }
    return null;
  }

}


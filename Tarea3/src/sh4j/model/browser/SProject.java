package sh4j.model.browser;

import java.util.ArrayList;
import java.util.List;

/**
 * SProject Class.
 *
 * @author Gabriel De La Parra
 * @version 1.0
 */
public class SProject extends SObject {
  /**
   * Packages Collection.
   */
  private final List<SPackage> packages;

  /**
   * Constructor.
   */
  public SProject() {
    packages = new ArrayList<SPackage>();
  }

  /**
   * Adds a new Package.
   *
   * @param pack Package to be added.
   */
  public void addPackage(SPackage pack) {
    packages.add(pack);
  }

  /**
   * Packages collection getter.
   *
   * @return Returns the package collection.
   */
  public List<SPackage> packages() {
    return packages;
  }

  /**
   * Gets a package by name.
   *
   * @param pkgName Name of the package to get.
   * @return Returns a Package by the name.
   */
  public SPackage get(String pkgName) {
    SPackage returnPackage = null;
    for (SPackage pkg : packages) {
      if (pkg.toString().equals(pkgName)) {
        returnPackage = pkg;
      }
    }
    return returnPackage;
  }
}

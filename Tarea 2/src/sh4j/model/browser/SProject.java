package sh4j.model.browser;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

/**
 * SProject Class.
 *
 * @author Gabriel De La Parra
 * @version 1.0
 */
public class SProject implements SObject {
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
//      else if (pkg.toString().replace("\\", "/").equals(pkgName)) {
//        returnPackage = pkg;
//      }
    }
    return returnPackage;
  }

  /**
   * UI Icon getter.
   *
   * @return Returns the UI Icon.
   */
  @Override
  public String icon() {
    return "./resources/package_mode.gif";
  }

  /**
   * UI Font getter.
   *
   * @return Returns the UI Font.
   */
  @Override
  public Font font() {
    return new Font("Helvetica", Font.PLAIN, 12);
  }

  /**
   * UI Background color getter.
   *
   * @return Returns the Background color.
   */
  @Override
  public Color background() {
    return Color.WHITE;
  }
}

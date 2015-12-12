package sh4j.model.browser;

import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Modifier;
import sh4j.parser.model.SBlock;

import java.awt.Color;

/**
 * SMethod Class.
 *
 * @author Gabriel De La Parra
 * @version 1.0
 */
public class SMethod extends SObject {

  /**
   * Method's declaration.
   */
  private final MethodDeclaration declaration;

  /**
   * Method's Body.
   */
  private final SBlock body;

  /**
   * Base constructor.
   *
   * @param methodDeclaration Method declaration.
   * @param body              Method's body.
   */
  public SMethod(MethodDeclaration methodDeclaration, SBlock body) {
    this.declaration = methodDeclaration;
    this.body = body;
  }

  /**
   * Gets the method's access modifier.
   *
   * @return Returns the current's method access modifier.
   */
  private String modifier() {
    String modfr = "default";
    for (Object obj : declaration.modifiers()) {
      if (obj instanceof Modifier) {
        Modifier modifier = (Modifier) obj;
        modfr = modifier.getKeyword().toString();
      }
    }
    return modfr;
  }

  /**
   * Gets the method's name.
   *
   * @return Returns the method's name.
   */
  private String name() {
    return declaration.getName().getIdentifier();
  }

  /**
   * Gets the method's body.
   *
   * @return Returns the methods body.
   */
  public SBlock body() {
    return body;
  }

  /**
   * Gets the method's name.
   *
   * @return Returns the current's method name.
   */
  public String toString() {
    return name();
  }

  /**
   * Gets the UI Icon.
   *
   * @return Returns an icon based on the access modifiers for the method.
   */
  @Override
  public String icon() {
    String modifier = modifier();
    if ("public".equals(modifier)) {
      return "./resources/public_co.gif";
    } else if ("private".equals(modifier)) {
      return "./resources/private_co.gif";
    } else if ("protected".equals(modifier)) {
      return "./resources/protected_co.gif";
    } else {
      return "./resources/default_co.gif";
    }
  }

  /**
   * Gets the Count of lines of code.
   *
   * @return Returns the Method's body number of lines.
   */
  public int getLinesOfCode() {
    String definition = this.body().toString();
    return definition.length() - definition.replace("\n", "").length();
  }

  /**
   * Get the UI Method background.
   *
   * @return Returns RED if over 50 lines, Yellow if over 30 and white if below 30.
   */
  @Override
  public Color background() {
    int length = getLinesOfCode();

    if (length > 50) {
      return Color.RED;
    } else if (length > 30) {
      return Color.YELLOW;
    } else {
      return super.background();
    }
  }
}

package sh4j.model.browser;

import org.eclipse.jdt.core.dom.TypeDeclaration;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

/**
 * SClass
 *
 * @author Gabriel De La Parra
 * @version 1.0
 */
public class SClass implements SObject {
  private final TypeDeclaration declaration;
  private final List<SMethod> methods;

  /**
   * Base Constructor.
   *
   * @param typeDeclaration Class constructor declaration.
   * @param methods         Class methods.
   */
  public SClass(TypeDeclaration typeDeclaration, List<SMethod> methods) {
    declaration = typeDeclaration;
    this.methods = methods;
  }

  /**
   * Get Methods Collections.
   *
   * @return Returns a List of Methods.
   */
  public List<SMethod> methods() {
    return methods;
  }

  /**
   * Gets the Class Name.
   *
   * @return Returns the current Class Name.
   */
  public String className() {
    return declaration.getName().getIdentifier();
  }

  /**
   * Checks if the Class is an interface.
   *
   * @return Returns true if the current class is an interface.
   */
  private boolean isInterface() {
    return declaration.isInterface();
  }

  /**
   * Gets the current's Super Class Name.
   *
   * @return Returns the current's class Super Class Name.
   */
  public String superClass() {
    if (declaration.getSuperclassType() == null) {
      return "Object";
    }
    return declaration.getSuperclassType().toString();
  }

  /**
   * Gets a concatenated SuperClass.Class Name.
   *
   * @return Returns a SuperClass.ClassName String.
   */
  public String superClassName() {
    if (declaration.getSuperclassType() == null) {
      return this.className();
    }
    return declaration.getSuperclassType().toString() + "." + this.className();
  }

  /**
   * Gets the current class name.
   *
   * @return Returns the current's class name.
   */
  public String toString() {
    return className();
  }

  /**
   * Gets the UI Icon for the class.
   *
   * @return Returns the resource reference to the icon.
   */
  @Override
  public String icon() {
    if (isInterface()) {
      return "./resources/int_obj.gif";
    } else {
      return "./resources/class_obj.gif";
    }
  }

  /**
   * Gets the UI Font for the class.
   *
   * @return Returns Italic if interface, otherwise, returns plain.
   */
  @Override
  public Font font() {
    if (isInterface()) {
      return new Font("Helvetica", Font.ITALIC, 12);
    } else {
      return new Font("Helvetica", Font.PLAIN, 12);
    }
  }

  /**
   * Gets the UI Background for the class.
   *
   * @return Returns black.
   */
  @Override
  public Color background() {
    return Color.WHITE;
  }
}
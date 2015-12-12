package sh4j.model.browser;

import org.eclipse.jdt.core.dom.TypeDeclaration;

import java.awt.Font;
import java.util.List;

/**
 * SClass
 *
 * @author Gabriel De La Parra
 * @version 1.0
 */
public class SClass extends SObject {
  private final TypeDeclaration declaration;
  private final List<SMethod> methods;
  private String className;
  private boolean isParent = false;

  /**
   * Base Constructor.
   *
   * @param typeDeclaration Class constructor declaration.
   * @param methods         Class methods.
   */
  public SClass(TypeDeclaration typeDeclaration, List<SMethod> methods) {
    declaration = typeDeclaration;
    getClassName();
    this.methods = methods;
  }

  private void getClassName() {
    this.className = declaration.getName().getIdentifier();
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
    return className;
  }

  /**
   * Checks if the Class is an interface.
   *
   * @return Returns true if the current class is an interface.
   */
  public boolean isInterface() {
    return declaration.isInterface();
  }

//  /**
//   * Gets a concatenated SuperClass.Class Name.
//   *
//   * @return Returns a SuperClass.ClassName String.
//   */
//  public String superClassName() {
//    if (declaration.getSuperclassType() == null) {
//      return this.className();
//    }
//    return declaration.getSuperclassType().toString() + "." + this.className();
//  }

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
      return super.font();
    }
  }

  public void indent(boolean indent) {
    getClassName();
    if (indent) {
      className = "    " + className;
    }
  }

  public boolean isParent() {
    return isParent;
  }

  public void setParent(final boolean isParent) {
    this.isParent = isParent;
  }

//  public void setName(final String s) {
//    className = className + "." + s;
//  }
}
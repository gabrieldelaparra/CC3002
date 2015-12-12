package sh4j.model.style;

/**
 Style Implementation: Dark.

 @author Gabriel De La Parra
 @version 1.0 */
public class SDarkStyle implements SStyle {

  /**
   Style name field.
   Meaning that there should be more than one line.
   */
  private static String name = "dark";

  /**
   Style's toString Method.
   Meaning that there should be more than one line.

   @return Style identifier.
   */
  public String toString() {
    return name;
  }

  /**
   Checks if text requires highlighting.
   Meaning that there should be more than one line.

   @param styleName style name.
   @return compares with current style name.
   */
  public boolean isStyle(String styleName) {
    return name.equals(styleName);
  }
}

package sh4j.model.style;

/**
 Style Implementation: Bred.

 @author Gabriel De La Parra
 @version 1.0 */
public class SBredStyle implements SStyle {

  /**
   Style name field.
  */
  private static String name = "bred";

  /**
   Style's toString Method.
    
   @return Style identifier.
   */
  public String toString() {
    return name;
  }

  /**
   Checks if text requires highlighting.
    
   @param styleName style name.
   @return compares with current style name.
   */
  public boolean isStyle(String styleName) {
    return name.equals(styleName);
  }
}


package sh4j.model.style;

/**
 SStyle Interface.

 @author Gabriel De La Parra
 @version 1.0 */
public interface SStyle {
  /**
   Style's to string Method.

   @return Style identifier.
   */
  String toString();

  /**
   Checks if text requires highlighting.

   @param styleName style name.
   @return compares with current style name.
   */
  boolean isStyle(String styleName);
}

package sh4j.model.browser;

import java.awt.Color;
import java.awt.Font;

/**
 * SObject base interface.
 */
public interface SObject {
  /**
   * Interface's Icon getter.
   *
   * @return Returns the UI Icon reference path.
   */
  String icon();

  /**
   * Interface's Font getter.
   *
   * @return Returns the UI Font.
   */
  Font font();

  /**
   * Interface's Background Color getter.
   *
   * @return Returns the UI Background Color.
   */
  Color background();

}

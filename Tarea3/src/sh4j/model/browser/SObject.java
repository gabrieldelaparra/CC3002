package sh4j.model.browser;

import java.awt.Color;
import java.awt.Font;
import java.util.Observable;

/**
 * SObject base class.
 */
public class SObject extends Observable {
  /**
   * SObject's Icon getter.
   *
   * @return Returns the UI Icon reference path.
   */
  public String icon(){
    return "";
  }

  /**
   * SObject's Font getter.
   *
   * @return Returns the UI Font.
   */
  public Font font(){
    return new Font("Helvetica", Font.PLAIN, 12);
  }

  /**
   * SObject's Background Color getter.
   *
   * @return Returns the UI Background Color.
   */
  public Color background(){
    return Color.WHITE;
  }

  /**
   * SObject's Observer implementation;
   */
  public void changed(){
    setChanged();
    notifyObservers();
  }

}

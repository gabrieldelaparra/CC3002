package sh4j.ui;

import sh4j.model.browser.SClass;
import sh4j.model.browser.SMethod;
import sh4j.model.browser.SPackage;

import javax.swing.JLabel;

/**
 * Created by CLGADEL on 12/12/2015.
 */
public class StatusBarSummary {
  public int LineCount;
  public int ClassCount;
  public String Warning = "";
  public String TextFormat = "Lines Count: %s; Class Count: %s; %s";
  public boolean displaySummary = true;
  private JLabel statusBar;

  /**
   * Constructor. Takes a Control to be updated.
   *
   * @param statusBar StatusBar's JLabel Control.
   */
  public StatusBarSummary(final JLabel statusBar) {
    this.statusBar = statusBar;
  }

  /**
   * Applies formatting to the StatusBar Text.
   * @return Formatted StatusBar's text.
   */
  public String GetText() {
    if (displaySummary) {
      return String.format(TextFormat, LineCount, ClassCount, Warning);
    }
    return "";
  }

  /**
   * Updates the StatusBar Text and displays the SClass Information.
   * @param cls SClass information to be displayed.
   */
  public void update(final SClass cls) {

  }

  /**
   * Updates the StatusBar Text and displays the SMethod Information.
   * @param method SMethod information to be displayed.
   */
  public void update(final SMethod method) {
    LineCount = method.getLinesOfCode();
    Warning = LineCount > 30 ? "Warning" : "";
    statusBar.setText(GetText());
  }

  /**
   * Updates the StatusBar Text and displays the SPackage Information.
   * @param pkg SPackage information to be displayed.
   */
  public void update(final SPackage pkg) {
    ClassCount = pkg.classes().size();
    statusBar.setText(GetText());
  }
}

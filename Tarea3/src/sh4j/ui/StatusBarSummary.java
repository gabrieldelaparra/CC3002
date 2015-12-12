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

  public StatusBarSummary(final JLabel statusBar) {
    this.statusBar = statusBar;
  }

  public String GetText() {
    if (displaySummary) {
      return String.format(TextFormat, LineCount, ClassCount, Warning);
    }
    return "";
  }

  public void update(final SClass cls) {

  }

  public void update(final SMethod method) {
    LineCount = method.getLinesOfCode();
    Warning = LineCount > 30 ? "Warning" : "";
    statusBar.setText(GetText());
  }

  public void update(final SPackage pkg) {
    ClassCount = pkg.classes().size();
    statusBar.setText(GetText());
  }
}

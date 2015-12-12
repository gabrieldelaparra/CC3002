package sh4j.model;

import sh4j.model.command.SSortClassesByHierarchy;
import sh4j.model.command.SSortClassesByName;
import sh4j.model.command.SSortPackagesByName;
import sh4j.model.highlight.SClassName;
import sh4j.model.highlight.SCurlyBracket;
import sh4j.model.highlight.SKeyWord;
import sh4j.model.highlight.SMainClass;
import sh4j.model.highlight.SModifier;
import sh4j.model.highlight.SPseudoVariable;
import sh4j.model.highlight.SSemiColon;
import sh4j.model.highlight.SString;
import sh4j.model.style.SEclipseStyle;
import sh4j.ui.SFrame;

import java.io.File;
import java.io.IOException;

/**
 * Main Entry Point.
 */
@SuppressWarnings("ALL")
public final class MainClass {
  /**
   * Base constructor.
   */
  private MainClass() {

  }

  /**
   * Main Entrance Method.
   *
   * @param args Arguments for the application.
   * @throws IOException Any IO Exception that could be thrown.
   */
  public static void main(String[] args) throws IOException {
    SFrame frame = new SFrame(new SEclipseStyle(), new SClassName(), new SCurlyBracket(), new
        SKeyWord(), new SMainClass(), new SModifier(), new SPseudoVariable(), new SSemiColon(),
        new SString());
    frame.pack();
    frame.setVisible(true);
    frame.addCommands(new SSortClassesByHierarchy(),
        new SSortClassesByName(),
        new SSortPackagesByName());
    System.out.println(File.separator);
  }
}

package sh4j.model.command;

import sh4j.model.browser.SProject;

/**
 * It represent a command that could be applied to a project.
 *
 * @author juampi
 */
public abstract class SCommand {

  /**
   * ExecuteOn base call for Commands. To be implemented by Child Classes.
   *
   * @param project Project which calls the Command.
   */
  public abstract void executeOn(SProject project);

  /**
   * Base Getter for Commands' Name.
   * @return Commands' Name.
   */
  public String name() {
    return this.getClass().getName();
  }
}

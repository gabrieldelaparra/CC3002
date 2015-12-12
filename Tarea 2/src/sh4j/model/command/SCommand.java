package sh4j.model.command;

import sh4j.model.browser.SProject;

/**
 * It represent a command that could be applied to a project.
 *
 * @author juampi
 */
public abstract class SCommand {

  public abstract void executeOn(SProject project);

  public String name() {
    return this.getClass().getName();
  }
}

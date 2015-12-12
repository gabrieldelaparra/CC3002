package sh4j.model.browser;

import sh4j.parser.SClassParser;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Create object of classes SMethod, SClass and SPackage.
 *
 * @author juampi
 */
public class SFactory {

  /**
   * Create a SProject based in a String path.
   */
  public SProject create(String path) throws IOException {
    File directory = new File(path);
    List<File> folders = new ArrayList<File>();
    fillFolders(folders, directory);
    SProject project = new SProject();
    for (File file : folders) {
      project.addPackage(createPackage(file, directory.getCanonicalPath()));
    }
    return project;
  }

  /**
   * Create an SPackage using a String path.
   */
  private SPackage createPackage(File root, String path) throws IOException {
    SPackage pack = new SPackage(root.getCanonicalPath().replace(path, ""));
    for (File file : root.listFiles()) {
      if (file.isFile() && file.getName().endsWith(".java")) {
        List<SClass> classes = createClass(file);
        for (SClass cls : classes) {
          pack.addClass(cls);
        }
      }
    }
    return pack;
  }

  /**
   * Create the classes inside the file passed as argument.
   */
  private List<SClass> createClass(File root) throws IOException {
    Path path = Paths.get(root.getPath());
    Charset charset = Charset.forName("ISO-8859-1");
    List<String> lines = Files.readAllLines(path, charset);
    StringBuilder code = new StringBuilder();
    for (String line : lines) {
      code.append(line);
    }
    return SClassParser.parse(code.toString());
  }

  /**
   * Fills folders with files.
   *
   * @param folders Folders collection.
   * @param root    Name of the root file.
   */
  private void fillFolders(List<File> folders, File root) {
    if (root.isDirectory()) {
      folders.add(root);
      for (File file : root.listFiles()) {
        fillFolders(folders, file);
      }
    }
  }
}

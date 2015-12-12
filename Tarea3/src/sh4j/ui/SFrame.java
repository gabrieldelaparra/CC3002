package sh4j.ui;

import sh4j.model.browser.SClass;
import sh4j.model.browser.SFactory;
import sh4j.model.browser.SMethod;
import sh4j.model.browser.SObject;
import sh4j.model.browser.SPackage;
import sh4j.model.browser.SProject;
import sh4j.model.command.SCommand;
import sh4j.model.highlight.SHighlighter;
import sh4j.model.style.SEclipseStyle;
import sh4j.model.style.SStyle;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * JBrowser. gdelaparra: 2015.12.12: Added StatusBar and Styles Menu.
 *
 * @author juampi
 */
@SuppressWarnings({"unchecked", "serial"})
public class SFrame extends JFrame implements Observer {

  protected SStyle style = new SEclipseStyle();
  private SHighlighter[] lighters;
  private JList<SPackage> packages;
  private JList<SClass> classes;
  private JList<SMethod> methods;
  private JEditorPane htmlPanel;
  private JMenu file;
  private JMenu hierarchyMenu;
  private SProject project;
  private boolean showLineNumbers;
  private JPanel auxPane;
  private JLabel statusBar;
  private StatusBarSummary sbSummary;

  /**
   * Create a browser, it will use the style and the lighters passed in the argutments.
   */
  public SFrame(SStyle style, SHighlighter... lighters) {
    super("CC3002 Browser");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false);
    setSize(450, 750);
    build();
    this.lighters = lighters;
    this.style = style;
    JMenuBar bar = new JMenuBar();

    file = new JMenu("Sort");
    bar.add(file);

    hierarchyMenu = new JMenu("Style");
    bar.add(hierarchyMenu);

    setJMenuBar(bar);

    sbSummary = new StatusBarSummary(statusBar);
  }

  /**
   * Add commands to the menu bar
   */
  public void addCommands(SCommand... commands) {
    for (SCommand c : commands) {
      addCommand(c);
    }
  }

  /**
   * Add Styles to the menu bar
   */
  public void addStyles(SStyle... styles) {
    for (SStyle c : styles) {
      addStyle(c);
    }
    addLineNumberCheckBoxMenu();
  }

  /**
   * Adds a checkBox menu item to toggle Line Numbers.
   */
  private void addLineNumberCheckBoxMenu() {
    hierarchyMenu.addSeparator();
    JCheckBoxMenuItem item = new JCheckBoxMenuItem("Line Numbers");
    item.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (project != null) {
          showLineNumbers = !showLineNumbers;
          style(style);
        }
      }
    });
    hierarchyMenu.add(item);
  }

  /**
   * Adds styles to Style's Menu and subscribe events.
   *
   * @param style Style to be added.
   */
  private void addStyle(final SStyle style) {
    JMenuItem item = new JMenuItem(style.toString());
    item.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent actionEvent) {
        if (project != null) {
          style(style);
        }
      }
    });
    hierarchyMenu.add(item);
  }

  /**
   * Adds Commans to the Command's Menu and subscribe events.
   *
   * @param command Command to be added.
   */
  private void addCommand(final SCommand command) {
    JMenuItem item = new JMenuItem(command.name());
    item.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (project != null) {
          command.executeOn(project);
        }
      }
    });
    file.add(item);
  }

  /**
   * Updates a List of objects with new Data.
   * @param list List to be updated.
   * @param data Data for the List.
   */
  public void update(@SuppressWarnings("rawtypes") JList list, @SuppressWarnings("rawtypes") List
      data) {
    list.setListData(data.toArray(new SObject[]{}));
    if (data.size() > 0) {
      list.setSelectedIndex(0);
    }
  }

  /**
   * Gnereates the UI with all controls.
   */
  private void build() {
    buildPathPanel();
    methods = new JList<SMethod>();
    addOn(methods, BorderLayout.EAST);
    classes = new JList<SClass>();
    addOn(classes, BorderLayout.CENTER);
    packages = new JList<SPackage>();
    addOn(packages, BorderLayout.WEST);

    buildAuxPane();
    buildHTMLPanel(auxPane);
    buildStatusBar();

    packages.addListSelectionListener(new ListSelectionListener() {
      @Override
      public void valueChanged(ListSelectionEvent e) {
        if (packages.getSelectedIndex() != -1) {
          update(classes, packages.getSelectedValue().classes());
          sbSummary.update(packages.getSelectedValue());
        }
      }
    });
    classes.addListSelectionListener(new ListSelectionListener() {
      @Override
      public void valueChanged(ListSelectionEvent e) {
        if (classes.getSelectedIndex() != -1) {
          update(methods, classes.getSelectedValue().methods());
          sbSummary.update(classes.getSelectedValue());
        }
      }
    });
    methods.addListSelectionListener(new ListSelectionListener() {
      @Override
      public void valueChanged(ListSelectionEvent e) {
        if (methods.getSelectedIndex() != -1) {
          SMethod method = methods.getSelectedValue();
          htmlPanel.setText(method.body().toHTML(showLineNumbers, style, lighters));
          sbSummary.update(method);
        }
      }
    });
  }

  /**
   * Generic method for adding panels for Packages, Class and Methods.
   * @param list List to be added.
   * @param direction Control's Location.
   */
  public void addOn(JList<? extends SObject> list, String direction) {
    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    list.setLayoutOrientation(JList.VERTICAL);
    list.setCellRenderer(new SItemRenderer());
    JScrollPane pane = new JScrollPane(list);
    pane.setMinimumSize(new Dimension(200, 200));
    pane.setPreferredSize(new Dimension(200, 200));
    pane.setMaximumSize(new Dimension(200, 200));
    getContentPane().add(pane, direction);
  }

  /**
   * Open Directory Dialog and Label with Directory Name in a Control for the UI.
   */
  private void buildPathPanel() {
    JPanel pathPanel = new JPanel();
    pathPanel.setLayout(new BorderLayout());
    final JTextField pathField = new JTextField();
    pathField.setEnabled(false);
    JButton browseButton = new JButton("Browse");
    browseButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Select a root folder");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        if (chooser.showOpenDialog(SFrame.this) == JFileChooser.APPROVE_OPTION) {
          pathField.setText(chooser.getSelectedFile().getPath());
          try {
            project = new SFactory().create(chooser.getSelectedFile().getPath());
            project.addObserver(SFrame.this);
            update(packages, project.packages());
            if (project.packages().size() > 0) {
              packages.setSelectedIndex(0);
            }
          } catch (IOException ex) {

          }
        }
      }
    });
    pathPanel.add(pathField, BorderLayout.CENTER);
    pathPanel.add(browseButton, BorderLayout.EAST);
    getContentPane().add(pathPanel, BorderLayout.NORTH);
  }

  /**
   * Auxiliary Panel for adding the HTML Panel and the Status Bar.
   */
  public void buildAuxPane() {
    auxPane = new JPanel();
    auxPane.setLayout(new BorderLayout());
    auxPane.setMinimumSize(new Dimension(600, 300));
    auxPane.setPreferredSize(new Dimension(600, 300));
    auxPane.setMaximumSize(new Dimension(600, 300));
    getContentPane().add(auxPane, BorderLayout.SOUTH);
  }

  /**
   * HTML Panel Generator.
   * @param parentPane Where the Control should be added.
   */
  public void buildHTMLPanel(final JPanel parentPane) {
    htmlPanel = new JEditorPane();
    htmlPanel.setEditable(false);
    htmlPanel.setMinimumSize(new Dimension(600, 300));
    htmlPanel.setPreferredSize(new Dimension(600, 300));
    htmlPanel.setMaximumSize(new Dimension(600, 300));
    htmlPanel.setContentType("text/html");

    parentPane.add(new JScrollPane(htmlPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS), BorderLayout.CENTER);
  }

  /**
   * StatusBar control generation.
   */
  public void buildStatusBar() {
    statusBar = new JLabel();
    statusBar.setText("JBrowser: Please browse for a path to continue");
    statusBar.setBorder(new BevelBorder(BevelBorder.LOWERED));
    statusBar.setPreferredSize(new Dimension(getWidth(), 16));
    statusBar.setLayout(new BoxLayout(statusBar, BoxLayout.X_AXIS));
    auxPane.add(statusBar, BorderLayout.SOUTH);
  }

  /**
   * Updates for the project based on a new selected Directory.
   * @param project Project and its internal contect to be updated.
   */
  public void update(SProject project) {
    SPackage pkg = packages.getSelectedValue();
    SClass cls = classes.getSelectedValue();
    SMethod method = methods.getSelectedValue();
    packages.setListData(project.packages().toArray(new SPackage[]{}));
    packages.setSelectedValue(pkg, true);
    if (pkg != null) {
      classes.setListData(pkg.classes().toArray(new SClass[]{}));
      classes.setSelectedValue(cls, true);
      if (cls != null) {
        methods.setListData(cls.methods().toArray(new SMethod[]{}));
        methods.setSelectedValue(method, true);
      }
    }
  }

  /**
   * Styles the HTMLPanel contents.
   * @param style Style to be applied.
   */
  public void style(SStyle style) {
    this.style = style;
    SMethod method = methods.getSelectedValue();
    htmlPanel.setText(method.body().toHTML(showLineNumbers, style, lighters));
  }

  /**
   * Observable Update Implementation for the UI.
   * @param observable Project which would be updated.
   * @param updatableObject Not Used.
   */
  @Override
  public void update(Observable observable, Object updatableObject) {
    if (observable instanceof SProject) {
      SProject project = (SProject) observable;
      update(project);
    }
  }
}

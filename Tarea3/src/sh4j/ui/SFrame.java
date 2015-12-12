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

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * JBrowser
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

    hierarchyMenu = new JMenu("Highlight");
    bar.add(hierarchyMenu);
    setJMenuBar(bar);
  }

  /**
   * Add commands to the menu bar
   */
  public void addCommands(SCommand... cmds) {
    for (SCommand c : cmds) {
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
  }

  private void addStyle(final SStyle c) {
    JMenuItem item = new JMenuItem(c.toString());
    item.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (project != null) {
          style(c);
        }
      }
    });
    hierarchyMenu.add(item);
  }

  private void addCommand(final SCommand c) {
    JMenuItem item = new JMenuItem(c.name());
    item.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (project != null) {
          c.executeOn(project);
        }
      }
    });
    file.add(item);
  }

  public void update(@SuppressWarnings("rawtypes") JList list, @SuppressWarnings("rawtypes") List
      data) {
    list.setListData(data.toArray(new SObject[]{}));
    if (data.size() > 0) {
      list.setSelectedIndex(0);
    }
  }

  private void build() {
    buildPathPanel();
    methods = new JList<SMethod>();
    addOn(methods, BorderLayout.EAST);
    classes = new JList<SClass>();
    addOn(classes, BorderLayout.CENTER);
    packages = new JList<SPackage>();
    addOn(packages, BorderLayout.WEST);
    htmlPanel = buildHTMLPanel(BorderLayout.SOUTH);
    packages.addListSelectionListener(new ListSelectionListener() {
      @Override
      public void valueChanged(ListSelectionEvent e) {
        if (packages.getSelectedIndex() != -1) {
          update(classes, packages.getSelectedValue().classes());
        }
      }
    });
    classes.addListSelectionListener(new ListSelectionListener() {
      @Override
      public void valueChanged(ListSelectionEvent e) {
        if (classes.getSelectedIndex() != -1) {
          update(methods, classes.getSelectedValue().methods());
        }
      }
    });
    methods.addListSelectionListener(new ListSelectionListener() {
      @Override
      public void valueChanged(ListSelectionEvent e) {
        if (methods.getSelectedIndex() != -1) {
          SMethod method = methods.getSelectedValue();
          htmlPanel.setText(method.body().toHTML(style, lighters));
        }
      }
    });

  }

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

  public JEditorPane buildHTMLPanel(String direction) {
    JEditorPane htmlPanel = new JEditorPane();
    htmlPanel.setEditable(false);
    htmlPanel.setMinimumSize(new Dimension(600, 300));
    htmlPanel.setPreferredSize(new Dimension(600, 300));
    htmlPanel.setMaximumSize(new Dimension(600, 300));
    htmlPanel.setContentType("text/html");
    getContentPane().add(new JScrollPane(htmlPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS), direction);
    return htmlPanel;
  }

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

  public void style(SStyle style) {
    this.style = style;
    SMethod method = methods.getSelectedValue();
    htmlPanel.setText(method.body().toHTML(style, lighters));
  }

  @Override
  public void update(Observable o, Object arg) {
    if (o instanceof SProject) {
      SProject project = (SProject) o;
      update(project);
    }
  }
}

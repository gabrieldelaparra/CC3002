package sh4j.ui;

import sh4j.model.browser.SClass;
import sh4j.model.browser.SFactory;
import sh4j.model.browser.SMethod;
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
@SuppressWarnings("ALL")
public class SFrame extends JFrame {

  private SStyle style = new SEclipseStyle();
  private final SHighlighter[] lighters;
  private JList<SPackage> packages;
  private JList<SClass> classes;
  private JList<SMethod> methods;
  private JEditorPane htmlPanel;
  private final JMenu file;
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

  private void update(JList list, List data) {
    list.setListData(data.toArray());
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

  private void addOn(JList list, String direction) {
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
            update(packages, project.packages());
            if (project.packages().size() > 0) {
              packages.setSelectedIndex(0);
            }
          } catch (IOException ex) {
//throw ?
          }
        }
      }
    });
    pathPanel.add(pathField, BorderLayout.CENTER);
    pathPanel.add(browseButton, BorderLayout.EAST);
    getContentPane().add(pathPanel, BorderLayout.NORTH);
  }

  private JEditorPane buildHTMLPanel(String direction) {
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
}

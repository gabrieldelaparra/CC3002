package sh4j.ui;

import sh4j.model.highlight.*;
import sh4j.model.style.SBredStyle;
import sh4j.model.style.SDarkStyle;
import sh4j.model.style.SEclipseStyle;
import sh4j.model.style.SStyle;
import sh4j.parser.SParser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SFrame extends JFrame {
  private JTextArea sourcePanel;
  private JEditorPane htmlPanel;
  private JComboBox<SStyle> styleBox;

  private SHighlighter[] lighters;

  public SFrame(SHighlighter... gl) {
    super("CC3002 syntax highlighting for JAVA");
    setMinimumSize(getMinimunSize());
    setPreferredSize(getMinimunSize());
    setMaximumSize(getMinimunSize());
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    buildHTMLPanel();
    buildSourcePanel();
    buildStyleBox();
    lighters = gl;
  }

  public void buildSourcePanel() {
    sourcePanel = new JTextArea();
    sourcePanel.setEditable(true);
    sourcePanel.setPreferredSize(new Dimension(500, 250));
    add(new JScrollPane(sourcePanel,
                        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                        JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS),
        BorderLayout.CENTER);
  }

  public void buildHTMLPanel() {
    htmlPanel = new JEditorPane();
    htmlPanel.setEditable(false);
    htmlPanel.setContentType("text/html");
    htmlPanel.setPreferredSize(new Dimension(500, 250));
    add(new JScrollPane(htmlPanel,
                        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                        JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS),
        BorderLayout.SOUTH);
  }

  public void buildStyleBox() {
    styleBox = new JComboBox<SStyle>();
    styleBox.addItem(new SEclipseStyle());
    styleBox.addItem(new SBredStyle());
    styleBox.addItem(new SDarkStyle());
    styleBox.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent event) {
        SStyle style = (SStyle) styleBox.getSelectedItem();
        htmlPanel.setText(SParser.parse(sourcePanel.getText())
                              .toHTML(style, lighters));
      }
    });
    add(styleBox, BorderLayout.NORTH);
  }

  public Dimension getMinimunSize() {
    return new Dimension(500, 600);
  }

  public static void main(String[] args) {
    new SFrame(new SClassName(),
               new SCurlyBracket(),
               new SKeyWord(),
               new SMainClass(),
               new SModifier(),
               new SPseudoVariable(),
               new SSemiColon(),
               new SString()).setVisible(true);
  }
}

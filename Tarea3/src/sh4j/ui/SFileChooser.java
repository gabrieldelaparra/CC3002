package sh4j.ui;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class SFileChooser extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JButton go;
    private JFileChooser chooser;
    private String choosertitle;

    public SFileChooser() {
        go = new JButton("Do it");
        go.addActionListener(this);
        add(go);
    }

    public void actionPerformed(ActionEvent e) {
        chooser = new JFileChooser(); 
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle(choosertitle);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) { 
            System.out.println("getCurrentDirectory(): " 
                    +  chooser.getCurrentDirectory());
            System.out.println("getSelectedFile() : " 
                    +  chooser.getSelectedFile());
        }
        else {
            System.out.println("No Selection ");
        }
    }

    public Dimension getPreferredSize(){
        return new Dimension(200, 200);
    }
}

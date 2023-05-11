package GUI.infrastructuredesign;

import controllers.InfrastructureDesignController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;


public class FileOpslaan extends JFrame implements ActionListener {
    private JButton button;
    private InfrastructureDesignController controller;
    private InfrastructureDesignGUI GUI;

    public FileOpslaan(InfrastructureDesignGUI Gui){
        this.GUI = Gui;
        this.controller = new InfrastructureDesignController(this.GUI);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());

        button = new JButton("Select File");
        button.addActionListener(this);

        this.add(button);
        this.pack();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(button)) {
            System.out.println("knop werkt");
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(".")); //sets current directory
            int response = fileChooser.showSaveDialog(GUI); //select file to open
            //int response = fileChooser.showSaveDialog(null); //select file to save

            if(response == JFileChooser.APPROVE_OPTION) {
                String file = fileChooser.getSelectedFile().getAbsolutePath();
                try {
                    controller.saveInfrastructureDesign(file);
                    System.out.println(this.controller.getCurrentlyActiveDesign());
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                this.dispose();
            }
        }
    }
}

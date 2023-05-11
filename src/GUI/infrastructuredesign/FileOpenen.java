package GUI.infrastructuredesign;

import controllers.InfrastructureDesignController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileOpenen extends JFrame implements ActionListener {
    private JButton button;
    private InfrastructureDesignController controller;
    private InfrastructureDesignGUI GUI;

    public FileOpenen(InfrastructureDesignGUI Gui){
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
            int response = fileChooser.showOpenDialog(null); //select file to open
            //int response = fileChooser.showSaveDialog(null); //select file to save

            if(response == JFileChooser.APPROVE_OPTION) {
                //File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                String file = fileChooser.getSelectedFile().getAbsolutePath();
                try {
                    controller.loadInfrastructureDesign(file);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                this.dispose();
            }
        }
    }
}

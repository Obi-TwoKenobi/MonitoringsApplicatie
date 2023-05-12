package GUI.infrastructuredesign;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import controllers.InfrastructureDesignController;

public class InfrastructureDesignGUI extends JDialog implements ActionListener {

    private final int GUI_WIDTH = 1280;
    private final int GUI_HEIGHT = 720;

    private final int SCROLL_SPEED = 16;

    private JMenuBar topMenubar;
    private JMenu fileMenu;
    private JMenu generateOptimalDesignMenu;

    private JMenuItem fileMenuLoadDesignItem;
    private JMenuItem fileMenuSaveDesignItem;
    private JMenuItem fileMenuCloseDesignItem;

    private InfrastructureItemSelectionContainer itemSelectionContainer;
    private InfrastructureDesignContainer designContainer;
    private InfrastructureDesignStatisticsContainer designStatisticsContainer;

    private InfrastructureDesignController controller;


    public InfrastructureDesignGUI(JFrame parent) {
        super(parent, false);
        this.controller = new InfrastructureDesignController(this);

        this.fileMenu = new JMenu("Bestand");
        this.generateOptimalDesignMenu = new JMenu("ontwerp genereren");

        this.fileMenuLoadDesignItem = new JMenuItem("Bestand openen");
        fileMenuLoadDesignItem.addActionListener(this);
        this.fileMenuSaveDesignItem = new JMenuItem("bestand opslaan");
        fileMenuSaveDesignItem.addActionListener(this);
        this.fileMenuCloseDesignItem = new JMenuItem("Sluiten");

        this.itemSelectionContainer = new InfrastructureItemSelectionContainer(this.controller);
        JScrollPane itemSelectionPaneScrollable = new JScrollPane(itemSelectionContainer);
        itemSelectionPaneScrollable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        itemSelectionPaneScrollable.getVerticalScrollBar().setUnitIncrement(SCROLL_SPEED);
        this.designContainer = new InfrastructureDesignContainer(this.controller);
        this.designStatisticsContainer = new InfrastructureDesignStatisticsContainer(this, this.controller);

        this.topMenubar = new JMenuBar();
        this.topMenubar.add(fileMenu);
        this.fileMenu.add(fileMenuLoadDesignItem);
        this.fileMenu.add(fileMenuSaveDesignItem);
        this.fileMenu.add(fileMenuCloseDesignItem);

        this.topMenubar.add(generateOptimalDesignMenu);


        this.setJMenuBar(topMenubar);
        this.setSize(GUI_WIDTH, GUI_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.setTitle("Infrastructure monitoring - ontwerp - mijnontwerp.ngio");

        this.add(itemSelectionPaneScrollable, BorderLayout.WEST);
        this.add(designContainer, BorderLayout.CENTER);
        this.add(designStatisticsContainer, BorderLayout.SOUTH);
    }


    public InfrastructureDesignContainer getDesignContainer() {
        return this.designContainer;
    }

    public InfrastructureDesignStatisticsContainer getStatisticsContainer() {
        return this.designStatisticsContainer;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(fileMenuSaveDesignItem)) {
            JFileChooser fileChooser = new JFileChooser();
            FileFilter filter = new FileNameExtensionFilter("Designs", "NerdyGadgets");
            fileChooser.setCurrentDirectory(new File("."));
            fileChooser.addChoosableFileFilter(filter);
            fileChooser.setFileFilter(filter);
            int response = fileChooser.showSaveDialog(null);

            if (response == JFileChooser.APPROVE_OPTION){
                File selectedFile = fileChooser.getSelectedFile();
                try {
                   this.controller.saveInfrastructureDesign(String.valueOf(selectedFile));
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        }
        if (e.getSource().equals(fileMenuLoadDesignItem)){
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("."));
            int returnValue = fileChooser.showOpenDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                try {
                    this.controller.loadInfrastructureDesign(String.valueOf(selectedFile));
                    this.designStatisticsContainer.updateView();
                    this.designContainer.updateView();
                    this.revalidate();
                    this.repaint();
                } catch (IOException | ClassNotFoundException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }
}

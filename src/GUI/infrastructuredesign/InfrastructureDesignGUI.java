package GUI.infrastructuredesign;
import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controllers.InfrastructureDesignController;

public class InfrastructureDesignGUI extends JDialog {

    private final int GUI_WIDTH = 1280;
    private final int GUI_HEIGHT = 720;

    private JMenuBar topMenubar;
    private JMenu fileMenu;
    private JMenu generateOptimalDesignMenu;

    private JMenuItem fileMenuOpenDesignItem;
    private JMenuItem fileMenuSaveDesignItem;
    private JMenuItem fileMenuCloseDesignItem;

    private InfrastructureItemSelectionContainer itemSelectionContainer;
    private InfrastructureDesignContainer designContainer;
    private InfrastructureDesignStatisticsContainer designStatisticsContainer;

    private InfrastructureDesignController controller;


    public InfrastructureDesignGUI(JFrame parent){
        super(parent, true);
        this.controller = new InfrastructureDesignController(this);

        this.fileMenu = new JMenu("Bestand");
        this.generateOptimalDesignMenu = new JMenu("ontwerp genereren");

        this.fileMenuOpenDesignItem = new JMenuItem("Bestand openen");
        this.fileMenuSaveDesignItem = new JMenuItem("bestand opslaan");
        this.fileMenuCloseDesignItem = new JMenuItem("Sluiten");

        this.itemSelectionContainer = new InfrastructureItemSelectionContainer(this.controller);
        this.designContainer = new InfrastructureDesignContainer(this.controller);
        this.designStatisticsContainer = new InfrastructureDesignStatisticsContainer(this, this.controller);

        this.topMenubar = new JMenuBar();
        this.topMenubar.add(fileMenu);
        this.fileMenu.add(fileMenuOpenDesignItem);
        this.fileMenu.add(fileMenuSaveDesignItem);
        this.fileMenu.add(fileMenuCloseDesignItem);

        this.topMenubar.add(generateOptimalDesignMenu);


        this.setJMenuBar(topMenubar);
        this.setSize(GUI_WIDTH, GUI_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.setTitle("Infrastructure monitoring - ontwerp - mijnontwerp.ngio");

        this.add(itemSelectionContainer, BorderLayout.WEST);
        this.add(designContainer, BorderLayout.CENTER);
        this.add(designStatisticsContainer, BorderLayout.SOUTH);
    }


    public InfrastructureDesignContainer getDesignContainer(){
        return this.designContainer;
    }
    
    public InfrastructureDesignStatisticsContainer getStatisticsContainer(){
        return this.designStatisticsContainer;
    }
}
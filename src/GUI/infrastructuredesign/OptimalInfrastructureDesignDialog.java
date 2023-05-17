package GUI.infrastructuredesign;

import controllers.InfrastructureDesignController;

import javax.swing.*;
import java.awt.*;

public class OptimalInfrastructureDesignDialog extends JDialog {
    private final int GUI_WIDTH = 1280;
    private final int GUI_HEIGHT = 720;
    private JLabel availibilityPercentageLabel;
    private JTextField availibilityPercentageField;
    private JButton generateDesignButton, closeButton;
    private JFrame parent;
    private InfrastructureDesignController controller;

    public OptimalInfrastructureDesignDialog(JFrame parent, InfrastructureDesignController controller) {
        super(parent, false);

        this.setSize(GUI_WIDTH, GUI_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setLayout(new BoxLayout());
        this.setTitle("Infrastructure monitoring - ontwerp - mijnontwerp.ngio");

        this.parent = parent;
        this.controller = controller;

        this.availibilityPercentageLabel = new JLabel("Beschikbaarheidpercentage:");
        this.availibilityPercentageField = new JTextField();
        this.generateDesignButton = new JButton();
        this.closeButton = new JButton();


    }
}

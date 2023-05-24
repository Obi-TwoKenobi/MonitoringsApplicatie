package GUI.infrastructuredesign;

import controllers.InfrastructureDesignController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptimalInfrastructureDesignDialog extends JDialog implements ActionListener {
    private JLabel availibilityPercentageLabel;
    private JTextField availibilityPercentageField;
    private JButton generateDesignButton, closeButton;
    private InfrastructureDesignController controller;

    public OptimalInfrastructureDesignDialog(JDialog parent, InfrastructureDesignController controller) {
        super(parent, false);

        this.setSize(300, 100);
        this.setLocationRelativeTo(null);
        this.setLayout(new GridLayout(2,2));
        this.setTitle("Genereren optimale beschikbaarheid");

        this.controller = controller;

        this.availibilityPercentageLabel = new JLabel("Beschikbaarheidpercentage:");
        this.availibilityPercentageField = new JTextField();
        this.generateDesignButton = new JButton("Genereren");
        generateDesignButton.addActionListener(this);
        this.closeButton = new JButton("Close");
        closeButton.addActionListener(this);

        add(availibilityPercentageLabel);
        add(availibilityPercentageField);
        add(generateDesignButton);
        add(closeButton);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(generateDesignButton)){
            try {
                String availability = availibilityPercentageField.getText();
                availability = availability.replace(",", ".");
                double beschikbaarheidpercentage = Double.parseDouble(availability);
                this.controller.getOptimalDesign(beschikbaarheidpercentage);
            } catch (NumberFormatException nfe){
                JOptionPane.showMessageDialog(this, "Vul een geldige waarde in!", "Fout", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource().equals(closeButton)){
            this.dispose();
        }
    }
}

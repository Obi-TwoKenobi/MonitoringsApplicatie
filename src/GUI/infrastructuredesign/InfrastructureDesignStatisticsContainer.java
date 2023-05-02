package GUI.infrastructuredesign;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InfrastructureDesignStatisticsContainer extends JPanel{

    private JLabel availabilityPercentageLabel;
    private JLabel totalDesignCostLabel;

    private JButton saveButton;
    private JButton cancelButton;



    public InfrastructureDesignStatisticsContainer(){
        super();
        setPreferredSize(new Dimension(this.getWidth(), 30));

        this.availabilityPercentageLabel = new JLabel("Beschikbaarheid ontwerp: 97%");
        this.totalDesignCostLabel = new JLabel("Prijs ontwerp: € 60.000");

        this.saveButton = new JButton("Opslaan");
        this.cancelButton = new JButton("Cancel");

        

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(Box.createRigidArea(new Dimension(10, this.getHeight())));
        this.add(availabilityPercentageLabel);
        this.add(Box.createRigidArea(new Dimension(5, this.getHeight())));
        this.add(totalDesignCostLabel);
        this.add(Box.createRigidArea(new Dimension(780, this.getHeight())));
        this.add(saveButton);
        this.add(Box.createRigidArea(new Dimension(5, this.getHeight())));
        this.add(cancelButton);
    }
}

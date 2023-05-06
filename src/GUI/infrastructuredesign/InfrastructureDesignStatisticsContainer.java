package GUI.infrastructuredesign;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controllers.InfrastructureDesignController;

public class InfrastructureDesignStatisticsContainer extends JPanel{

    private JLabel availabilityPercentageLabel;
    private JLabel totalDesignCostLabel;

    private JButton saveButton;
    private JButton cancelButton;

    private InfrastructureDesignController controller;

    private JDialog parent;

    private final int GUI_HEIGHT = 30;

    public InfrastructureDesignStatisticsContainer(JDialog parent, InfrastructureDesignController controller){
        super();
        this.controller = controller;
        this.parent = parent;
        this.setPreferredSize(new Dimension(this.getWidth(), GUI_HEIGHT));

        this.availabilityPercentageLabel = new JLabel("Beschikbaarheid ontwerp: " + this.controller.getCurrentlyActiveDesign().calculateAvailabilityPercentage() * 100 + "%");
        this.totalDesignCostLabel = new JLabel("Prijs ontwerp: € " + this.controller.getCurrentlyActiveDesign().calculateTotalPrice());

        this.saveButton = new JButton("Opslaan");
        this.cancelButton = new JButton("Cancel");
        this.cancelButton.addActionListener((e) -> {
            int selectedOption = JOptionPane.showConfirmDialog(this, "Weet u zeker dat u het ontwerp wilt afsluiten?");
            if(selectedOption == JOptionPane.YES_OPTION){
                this.parent.setVisible(false);
            }
        });

        

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

    public void updateView(){
        this.availabilityPercentageLabel.setText("Beschikbaarheid ontwerp: " + this.controller.getCurrentlyActiveDesign().calculateAvailabilityPercentage() * 100 + "%");
        this.totalDesignCostLabel.setText("Prijs ontwerp: € " + this.controller.getCurrentlyActiveDesign().calculateTotalPrice());
    }
}

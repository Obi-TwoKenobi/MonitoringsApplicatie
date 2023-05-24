package GUI.infrastructuredesign;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import GUI.Styling;
import controllers.InfrastructureDesignController;

public class InfrastructureDesignStatisticsContainer extends JPanel{

    private JLabel availabilityPercentageLabel;
    private JLabel totalDesignCostLabel;

    private JButton saveButton;
    private JButton cancelButton;

    private final Font STATISTICS_FONT = new Font("arial", Font.PLAIN, 14);

    private InfrastructureDesignController controller;

    private JDialog parent;

    private final int GUI_HEIGHT = 30;

    public InfrastructureDesignStatisticsContainer(JDialog parent, InfrastructureDesignController controller){
        super();
        this.controller = controller;
        this.parent = parent;
        this.setPreferredSize(new Dimension(this.getWidth(), GUI_HEIGHT));

        this.setBackground(Styling.COLOR_LIGHT_BLUE);

        this.availabilityPercentageLabel = new JLabel("Beschikbaarheid ontwerp: " + this.controller.getCurrentlyActiveDesign().calculateAvailabilityPercentage() * 100 + "%");
        this.availabilityPercentageLabel.setForeground(Color.WHITE);
        this.availabilityPercentageLabel.setFont(STATISTICS_FONT);

        this.totalDesignCostLabel = new JLabel("Prijs ontwerp: € " + this.controller.getCurrentlyActiveDesign().calculateTotalPrice());
        this.totalDesignCostLabel.setForeground(Color.WHITE);
        this.totalDesignCostLabel.setFont(STATISTICS_FONT);

        this.saveButton = new JButton("Opslaan");
        this.saveButton.addActionListener((e) -> {
                if(this.controller.getCurrentlyActiveDesign().getSavedFilePath() != null){
                    try {
                        this.controller.saveInfrastructureDesign(this.controller.getCurrentlyActiveDesign().getSavedFilePath());
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }else{
                    JFileChooser fileChooser = new JFileChooser();
                    FileFilter filter = new FileNameExtensionFilter("NerdyGadgets Infrastructuur ontwerp(*.ngio)", "ngio");
                    fileChooser.setCurrentDirectory(new File("."));
                    fileChooser.addChoosableFileFilter(filter);
                    fileChooser.setFileFilter(filter);
                    int response = fileChooser.showSaveDialog(null);
    
                    if (response == JFileChooser.APPROVE_OPTION){
                        File selectedFile = fileChooser.getSelectedFile();
                        try {
                            this.controller.getCurrentlyActiveDesign().setSavedFilePath(String.valueOf(selectedFile));
                            this.controller.saveInfrastructureDesign(String.valueOf(selectedFile));
                        } catch (FileNotFoundException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
        });

        this.cancelButton = new JButton("Cancel");
        this.cancelButton.addActionListener((e) -> {
            String[] options = {"Ja", "Nee"};
            int selectedOption = JOptionPane.showOptionDialog(this, "Weet u zeker dat u dit ontwerp wilt afsluiten?", "Afsluiten", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
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

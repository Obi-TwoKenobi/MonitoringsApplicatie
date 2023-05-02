package GUI.infrastructuredesign;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class DesignComponent extends JButton{
    private String name;
    private double availabilityPercentage;
    private double pricePerYear;

    public DesignComponent(String name, double availabilityPercentage, double pricePerYear){
        this.name = name;
        this.availabilityPercentage = availabilityPercentage;
        this.pricePerYear = pricePerYear;

        this.setMaximumSize(new Dimension(150, 150));
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setText("<html>" + this.name + " <br> Beschikbaarheid:" + this.availabilityPercentage +  " <br>  Prijs:" + this.pricePerYear + "</html>");
    }
}

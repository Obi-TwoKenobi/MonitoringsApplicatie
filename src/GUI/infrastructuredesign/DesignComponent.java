package GUI.infrastructuredesign;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

import data.InfrastructureDesignComponent;

public class DesignComponent extends JButton{
    private String name;
    private double availabilityPercentage;
    private double pricePerYear;

    public DesignComponent(InfrastructureDesignComponent component){
        this.name = component.getName();
        this.availabilityPercentage = component.getAvailabilityPercentage();
        this.pricePerYear = component.getPricePerYear();

        this.setMaximumSize(new Dimension(150, 150));
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setOpaque(false);
        this.setFont(new Font("arial", Font.BOLD, 10));
        // this.setContentAreaFilled(false);
        this.setText("<html>" + this.name + " <br> Beschikbaarheid:" + this.availabilityPercentage +  " <br>  Prijs:" + this.pricePerYear + "</html>");
    }
}

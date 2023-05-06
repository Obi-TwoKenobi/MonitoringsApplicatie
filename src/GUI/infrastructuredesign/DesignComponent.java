package GUI.infrastructuredesign;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import data.DatabaseserverComponent;
import data.FirewallComponent;
import data.InfrastructureDesignComponent;
import data.WebserverComponenet;

public class DesignComponent extends JButton{
    private ImageIcon icon;
    private String name;
    private double availabilityPercentage;
    private double pricePerYear;

    private final int IMAGE_width = 130;
    private final int IMAGE_height = 80;

    private final int MAXIMUM_COMPONENT_WIDTH = 200;
    private final int MAXIMUM_COMPONENT_HEIGHT = 200;

    private final int MINIMUM_COMPONENT_WIDTH = 20;
    private final int MINIMUM_COMPONENT_HEIGHT = 20;

    private final int FONT_SIZE = 10;

    public DesignComponent(InfrastructureDesignComponent component){
        this.name = component.getName();
        this.availabilityPercentage = component.getAvailabilityPercentage();
        this.pricePerYear = component.getPricePerYear();

        this.setMaximumSize(new Dimension(MAXIMUM_COMPONENT_WIDTH, MAXIMUM_COMPONENT_HEIGHT));
        this.setMinimumSize(new Dimension(MINIMUM_COMPONENT_WIDTH, MINIMUM_COMPONENT_HEIGHT));
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setOpaque(false);

        this.icon = this.getComponentImage(component);

        this.setFont(new Font("arial", Font.BOLD, FONT_SIZE));
        this.setContentAreaFilled(false);
        this.setVerticalTextPosition(SwingConstants.BOTTOM);
        this.setHorizontalTextPosition(SwingConstants.CENTER);
        this.setIcon(this.icon);
        this.setText("<html>" + this.name + " <br> Beschikbaarheid:" + (this.availabilityPercentage * 100) +  "% <br>  Prijs: €" + this.pricePerYear + "</html>");
    }

    private ImageIcon getComponentImage(InfrastructureDesignComponent component){
        String filePath = "";
        if(component instanceof FirewallComponent) filePath = "src/resources/images/firewall.png";
        if(component instanceof WebserverComponenet) filePath = "src/resources/images/webserver.png";
        if(component instanceof DatabaseserverComponent) filePath = "src/resources/images/database.png";

        try{
            BufferedImage img = ImageIO.read(new File(filePath));
            Image scaledImage = img.getScaledInstance(IMAGE_width, IMAGE_height, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
        }catch(IOException ioe){
            ioe.printStackTrace();
            return new ImageIcon();
        }
    }
}

import javax.imageio.ImageIO;
import javax.swing.*;

import GUI.Styling;
import GUI.infrastructuredesign.InfrastructureDesignGUI;
import GUI.monitoring.MonitoringsGUI;
import data.DatabaseserverComponent;
import data.FirewallComponent;
import data.InfrastructureDesignComponent;
import data.WebserverComponent;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;

public class MainScreen extends JPanel implements ActionListener {
    //buttons
    private JButton monitoring, ontwerpButton;
    private JFrame hoofdscherm;
    private JLabel monitoringLabel, ontwerpLabel;
    private final int IMAGE_width = 250;
    private final int IMAGE_height = 200;
    private InfrastructureDesignGUI infrastructureDesignGUI;
    private ImageIcon monitoringIcon, ontwerpIcon;

    //Screen settings
    private final int originalTileSize = 16; // 16x16 tile
    private final int scale = 3;

    private final int tileSize = originalTileSize * scale; //48x48 tile
    private final int maxScreenCol = 16;
    private final int maxScreenRow = 12;
    private final int screenWidth = tileSize * maxScreenCol; // 768px
    private final int screenHeight = tileSize * maxScreenRow; // 576px


    public MainScreen (JFrame jFrame){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Styling.COLOR_DARK_BLUE);
        this.setDoubleBuffered(true);
        this.hoofdscherm = jFrame;

        this.monitoringLabel = new JLabel("Monitoring");
        this.ontwerpLabel = new JLabel("Ontwerpen");

        this.monitoringIcon = this.getComponentImage(0);
        this.ontwerpIcon = this.getComponentImage(1);

        add(monitoringLabel);
        add(ontwerpLabel);

        monitoringLabel.setBounds(150, 420, 150, 30);
        ontwerpLabel.setBounds(470, 420, 150, 30);

        monitoringLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        monitoringLabel.setForeground(Color.white);

        ontwerpLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        ontwerpLabel.setForeground(Color.white);

        monitoring = new JButton(monitoringIcon);
        monitoring.addActionListener(this);
        Dimension size = monitoring.getPreferredSize();
        monitoring.setBounds(80, 200, size.width, size.height);
        setLayout(null);
        add(monitoring);

        this.ontwerpButton = new JButton(ontwerpIcon);
        infrastructureDesignGUI = new InfrastructureDesignGUI(hoofdscherm);
        ontwerpButton.setBounds(400, 200, size.width, size.height);
        ontwerpButton.addActionListener(this);
        add(ontwerpButton);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        ontwerpButton.setBorderPainted(false);
        monitoring.setBorderPainted(false);

        ontwerpButton.setFocusPainted(false);
        monitoring.setFocusPainted(false);

        ontwerpButton.setOpaque(false);
        monitoring.setOpaque(false);
    }
    private ImageIcon getComponentImage(int keuze){
        String filePath = "";
        if(keuze == 0) filePath = "src/resources/images/MonitorWaveformFontAwesome.png";
        if(keuze == 1) filePath = "src/resources/images/DesktopAwesomeFont.png";

        try{
            BufferedImage img = ImageIO.read(new File(filePath));
            Image scaledImage = img.getScaledInstance(IMAGE_width, IMAGE_height, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
        }catch(IOException ioe){
            ioe.printStackTrace();
            return new ImageIcon();
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(monitoring)){
            hoofdscherm.setVisible(false);
            MonitoringsGUI monitoringsGUI = new MonitoringsGUI();
        }else if(e.getSource().equals(ontwerpButton)){
            this.infrastructureDesignGUI.setVisible(true);
        }
    }
}



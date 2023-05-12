import javax.imageio.ImageIO;
import javax.swing.*;

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
    private JButton monitoring;
    private JFrame hoofdscherm;
    private final int IMAGE_width = 130;
    private final int IMAGE_height = 80;
    private InfrastructureDesignGUI infrastructureDesignGUI;
    private ImageIcon icon0;
    private ImageIcon icon1;

    //buttons
    private JButton ontwerpButton;
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
        this.setBackground(new Color(35, 35, 47));
        this.setDoubleBuffered(true);
        this.hoofdscherm = jFrame;

        this.icon0 = this.getComponentImage(0);
        this.icon1 = this.getComponentImage(1);

        monitoring = new JButton(icon0);
        monitoring.addActionListener(this);
        Dimension size = monitoring.getPreferredSize();
        monitoring.setBounds(200, 240, size.width, size.height);
        setLayout(null);
        add(monitoring);

        this.ontwerpButton = new JButton(icon1);
        infrastructureDesignGUI = new InfrastructureDesignGUI(hoofdscherm);
        ontwerpButton.setBounds(400, 240, size.width, size.height);
        ontwerpButton.addActionListener(this);
        add(ontwerpButton);

        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

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



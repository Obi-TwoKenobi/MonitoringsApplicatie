import javax.swing.*;

import GUI.infrastructuredesign.InfrastructureDesignGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainScreen extends JPanel implements ActionListener {
    //buttons
    private JButton monitoring;
    private JFrame hoofdscherm;
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

        monitoring = new JButton("Monitoring");
        monitoring.addActionListener(this);
        Dimension size = monitoring.getPreferredSize();
        monitoring.setBounds(200, 240, size.width, size.height);
        setLayout(null);
        add(monitoring);

        JButton ontwerpButton = new JButton("ontwerp");
        InfrastructureDesignGUI idg = new InfrastructureDesignGUI(hoofdscherm);
        ontwerpButton.setBounds(400, 240, size.width, size.height);
        ontwerpButton.addActionListener((e) -> idg.setVisible(true));
        add(ontwerpButton);

        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(monitoring)){
            hoofdscherm.dispose();
            MonitoringsGUI monitoringsGUI = new MonitoringsGUI(hoofdscherm, true);
        }
    }
}


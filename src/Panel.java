import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Panel extends JPanel implements ActionListener {
    //buttons
    private JButton monitoring;
    private JFrame hoofdscherm;
    //Screen settings
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;

    final int tileSize = originalTileSize * scale; //48x48 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768px
    final int screenHeight = tileSize * maxScreenRow; // 576px

    public Panel (JFrame jFrame){
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

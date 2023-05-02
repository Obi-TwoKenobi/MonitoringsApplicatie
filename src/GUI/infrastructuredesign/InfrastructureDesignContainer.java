package GUI.infrastructuredesign;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

public class InfrastructureDesignContainer extends JPanel{

    private JLabel firewallLayerLabel;
    private JLabel webserverLayerLabel;
    private JLabel databaseLayerLabel;

    JPanel firewallLayer;
    JPanel webserverLayer;
    JPanel databaseLayer;

    public InfrastructureDesignContainer(){
        super();
        this.setLayout(new FlowLayout(FlowLayout.LEFT));

        this.firewallLayerLabel = new JLabel("Firewall");
        this.firewallLayerLabel.setAlignmentX(LEFT_ALIGNMENT);
        
        this.webserverLayerLabel = new JLabel("Webservers");
        this.webserverLayerLabel.setAlignmentX(LEFT_ALIGNMENT);
        
        this.databaseLayerLabel = new JLabel("Webservers");
        this.databaseLayerLabel.setAlignmentX(LEFT_ALIGNMENT);

        this.firewallLayer = new JPanel();
        this.firewallLayer.setPreferredSize(new Dimension(1280, 140));
        this.firewallLayer.setLayout(new BoxLayout(firewallLayer, BoxLayout.LINE_AXIS));
        this.firewallLayer.setAlignmentX(LEFT_ALIGNMENT);
        this.firewallLayer.setBackground(Color.RED);


        this.webserverLayer = new JPanel();
        this.webserverLayer.setPreferredSize(new Dimension(1280, 140));
        this.webserverLayer.setLayout(new BoxLayout(webserverLayer, BoxLayout.LINE_AXIS));
        this.webserverLayer.setAlignmentX(LEFT_ALIGNMENT);
        this.webserverLayer.setBackground(Color.GREEN);

        this.databaseLayer = new JPanel();
        this.databaseLayer.setPreferredSize(new Dimension(1280, 140));
        this.databaseLayer.setLayout(new BoxLayout(databaseLayer, BoxLayout.LINE_AXIS));
        this.databaseLayer.setAlignmentX(LEFT_ALIGNMENT);
        this.databaseLayer.setBackground(Color.BLUE);


        this.add(this.firewallLayerLabel);
        this.add(firewallLayer);
        this.add(webserverLayerLabel);
        this.add(this.webserverLayer);
        this.add(databaseLayerLabel);
        this.add(this.databaseLayer);


        this.firewallLayer.add(new DesignComponent("Pfsense", 97, 2000));

        //TODO aanvullen met InfrastructureDesign
        for(int i = 0; i < 2; i++){
            JButton server1 = new JButton();
            this.webserverLayer.add(new DesignComponent("HALW9001", 99, 4000));
        }
        
        for(int i = 0; i < 2; i++){
            JButton databaseserver = new JButton("Database " + (i + 1) + " \n Beschikbaarheid: 99,998% \n Prijs: €4,000");
            this.databaseLayer.add(new DesignComponent("HALW9001", 97, 2500));
        }
        
    }
}

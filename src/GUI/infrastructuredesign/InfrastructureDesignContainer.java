package GUI.infrastructuredesign;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import GUI.Styling;
import controllers.InfrastructureDesignController;
import data.InfrastructureDesignComponent;

public class InfrastructureDesignContainer extends JPanel{

    private JLabel firewallLayerLabel;
    private JLabel webserverLayerLabel;
    private JLabel databaseLayerLabel;

    private JPanel firewallLayer;
    private JPanel webserverLayer;
    private JPanel databaseLayer;

    private final int LAYER_WIDTH = 1280;
    private final int LAYER_HEIGHT = 140;

    private final Font LAYER_FONT = new Font("arial", Font.PLAIN, 16);

    private InfrastructureDesignController controller;

    public InfrastructureDesignContainer(InfrastructureDesignController controller){
        super();
        this.controller = controller;

        this.setLayout(new FlowLayout(FlowLayout.LEFT));

        this.firewallLayerLabel = new JLabel("Firewall");
        this.firewallLayerLabel.setAlignmentX(LEFT_ALIGNMENT);
        this.firewallLayerLabel.setForeground(Color.WHITE);
        this.firewallLayerLabel.setFont(LAYER_FONT);
        
        this.webserverLayerLabel = new JLabel("Webservers");
        this.webserverLayerLabel.setAlignmentX(LEFT_ALIGNMENT);
        this.webserverLayerLabel.setForeground(Color.WHITE);
        this.webserverLayerLabel.setFont(LAYER_FONT);
        
        this.databaseLayerLabel = new JLabel("Webservers");
        this.databaseLayerLabel.setAlignmentX(LEFT_ALIGNMENT);
        this.databaseLayerLabel.setForeground(Color.WHITE);
        this.databaseLayerLabel.setFont(LAYER_FONT);

        this.firewallLayer = new JPanel();
        this.firewallLayer.setPreferredSize(new Dimension(LAYER_WIDTH, LAYER_HEIGHT));
        this.firewallLayer.setLayout(new BoxLayout(firewallLayer, BoxLayout.LINE_AXIS));
        this.firewallLayer.setAlignmentX(LEFT_ALIGNMENT);
        this.firewallLayer.setBackground(Styling.COLOR_LIGHT_BLUE);

        this.webserverLayer = new JPanel();
        this.webserverLayer.setPreferredSize(new Dimension(LAYER_WIDTH, LAYER_HEIGHT));
        this.webserverLayer.setLayout(new BoxLayout(webserverLayer, BoxLayout.LINE_AXIS));
        this.webserverLayer.setAlignmentX(LEFT_ALIGNMENT);
        this.webserverLayer.setBackground(Styling.COLOR_LIGHT_BLUE);

        this.databaseLayer = new JPanel();
        this.databaseLayer.setPreferredSize(new Dimension(LAYER_WIDTH, LAYER_HEIGHT));
        this.databaseLayer.setLayout(new BoxLayout(databaseLayer, BoxLayout.LINE_AXIS));
        this.databaseLayer.setAlignmentX(LEFT_ALIGNMENT);
        this.databaseLayer.setBackground(Styling.COLOR_LIGHT_BLUE);

        this.add(this.firewallLayerLabel);
        this.add(this.firewallLayer);
        this.add(this.webserverLayerLabel);
        this.add(this.webserverLayer);
        this.add(this.databaseLayerLabel);
        this.add(this.databaseLayer);

        updateView();
    }

    public void updateView(){
        this.firewallLayer.removeAll();
        this.databaseLayer.removeAll();
        this.webserverLayer.removeAll();
        
        for(InfrastructureDesignComponent comp : this.controller.getCurrentlyActiveDesign().getFirewallLayer().getInfrastructureComponents()){
            DesignComponent dc = new DesignComponent(comp);
            this.firewallLayer.add(dc);
            dc.addActionListener((e) ->  {
                this.controller.removeComponentFromLayer(comp);
                this.repaint();
            });
        }

        for(InfrastructureDesignComponent comp : this.controller.getCurrentlyActiveDesign().getWebserverLayer().getInfrastructureComponents()){
            DesignComponent dc = new DesignComponent(comp);
            this.webserverLayer.add(dc);
            dc.addActionListener((e) ->  {
                this.controller.removeComponentFromLayer(comp);
                this.repaint();
            });
        }
        
        for(InfrastructureDesignComponent comp : this.controller.getCurrentlyActiveDesign().getDatabaseLayer().getInfrastructureComponents()){
            DesignComponent dc = new DesignComponent(comp);
            this.databaseLayer.add(dc);
            dc.addActionListener((e) ->  {
                this.controller.removeComponentFromLayer(comp);
                this.repaint();
            });
        }

        this.revalidate();
    }

    public JPanel getFirewallLayer(){
        return this.firewallLayer;
    }
    public JPanel getWebserverLayer(){
        return this.webserverLayer;
    }
    public JPanel getDatabaseLayer(){
        return this.databaseLayer;
    }
}

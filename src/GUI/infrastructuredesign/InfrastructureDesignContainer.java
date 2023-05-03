package GUI.infrastructuredesign;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import controllers.InfrastructureDesignController;
import data.FirewallComponent;
import data.InfrastructureDesignComponent;
import data.WebserverComponenet;

public class InfrastructureDesignContainer extends JPanel{

    private JLabel firewallLayerLabel;
    private JLabel webserverLayerLabel;
    private JLabel databaseLayerLabel;

    private JPanel firewallLayer;
    private JPanel webserverLayer;
    private JPanel databaseLayer;

    private InfrastructureDesignController controller;

    public InfrastructureDesignContainer(InfrastructureDesignController controller){
        super();
        this.controller = controller;

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

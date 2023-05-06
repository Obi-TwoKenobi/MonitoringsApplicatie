package GUI.infrastructuredesign;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JPanel;

import controllers.InfrastructureDesignController;
import data.ComponentLayer;
import data.DatabaseserverComponent;
import data.FirewallComponent;
import data.InfrastructureDesignComponent;
import data.WebserverComponenet;

public class InfrastructureItemSelectionContainer extends JPanel{
    
    private ArrayList<InfrastructureDesignComponent> COMPONENET_SELECTION;
    private InfrastructureDesignController controller;

    public InfrastructureItemSelectionContainer(InfrastructureDesignController controller){
        super();
        this.controller = controller;
        this.COMPONENET_SELECTION = new ArrayList<>(Arrays.asList(
            new FirewallComponent("pfSense", 0.99998, 4000),
            new WebserverComponenet("HAL9001W", 0.80, 2200),
            new WebserverComponenet("HAL9002W", 0.90, 3200),
            new WebserverComponenet("HAL9003W", 0.95, 5100),
            new DatabaseserverComponent("HAL9001DB", 0.90, 5100),
            new DatabaseserverComponent("HAL9002DB", 0.95, 7700),
            new DatabaseserverComponent("HAL9003DB", 0.98, 12200)
        ));
        setPreferredSize(new Dimension(150, this.getHeight()));
        this.setBackground(new Color(0xeeeeee));

        for(InfrastructureDesignComponent comp : this.COMPONENET_SELECTION){
            DesignComponent idc = new DesignComponent(comp);
            this.add(idc);
            idc.addActionListener((e) -> {
                this.controller.addComponentToLayer(comp);
                System.out.println(comp);   
            });
        }
    }
}

package GUI.infrastructuredesign;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JPanel;

import GUI.Styling;
import controllers.InfrastructureDesignController;
import data.DatabaseserverComponent;
import data.FirewallComponent;
import data.InfrastructureDesignComponent;
import data.WebserverComponent;

public class InfrastructureItemSelectionContainer extends JPanel{
    
    private ArrayList<InfrastructureDesignComponent> COMPONENT_SELECTION;
    private InfrastructureDesignController controller;

    public InfrastructureItemSelectionContainer(InfrastructureDesignController controller){
        super();

        this.controller = controller;
        this.COMPONENT_SELECTION = new ArrayList<>(Arrays.asList(
            new FirewallComponent("pfSense", 0.99998, 4000),
            new WebserverComponent("HAL9001W", 0.80000, 2200),
            new WebserverComponent("HAL9002W", 0.90000, 3200),
            new WebserverComponent("HAL9003W", 0.95000, 5100),
            new DatabaseserverComponent("HAL9001DB", 0.90000, 5100),
            new DatabaseserverComponent("HAL9002DB", 0.95000, 7700),
            new DatabaseserverComponent("HAL9003DB", 0.98000, 12200)
        ));
        setPreferredSize(new Dimension(150, (150 * 10)));
        this.setBackground(new Color(0xeeeeee));

        for(InfrastructureDesignComponent comp : this.COMPONENT_SELECTION){
            DesignComponent idc = new DesignComponent(comp);
            this.add(idc);
            idc.addActionListener((e) -> {
                this.controller.addComponentToLayer(comp);
            });
        }
    }
}

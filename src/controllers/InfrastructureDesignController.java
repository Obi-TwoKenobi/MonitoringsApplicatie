package controllers;

import javax.management.RuntimeErrorException;
import javax.swing.JComponent;

import GUI.infrastructuredesign.DesignComponent;
import GUI.infrastructuredesign.InfrastructureDesignGUI;
import data.ComponentLayer;
import data.DatabaseserverComponent;
import data.FirewallComponent;
import data.InfrastructureDesign;
import data.InfrastructureDesignComponent;
import data.WebserverComponent;

public class InfrastructureDesignController {
    private InfrastructureDesignGUI infrastructureDesignGUI;
    private InfrastructureDesign currentlyActiveDesign;

    public InfrastructureDesignController(InfrastructureDesignGUI infrastructureDesignGUI){
        this.infrastructureDesignGUI = infrastructureDesignGUI;
        this.currentlyActiveDesign = new InfrastructureDesign();
    }
    
    public InfrastructureDesignController(InfrastructureDesignGUI infrastructureDesignGUI, InfrastructureDesign currentlyActiveDesign){
        this.infrastructureDesignGUI = infrastructureDesignGUI;
        this.currentlyActiveDesign = currentlyActiveDesign;
    }

    public boolean saveInfrastructureDesign(String filePath) throws Exception{
        throw new Exception("nog niet geimplementeert");
    }
    
    public boolean loadInfrastructureDesign(String filePath) throws Exception{
        throw new Exception("nog niet geimplementeert");
    }
    
    
    public boolean getOptimalDesign(double targetPercentage) throws Exception{
        throw new Exception("nog niet geimplementeert");
    }

    public boolean addComponentToLayer(InfrastructureDesignComponent component){
        if(component instanceof WebserverComponent){
            this.getCurrentlyActiveDesign().getWebserverLayer().getInfrastructureComponents().add((WebserverComponent)component);
        }else if (component instanceof DatabaseserverComponent){
            this.getCurrentlyActiveDesign().getDatabaseLayer().getInfrastructureComponents().add((DatabaseserverComponent)component);
        }else if(component instanceof FirewallComponent){
            this.getCurrentlyActiveDesign().getFirewallLayer().getInfrastructureComponents().add((FirewallComponent)component);
            
        }
        this.infrastructureDesignGUI.getDesignContainer().updateView();
        this.infrastructureDesignGUI.getStatisticsContainer().updateView();
        return true;
    }
    
    public boolean removeComponentFromLayer(InfrastructureDesignComponent idc){
        if(idc instanceof FirewallComponent){
            this.getCurrentlyActiveDesign().getFirewallLayer().getInfrastructureComponents().remove((FirewallComponent)idc);
        }else if(idc instanceof WebserverComponent){
            this.getCurrentlyActiveDesign().getWebserverLayer().getInfrastructureComponents().remove((WebserverComponent)idc);
        }else if(idc instanceof DatabaseserverComponent){
            this.getCurrentlyActiveDesign().getDatabaseLayer().getInfrastructureComponents().remove((DatabaseserverComponent)idc);
        }

        this.infrastructureDesignGUI.getDesignContainer().updateView();
        this.infrastructureDesignGUI.getStatisticsContainer().updateView();

        return true;
    }

    public InfrastructureDesign getCurrentlyActiveDesign(){
        return this.currentlyActiveDesign;
    }
}

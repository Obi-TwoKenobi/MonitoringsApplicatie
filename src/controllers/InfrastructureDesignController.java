package controllers;

import javax.management.RuntimeErrorException;
import javax.swing.JComponent;

import GUI.infrastructuredesign.DesignComponent;
import GUI.infrastructuredesign.InfrastructureDesignContainer;
import GUI.infrastructuredesign.InfrastructureDesignGUI;
import data.ComponentLayer;
import data.DatabaseserverComponent;
import data.FirewallComponent;
import data.InfrastructureDesign;
import data.InfrastructureDesignComponent;
import data.WebserverComponent;

import java.io.*;

public class InfrastructureDesignController {
    private InfrastructureDesignGUI infrastructureDesignGUI;
    private InfrastructureDesign currentlyActiveDesign;
    private InfrastructureDesignContainer infrastructureDesignContainer;

    public InfrastructureDesignController(InfrastructureDesignGUI infrastructureDesignGUI){
        this.infrastructureDesignGUI = infrastructureDesignGUI;
        this.currentlyActiveDesign = new InfrastructureDesign();
    }
    
    public InfrastructureDesignController(InfrastructureDesignGUI infrastructureDesignGUI, InfrastructureDesign currentlyActiveDesign){
        this.infrastructureDesignGUI = infrastructureDesignGUI;
        this.currentlyActiveDesign = currentlyActiveDesign;
    }

    public void saveInfrastructureDesign(String filePath) throws FileNotFoundException {
        try {
            FileOutputStream fileOut = new FileOutputStream("UserInfo.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this.currentlyActiveDesign);
            out.close();
            fileOut.close();
            System.out.println("object info opgeslagen");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    
    public void loadInfrastructureDesign(String filePath) throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream("C:\\Users\\gwijn\\OneDrive\\Documenten\\GitHub\\MonitoringsApplicatie\\UserInfo.ser");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        this.currentlyActiveDesign = (data.InfrastructureDesign) in.readObject();
        in.close();
        fileIn.close();
        System.out.println("openene werkt");
        this.infrastructureDesignGUI.getDesignContainer().updateView();
        this.infrastructureDesignGUI.getStatisticsContainer().updateView();
        this.infrastructureDesignGUI.revalidate();
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

    public void setDesign(){
        this.currentlyActiveDesign = null;
    }
}

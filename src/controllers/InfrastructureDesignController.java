package controllers;

import javax.swing.*;
import GUI.infrastructuredesign.InfrastructureDesignGUI;
import data.*;
import exceptions.Infrastructuredesign.NoSuitableInfrastructureDesignException;
import java.io.*;

public class InfrastructureDesignController {
    private InfrastructureDesignGUI infrastructureDesignGUI;
    private InfrastructureDesign currentlyActiveDesign;

    public InfrastructureDesignController(InfrastructureDesignGUI infrastructureDesignGUI){
        this.infrastructureDesignGUI = infrastructureDesignGUI;
        this.currentlyActiveDesign = new InfrastructureDesign();
    }

    public void saveInfrastructureDesign(String filePath) throws FileNotFoundException {
        try {
            FileOutputStream fileOut = new FileOutputStream(filePath);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this.currentlyActiveDesign);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void loadInfrastructureDesign(String filePath) throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream(filePath);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        this.currentlyActiveDesign = (data.InfrastructureDesign) in.readObject();
        in.close();
        fileIn.close();
        this.infrastructureDesignGUI.getDesignContainer().updateView();
        this.infrastructureDesignGUI.getStatisticsContainer().updateView();
        this.infrastructureDesignGUI.revalidate();
    }

    public void getOptimalDesign(double targetPercentage){
        if (targetPercentage > 1){
            targetPercentage = targetPercentage/100;
        }
        OptimalInfrastructureDesignGenerator dg = new OptimalInfrastructureDesignGenerator();
        try{
            this.setCurrentlyActiveDesign(dg.generateOptimizedDesign(targetPercentage));
        }catch(NoSuitableInfrastructureDesignException nsie){
            JOptionPane.showMessageDialog(infrastructureDesignGUI, "Met dit percentage kunnen op met moment geen ontwerpen worden gemaakt", "Fout code: 69", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean addComponentToLayer(InfrastructureDesignComponent component){
        if(component instanceof WebserverComponent){
            if(this.getCurrentlyActiveDesign().getWebserverLayer().getInfrastructureComponents().size() >= this.getCurrentlyActiveDesign().getWebserverLayer().MAX_WEB_SERVERS){
                String[] options = {"Ok"};
                JOptionPane.showMessageDialog(infrastructureDesignGUI, "U heeft het maximum aantal van 6 webservers bereikt.", "Maximum Webservers", JOptionPane.ERROR_MESSAGE);return false;}
            this.getCurrentlyActiveDesign().getWebserverLayer().getInfrastructureComponents().add((WebserverComponent)component);
        }else if (component instanceof DatabaseserverComponent){
            if(this.getCurrentlyActiveDesign().getDatabaseLayer().getInfrastructureComponents().size() >= this.getCurrentlyActiveDesign().getDatabaseLayer().MAX_DATABASE_SERVERS){
                String[] options = {"Ok"};
                JOptionPane.showMessageDialog(infrastructureDesignGUI, "U heeft het maximum aantal van 6 databaseservers bereikt.", "Maximum databaseservers", JOptionPane.ERROR_MESSAGE);return false;}
            this.getCurrentlyActiveDesign().getDatabaseLayer().getInfrastructureComponents().add((DatabaseserverComponent)component);
        }else if(component instanceof FirewallComponent){
            if(this.getCurrentlyActiveDesign().getFirewallLayer().getInfrastructureComponents().size() >= this.getCurrentlyActiveDesign().getFirewallLayer().MAX_FIREWALLS){
                String[] options = {"Ok"};
                JOptionPane.showMessageDialog(infrastructureDesignGUI, "U heeft het maximum aantal van 1 firewall bereikt.", "Maximum firewall", JOptionPane.ERROR_MESSAGE);return false;}
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

    public void newDesign(){
        this.setCurrentlyActiveDesign(new InfrastructureDesign());
        this.infrastructureDesignGUI.getDesignContainer().updateView();
        this.infrastructureDesignGUI.getStatisticsContainer().updateView();
        this.infrastructureDesignGUI.revalidate();
    }

    public InfrastructureDesign getCurrentlyActiveDesign(){
        return this.currentlyActiveDesign;
    }

    public void setCurrentlyActiveDesign(InfrastructureDesign design){
        this.currentlyActiveDesign = design;
        this.infrastructureDesignGUI.getDesignContainer().updateView();
        this.infrastructureDesignGUI.getStatisticsContainer().updateView();
        this.infrastructureDesignGUI.repaint();
    }
}

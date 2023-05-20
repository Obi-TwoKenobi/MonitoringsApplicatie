package controllers;

import javax.management.RuntimeErrorException;
import javax.swing.*;

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
import java.net.InetAddress;
import java.net.Socket;

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
    
    
    public boolean getOptimalDesign(double targetPercentage) throws Exception{
        throw new Exception("nog niet geimplementeert");
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
    }
    public void placeholderMethodBacktracking(double beschikbaarheidspercentage){
        System.out.println(beschikbaarheidspercentage);
    }
    public void placeholderMethodIsValid(){

    }
    private double currentLowestPrice = 999999999999999.0;
    private final int MIN_FIREWALLS = 1;
    private final int MIN_WEBSERVERS = 1;
    private final int MIN_DATABASESERVERS = 1;
    public boolean isValidDesign(InfrastructureDesign design, double availabilityPercentage) {
        //TODO finetunen van isValidDesign, werkt volgens mij(Joey) niet helemaal als verwacht.
        boolean desginHasCorrectAvailabilityPercentage = design.calculateAvailabilityPercentage() >= availabilityPercentage;

        boolean designIsCheaperThenCheapestGeneratedDesign = design.calculateTotalPrice() <= this.currentLowestPrice;

        boolean desginHasMoreThenMinFirewalls = design.getFirewallLayer().getInfrastructureComponents().size() >= MIN_FIREWALLS;
        boolean designHasMoreThenMinWebservers = design.getWebserverLayer().getInfrastructureComponents().size() >= MIN_WEBSERVERS;
        boolean designHasMoreThenMinDBServers = design.getDatabaseLayer().getInfrastructureComponents().size() >= MIN_DATABASESERVERS;

        boolean designHasMoreThenMinComponents = desginHasMoreThenMinFirewalls && designHasMoreThenMinWebservers && designHasMoreThenMinDBServers;

        boolean designHasRightAvailabilityAndPrice = desginHasCorrectAvailabilityPercentage && designIsCheaperThenCheapestGeneratedDesign;

        boolean isAcceptableDesign = designHasMoreThenMinComponents && designHasRightAvailabilityAndPrice;
        return isAcceptableDesign;
		/*if(isAcceptableDesign) {
			return true;
		}else {
			return false;
		}*/
    }
}

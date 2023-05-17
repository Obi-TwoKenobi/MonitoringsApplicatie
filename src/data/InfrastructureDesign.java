package data;

import java.io.Serializable;

public class InfrastructureDesign implements Serializable {
    private FirewallLayer firewallLayer;
    private WebserverLayer webserverLayer;
    private DatabaseLayer databaseLayer;

    private String savedFilePath;


    public InfrastructureDesign(){
        this.firewallLayer = new FirewallLayer();
        this.webserverLayer = new WebserverLayer();
        this.databaseLayer = new DatabaseLayer();
    }

    public InfrastructureDesign(FirewallLayer firewallLayer, WebserverLayer webserverLayer, DatabaseLayer databaseLayer){
        this.firewallLayer = firewallLayer;
        this.webserverLayer = webserverLayer;
        this.databaseLayer = databaseLayer;
    }

    public double calculateAvailabilityPercentage(){
        // System.out.println(this.webserverLayer.calculateAvailabilityPercentage());
        return this.firewallLayer.calculateAvailabilityPercentage() * this.webserverLayer.calculateAvailabilityPercentage() * this.databaseLayer.calculateAvailabilityPercentage();
    }
    
    public double calculateTotalPrice(){
        double totalPrice = 0;
		totalPrice += this.getFirewallLayer().getInfrastructureComponents().stream().mapToDouble(component -> component.getPricePerYear()).sum();
		totalPrice += this.getWebserverLayer().getInfrastructureComponents().stream().mapToDouble(component -> component.getPricePerYear()).sum();
		totalPrice += this.getDatabaseLayer().getInfrastructureComponents().stream().mapToDouble(component -> component.getPricePerYear()).sum();
		return totalPrice;
    }

    public FirewallLayer getFirewallLayer(){
        return this.firewallLayer;
    }
    
    public WebserverLayer getWebserverLayer(){
        return this.webserverLayer;
    }
    
    public DatabaseLayer getDatabaseLayer(){
        return this.databaseLayer;
    }

    public void setSavedFilePath(String savedFilePath) {
        this.savedFilePath = savedFilePath;
    }

    public String getSavedFilePath() {
        return savedFilePath;
    }

    @Override
    public String toString() {
        return "InfrastructureDesign{" +
                "firewallLayer=" + firewallLayer +
                ", webserverLayer=" + webserverLayer +
                ", databaseLayer=" + databaseLayer +
                '}';
    }
}

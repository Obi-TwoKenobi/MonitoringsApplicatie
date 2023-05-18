package data;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class InfrastructureDesign implements Serializable, Cloneable {
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
        double percentage = this.firewallLayer.calculateAvailabilityPercentage() * this.webserverLayer.calculateAvailabilityPercentage() * this.databaseLayer.calculateAvailabilityPercentage();
        return percentage;
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

    public void setFirewallLayer(FirewallLayer firewallLayer) {
        this.firewallLayer = firewallLayer;
    }

    public void setWebserverLayer(WebserverLayer webserverLayer) {
        this.webserverLayer = webserverLayer;
    }
    
    public void setDatabaseLayer(DatabaseLayer databaseLayer) {
        this.databaseLayer = databaseLayer;
    }

    @Override
    public String toString() {
        return "InfrastructureDesign{" +
                "firewallLayer=" + firewallLayer +
                ", webserverLayer=" + webserverLayer +
                ", databaseLayer=" + databaseLayer +
                '}';
    }

    public InfrastructureDesign deepCopy() throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bos);
        out.writeObject(this);
    
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream in = new ObjectInputStream(bis);
        InfrastructureDesign copiedInfrastructureDesign = (InfrastructureDesign) in.readObject();
        return copiedInfrastructureDesign;
    }

    @Override
	protected Object clone() throws CloneNotSupportedException {
        /*
         * Zou gebruikt moeten worden voor het clonene van een infrastructuur ontwerp maar deze werkt nog niet, daarom de boevenstaande deepCopy methode die met behulp van Serialization een deep copy maakt
         */
        InfrastructureDesign clonedDesign = (InfrastructureDesign) new InfrastructureDesign();
        clonedDesign.setFirewallLayer((FirewallLayer)clonedDesign.getFirewallLayer().clone());
        clonedDesign.setWebserverLayer((WebserverLayer)clonedDesign.getWebserverLayer().clone());
        clonedDesign.setDatabaseLayer((DatabaseLayer)clonedDesign.getDatabaseLayer().clone());
        return clonedDesign;
    }
}

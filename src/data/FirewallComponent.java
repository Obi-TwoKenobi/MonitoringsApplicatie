package data;

import java.io.Serializable;

public class FirewallComponent extends InfrastructureDesignComponent implements Serializable{

    public FirewallComponent() {
        // lege constructor
    }

    public FirewallComponent(String name, double availabilityPercentage, double pricePerYear) {
        super(name, availabilityPercentage, pricePerYear);
    }
}

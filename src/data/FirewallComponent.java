package data;

import java.io.Serializable;

public class FirewallComponent extends InfrastructureDesignComponent implements Serializable, Cloneable {

    public FirewallComponent() {
        // lege constructor
    }

    public FirewallComponent(String name, double availabilityPercentage, double pricePerYear) {
        super(name, availabilityPercentage, pricePerYear);
    }

    @Override
	protected Object clone() throws CloneNotSupportedException {
        FirewallComponent clone = new FirewallComponent(super.getName(),super.getAvailabilityPercentage(), super.getPricePerYear());
        return clone;
    }
    
}

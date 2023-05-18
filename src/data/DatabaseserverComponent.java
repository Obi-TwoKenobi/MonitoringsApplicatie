package data;

import java.io.Serializable;

public class DatabaseserverComponent extends InfrastructureDesignComponent implements Serializable {

    public DatabaseserverComponent() {
        // lege constructor
    }

    public DatabaseserverComponent(String name, double availabilityPercentage, double pricePerYear) {
        super(name, availabilityPercentage, pricePerYear);
    }

    @Override
	protected Object clone() throws CloneNotSupportedException {
        DatabaseserverComponent clone = new DatabaseserverComponent(super.getName(), super.getAvailabilityPercentage(), super.getPricePerYear());
        return clone;
    }
    
}

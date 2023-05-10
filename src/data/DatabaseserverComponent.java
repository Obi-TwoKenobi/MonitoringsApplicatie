package data;

import java.io.Serializable;

public class DatabaseserverComponent extends InfrastructureDesignComponent implements Serializable {

    public DatabaseserverComponent() {
        // lege constructor
    }

    public DatabaseserverComponent(String name, double availabilityPercentage, double pricePerYear) {
        super(name, availabilityPercentage, pricePerYear);
    }
    
}

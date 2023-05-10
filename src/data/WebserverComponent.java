package data;

import java.io.Serializable;

public class WebserverComponent extends InfrastructureDesignComponent implements Serializable {

    public WebserverComponent() {
        // lege constructor
    }

    public WebserverComponent(String name, double availabilityPercentage, double pricePerYear) {
        super(name, availabilityPercentage, pricePerYear);
    }
    
}

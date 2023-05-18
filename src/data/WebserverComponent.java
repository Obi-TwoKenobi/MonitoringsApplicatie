package data;

import java.io.Serializable;

public class WebserverComponent extends InfrastructureDesignComponent implements Serializable, Cloneable {

    public WebserverComponent() {
        // lege constructor
    }

    public WebserverComponent(String name, double availabilityPercentage, double pricePerYear) {
        super(name, availabilityPercentage, pricePerYear);
    }

    @Override
	protected Object clone() throws CloneNotSupportedException {
        WebserverComponent clone = new WebserverComponent(super.getName(), super.getAvailabilityPercentage(), super.getPricePerYear());
        return clone;
    }
    
}

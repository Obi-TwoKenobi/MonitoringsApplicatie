package data;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class ComponentLayer<T extends InfrastructureDesignComponent> implements Serializable {
    protected ArrayList<T> infrastructureComponentsList = new ArrayList<T>();

    protected double calculateAvailabilityPercentage(){
        double layerPercentage = 1;
		if(this.getInfrastructureComponents().isEmpty()) return layerPercentage;
		if(this.getInfrastructureComponents().size() > 1) {
			for(T comp : this.getInfrastructureComponents()) {
				layerPercentage *= (1 - comp.getAvailabilityPercentage());
			}
			layerPercentage = (1 - layerPercentage);
			return layerPercentage;
		}else {
			layerPercentage *= this.getInfrastructureComponents().get(0).getAvailabilityPercentage();
			return layerPercentage;
		}
    }

	@Override
	public String toString() {
		return "ComponentLayer{" + infrastructureComponentsList;
	}

	public ArrayList<T> getInfrastructureComponents(){
        return this.infrastructureComponentsList;
    }
}

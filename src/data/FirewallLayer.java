package data;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public class FirewallLayer extends ComponentLayer<FirewallComponent> implements Serializable, Cloneable {
    public final int MAX_FIREWALLS = 1;

    public FirewallLayer(){
		
	}
	
	public FirewallLayer(ArrayList<FirewallComponent> infrastructureArrayList){
		this.infrastructureComponenetsList = infrastructureArrayList;
	}

    @Override
	protected Object clone() throws CloneNotSupportedException {
        FirewallLayer clone = new FirewallLayer((ArrayList<FirewallComponent>)this.infrastructureComponenetsList.clone());
        return clone;
    }
}

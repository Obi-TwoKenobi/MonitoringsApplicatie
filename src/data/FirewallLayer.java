package data;

import java.io.Serializable;
import java.util.ArrayList;

public class FirewallLayer extends ComponentLayer<FirewallComponent> implements Serializable, Cloneable {
    public final int MAX_FIREWALLS = 1;

    public FirewallLayer(){
		
	}
	
	public FirewallLayer(ArrayList<FirewallComponent> infrastructureArrayList){
		this.infrastructureComponentsList = infrastructureArrayList;
	}

    @Override
	protected Object clone() throws CloneNotSupportedException {
        FirewallLayer clone = new FirewallLayer((ArrayList<FirewallComponent>)this.infrastructureComponentsList.clone());
        return clone;
    }
}

package data;

import java.io.Serializable;
import java.util.ArrayList;

public class FirewallLayer extends ComponentLayer<FirewallComponent> implements Serializable{
    public final int MAX_FIREWALLS = 1;

    public FirewallLayer(){
		
	}
	
	public FirewallLayer(ArrayList<FirewallComponent> infrastructureArrayList){
		this.infrastructureComponenetsList = infrastructureArrayList;
	}

}

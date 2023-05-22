package data;

import java.io.Serializable;
import java.util.ArrayList;

public class WebserverLayer extends ComponentLayer<WebserverComponent> implements Serializable, Cloneable {
    public final int MAX_WEB_SERVERS = 6;


    public WebserverLayer(){
		
	}
	
	public WebserverLayer(ArrayList<WebserverComponent> infrastructureArrayList){
		this.infrastructureComponentsList = infrastructureArrayList;
	}

    @Override
	protected Object clone() throws CloneNotSupportedException {
        WebserverLayer clone = new WebserverLayer((ArrayList<WebserverComponent>)this.infrastructureComponentsList.clone());
        return clone;
    }
}

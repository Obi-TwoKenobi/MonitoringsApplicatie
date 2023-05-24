package data;

import java.io.Serializable;
import java.util.ArrayList;

public class WebserverLayer extends ComponentLayer<WebserverComponent> implements Serializable{
    public final int MAX_WEB_SERVERS = 6;
    public WebserverLayer(){
		
	}
	public WebserverLayer(ArrayList<WebserverComponent> infrastructureArrayList){
		this.infrastructureComponenetsList = infrastructureArrayList;
	}
}

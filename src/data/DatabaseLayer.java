package data;

import java.io.Serializable;
import java.util.ArrayList;

public class DatabaseLayer extends ComponentLayer<DatabaseserverComponent> implements Serializable{
    public final int MAX_DATABASE_SERVERS = 6;


    public DatabaseLayer(){
		
	}
	
	public DatabaseLayer(ArrayList<DatabaseserverComponent> infrastructureArrayList){
		this.infrastructureComponenetsList = infrastructureArrayList;
	}
}

package data;

import java.io.Serializable;
import java.util.ArrayList;

public class DatabaseLayer extends ComponentLayer<DatabaseserverComponent> implements Serializable, Cloneable{
    public final int MAX_DATABASE_SERVERS = 6;


    public DatabaseLayer(){
		
	}
	
	public DatabaseLayer(ArrayList<DatabaseserverComponent> infrastructureArrayList){
		this.infrastructureComponentsList = infrastructureArrayList;
	}

    @Override
	protected Object clone() throws CloneNotSupportedException {
        DatabaseLayer clone = new DatabaseLayer((ArrayList<DatabaseserverComponent>)this.infrastructureComponentsList.clone());
        return clone;
    }

}

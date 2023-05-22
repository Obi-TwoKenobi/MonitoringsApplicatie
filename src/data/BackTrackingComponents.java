package data;

import java.io.Serializable;
import java.util.ArrayList;

public class BackTrackingComponents extends ComponentLayer<InfrastructureDesignComponent> implements Serializable, Cloneable{
    public BackTrackingComponents(){

    }
    public BackTrackingComponents(ArrayList<InfrastructureDesignComponent> infrastructureArrayList){
        this.infrastructureComponentsList = infrastructureArrayList;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        DatabaseLayer clone = new DatabaseLayer((ArrayList<DatabaseserverComponent>)this.infrastructureComponentsList.clone());
        return clone;
    }
}

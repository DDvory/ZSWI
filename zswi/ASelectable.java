package zswi;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import javafx.scene.Node;

public abstract class ASelectable {
    public static int incrementalId = 0;
    

    private int ID;

    public ASelectable() {
        this(getIncrementalIdAdd());
    }

    public ASelectable(int ID) {
        if(ID >= incrementalId)incrementalId = ID+1;
        this.ID = ID;
    }
    
    public static void clearIncrement(){
        incrementalId = 0;
    }
    

    private static int getIncrementalIdAdd() {
        return incrementalId++;
    }
    

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import javafx.scene.Node;

public abstract class ASelectable {

    private int ID;

    public ASelectable(int ID) {
        this.ID = ID;
    }
    

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    public abstract Node getView();
    public void setData(String arg0, String arg1) {
        try {
            Field field = this.getClass().getDeclaredField(arg0);
            field.setAccessible(true);

            if (!field.getType().equals(String.class)) {
                Class<?> dataType = field.getType();
                Method m = dataType.getDeclaredMethod("valueOf", String.class);
                m.setAccessible(true);

                Object o = m.invoke(this, arg1);
                field.set(this, dataType.cast(o));
            } else {
                field.set(this, arg1);
            }
        } catch (Exception e) { //(NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public String getData(String arg0) {
        try {
            Field field = this.getClass().getDeclaredField(arg0);
            field.setAccessible(true);

            if (!field.getType().equals(String.class)) {
                return String.valueOf(field.get(this));
            } else {
                return (String) field.get(this);
            }
        } catch (Exception e) {//(IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }
        return "";
    }
    private String CreateXMl(ASelectable as){
        return null;
    }
}

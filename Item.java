
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import javafx.scene.Node;
import javafx.scene.control.Label;
/**
 *
 * @author DDvory
 */
public class Item extends ASelectable {
    private String adress;
    private DataType  type;
    private int dataLen;
    private String format;
    private boolean correct;
    private String correctMessage;
    /////////////////////////////
    BigInteger INT;
    /////////////////////////////
    String STRING;
    LocalDate DATE;
    LocalTime TIME;
    float FLOAT;
    double DOUBLE;
    EnumItem ENUM;

    public Item(int id, String adress, String type, int dataLen, String format) {
        super(id);
        this.adress = adress;
        this.type = DataType.fromString(type);
        this.dataLen = dataLen;
        this.format = format;
    }

    public Item(String adress, String type, int dataLen, String format) {
        super();
        this.adress = adress;
        this.type = DataType.fromString(type);
        this.dataLen = dataLen;
        this.format = format;
    }
    
    private void correction(boolean bool, String message){
        correct = bool;
        correctMessage = message;
    }
    public void setData(String data){
        correction(true,"");
         try {
                Method m = Item.class.getDeclaredMethod("set"+type.toString(),String.class);
                m.setAccessible(true);
                m.invoke(this, data);
           
        } catch (Exception e) { //(NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
        
    }
    private void setSTRING(String string){
        if(string.getBytes().length>dataLen)correction(false,"Text je příliš dlouhý, zkraťte ho, nebo nastavte buňku na větší dýlku");
        STRING = string;
    }
    private void setDOUBLE(String string){
        try {
            DOUBLE = Double.valueOf(string);
        } catch (Exception e) {
            correction(false,"Špatný formát čísla");
        }
    }
    private void setFLOAT(String string){
        try {
            FLOAT=Float.valueOf(string);
        } catch (Exception e) {
            correction(false,"Špatný formát čísla");
        }
    }
    private void setINT(String string){
        try {
            BigInteger integ = new BigInteger(string);
            byte[] byt = integ.toByteArray();
            if(!(byt.length<=dataLen||byt.length==dataLen+1&&byt[0]==0)){
                throw new IllegalArgumentException();
            }
            INT = integ;
        } catch (Exception e) {
            correction(false,"Špatný formát čísla, nebo je číslo příliš veliké");
        }
        
    }
    private void setDATE(String string){
        try {
            String[] split = string.split(";");
            int a = Integer.decode(split[0]);
            int b = Integer.decode(split[1]);
            int c = Integer.decode(split[2]);
            DATE = LocalDate.of(a, b, c);
        } catch (Exception e) {
            correction(false,"Špatná data pro datum.");
        }
    }
    private void setTIME(String string){
        try {
            String[] split = string.split(";");
            int a = Integer.decode(split[0]);
            int b = Integer.decode(split[1]);
            int c = Integer.decode(split[2]);
            TIME = LocalTime.of(a, b, c);
        } catch (Exception e) {
            correction(false,"Špatná data pro čas.");
        }
    }
    private void setENUM(String string){
        try {
            String[] split = string.split(";");
            int a = Integer.decode(split[0]);
            int b = Integer.decode(split[1]);
            int c = Integer.decode(split[2]);
            TIME = LocalTime.of(a, b, c);
        } catch (Exception e) {
            correction(false,"Špatná data pro čas.");
        }
    }
    public byte[] getData(){
        return null;
    }
    
    private enum DataType{
        STRING, INT,DOUBLE,FLOAT, DATE, TIME, ENUM;
        public static DataType fromString(String str){
            DataType[] values = DataType.values();
            for (DataType value : values) {
                if(value.toString().equals(str))return value;
            }
            return null;
        }
    }
    private class EnumItem{
        private final int id;
        private int subId;

        public EnumItem(int id, int subId) {
            this.id = id;
            this.subId = subId;
        }
        
    }
}

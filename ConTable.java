/**
 *
 * @author DDvory
 */
public class ConTable {
    
    private ConTable(Table table) {
    }
    
    public static ConTable createTable(Table table){
        ConTable tab = new ConTable(table);
        return tab;
    }
    
}

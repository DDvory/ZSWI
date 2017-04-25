


/**
 *
 * @author DDvory
 */
public class Row extends ASelectable{
    //1 String identifikator
        
       private Item[] items;
       private Row row;

    public Row(int ID, Item[] items, int count) {
        super(ID);
        init(items,count);
    }
    

    public Row(Item[] items, int count) {
        super();
        init(items,count);
    }
    private void init(Item[] items,int count){
        if(items==null){
            this.items = new Item[count];
        }else{
            this.items = items;
        }
        row = this;
    }
    public Item get(int index){
        try {
            return items[index];
        } catch (Exception e) {
            return null;
        }
    }
    public void set(int index, Item item){
        try {
             items[index] = item;
        } catch (Exception e) {
            
        }
    } 
}

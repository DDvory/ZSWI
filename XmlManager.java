
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

/**
 *
 * @author DDvory
 */
public class XmlManager {
    
    public static Project ReadXML(String fileName){
        Project prj = null;
        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(fileName));
            prj = getProject((Element) doc.getElementsByTagName(Constants.Project).item(0));
        } catch (Exception ex) {
            
            Main.getAlert(Alert.AlertType.ERROR, "ERROR", "Nepodařilo se načíst xml",ex.getLocalizedMessage(), null).show();
            ex.printStackTrace();
        }
        return prj;
    }
    private static Project getProject(Element node)throws WrongArgumentException{
        
        List<String> regLang = getRegistredLanguages(node.getElementsByTagName(Constants.RegsitredLanguage));
        String language = getATR(node, Constants.language);
        String size = getATR(node, Constants.fontSize);
        Window w  = getWindow(node);
        return new Project(w.getID(),w.getName(),w.getLoad(),w.getWindow(),w.getPanel(),size,language,regLang);
    }
    private static  List<String> getRegistredLanguages(NodeList nodeList){
        List<String> list  = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            list.add(((Element)nodeList.item(i)).getAttribute(Constants.value));
        }
        return list;
    }
    private static Window getWindow(Element node)throws WrongArgumentException{
        List<Window> list = new ArrayList<>();
        NodeList elementsByTagName = node.getElementsByTagName(Constants.Window);
        for (int i = 0; i < elementsByTagName.getLength(); i++) {
            list.add(getWindow((Element)(elementsByTagName.item(i))));
        }
        NodeList panels = node.getElementsByTagName(Constants.Panel);
        Panel panel=null;
        if(panels.getLength()>0){
             panel = getPanel((Element)panels.item(0));
        }
        int ID = getID(node);
        String name  = getATR(node, Constants.name);
        String load = getATR(node,Constants.load);
        return new Window(ID, name, Load.getLoad(load), list, panel);
    }
    private static Panel getPanel(Element node)throws WrongArgumentException{
        List<Table> tables = new ArrayList<>();
        NodeList table = node.getElementsByTagName(Constants.Table);
        for (int i = 0; i < table.getLength(); i++) {
            tables.add(getTable((Element)node.getElementsByTagName(Constants.Table).item(i)));
        }
        return new Panel(getID(node),tables);
        
    }
    private static Table getTable(Element node)throws WrongArgumentException{
        ObservableList<Row> listRow = FXCollections.observableArrayList();
        NodeList rows = node.getElementsByTagName(Constants.Row);
        int columns = Integer.valueOf(getATR(node,Constants.columns));
        for (int i = 0; i < rows.getLength(); i++) {
            listRow.add(getRow((Element)rows.item(i), columns));
        }return new Table(getID(node),getATR(node, Constants.name),columns,listRow);
    }
    private static Row getRow(Element node, int len)throws WrongArgumentException{
        NodeList nodeList = node.getElementsByTagName(Constants.Item);
        Item[] item = new Item[len];
        for (int i = 0; i < len; i++) {
            Element el = (Element) nodeList.item(i);
            item[i]  = getItem(el);
        }
        return new Row(getID(node),item);
    }
    private static Item getItem(Element node) throws WrongArgumentException{
        String type = getATR(node,Constants.dataType);
            String meter = getATR(node,Constants.meter);
            int ID = getID(node);
            Item item  = new Item(ID,type, Meter.getMeter(meter));
            item.setData(node.getTextContent());
            return item;
    }
    private static int getID(Element el)throws WrongArgumentException{
        String attribute = el.getAttribute(Constants.id);
        if(attribute.isEmpty())throw new WrongArgumentException("Nebyl, nebo byl špatně, zadán atribut: \"id\"  v elementu: \"" + el.getNodeName()+"\"");
        return Integer.decode(attribute);
    }
    private static String getATR(Element el,String name) throws WrongArgumentException{
        String attribute = el.getAttribute(name);
        if(attribute.isEmpty())throw new WrongArgumentException("Nebyl zadán atribut: \"" + name +"\"  v elementu: \"" + el.getNodeName()+"\"");
        return el.getAttribute(name);
    }
    private static class WrongArgumentException extends Exception{

        public WrongArgumentException(String message) {
            super(message);
        }
        
    }
}

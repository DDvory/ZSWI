
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author DDvory
 */
public class ConMenu {

    static void setMenu(BorderPane root) {
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(getFileMenu(), getEditMenu());
        root.setTop(menuBar);
    }

    private static Menu getFileMenu() {
        Menu fileMenu = new Menu("_Soubor");
        MenuItem newItem = new MenuItem("_Nový");
        MenuItem openItem = new MenuItem("_Otevřít");
        MenuItem saveItem = new MenuItem("_Uložit");
        MenuItem saveAsItem = new MenuItem("Uložit _Jako");
        MenuItem importItem = new MenuItem("_Importovat Jazyk");
        MenuItem exportItem = new MenuItem("_Exportovat Jazyk");

        newItem.setOnAction(event -> ConProject.createProject());
//        openItem.setOnAction(event -> loadProject());
        //saveItem.setOnAction(event -> );
        //saveAsItem.setOnAction(event -> );
        //importItem.setOnAction(event -> );
        //exportItem.setOnAction(event -> );

        fileMenu.getItems().addAll(
                newItem, openItem, new SeparatorMenuItem(),
                saveItem, saveAsItem, new SeparatorMenuItem(),
                importItem, exportItem);
        return fileMenu;
    }

    private static Menu getEditMenu() {
        Menu editMenu = new Menu("_Upravit");
        MenuItem treeItem = new MenuItem("Přidat _Větev");
        MenuItem panelItem = new MenuItem("Přidat _Panel");
        MenuItem tableItem = new MenuItem("Přidat _Tabulku");

        MenuItem treeRemoveItem = new MenuItem("Odebrat Větev");
        MenuItem panelRemoveItem = new MenuItem("Odebrat Panel");

//        treeItem.setOnAction(event -> createTreeItem(treeView.getSelectionModel().getSelectedItem()));
//        panelItem.setOnAction(event -> createPanel(treeView.getSelectionModel().getSelectedItem()));
//        tableItem.setOnAction(event -> addTable(treeView.getSelectionModel().getSelectedItem()));
//
//        treeRemoveItem.setOnAction(event -> removeTreeItem(treeView.getSelectionModel().getSelectedItem()));
//        panelRemoveItem.setOnAction(event -> removePanel(treeView.getSelectionModel().getSelectedItem()));

        editMenu.getItems().addAll(
                treeItem, panelItem, tableItem, new SeparatorMenuItem(),
                treeRemoveItem, panelRemoveItem);
        return editMenu;
    }

}

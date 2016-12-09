package Classes;

import javafx.scene.image.Image;
import javafx.scene.media.VideoTrack;

import java.util.ArrayList;

public class Restaurant extends Guidance {
    public enum Type {
        cafe, fastfood, cuisine, sitDown
    }

    private Type type;
    private ArrayList<Table> tables;
    private Menu menu;

    public Restaurant(){

    }

    public Restaurant(Type type, ArrayList<Table> tables, Menu menu) {
        this.type = type;
        this.tables = tables;
        this.menu = menu;
    }

    public Restaurant(String name, Location location, ArrayList<Image> photos, int rating, ArrayList<Contact> contacts, ArrayList<Comment> comments, ArrayList<String> amenties, String info, Type type, ArrayList<Table> tables, Menu menu) {
        super(name, location, photos, rating, contacts, comments, amenties, info);
        this.type = type;
        this.tables = tables;
        this.menu = menu;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public ArrayList<Table> getTables() {
        return tables;
    }

    public void setTables(ArrayList<Table> tables) {
        this.tables = tables;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}

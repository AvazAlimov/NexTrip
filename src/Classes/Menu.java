package Classes;

import java.util.ArrayList;

public class Menu {
    private ArrayList<String> foods;
    private ArrayList<String> price;

    public Menu(){

    }

    public Menu(ArrayList<String> foods, ArrayList<String> price) {
        this.foods = foods;
        this.price = price;
    }

    public void setPrice(ArrayList<String> price) {
        this.price = price;
    }

    public void setFoods(ArrayList<String> foods) {
        this.foods = foods;
    }

    public ArrayList<String> getFoods() {
        return foods;
    }

    public ArrayList<String> getPrice() {
        return price;
    }
}

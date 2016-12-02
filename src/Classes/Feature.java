package Classes;

import java.util.ArrayList;

public class Feature {
    private String name;
    private ArrayList<String> tools;
    private double price;
    private String rules;

    public Feature(){

    }

    public Feature(String name, ArrayList<String> tools, double price, String rules) {
        this.name = name;
        this.tools = tools;
        this.price = price;
        this.rules = rules;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public void setTools(ArrayList<String> tools) {
        this.tools = tools;
    }

    public ArrayList<String> getTools() {
        return tools;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getRules() {
        return rules;
    }
}

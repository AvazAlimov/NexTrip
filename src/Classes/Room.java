package Classes;

public class Room {
    private String type;
    private double price;
    private int numberOfPeople;

    public Room(){

    }

    public Room(String type, double price, int numberOfPeople) {
        this.type = type;
        this.price = price;
        this.numberOfPeople = numberOfPeople;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }
}

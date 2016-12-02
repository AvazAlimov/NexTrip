package Classes;

public class Table {
    private int numberOfPeople;
    public enum Type{
        children, family, business_lunch, couples
    }
    private Type type;

    public Table(){

    }

    public Table(int numberOfPeople, Type type) {
        this.numberOfPeople = numberOfPeople;
        this.type = type;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public Type getType() {
        return type;
    }
}

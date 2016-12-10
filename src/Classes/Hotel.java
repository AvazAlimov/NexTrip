package Classes;

public class Hotel extends Guidance {
    private double startingPrice;
    private double endingPrice;
    private int numberOfRooms;

    public Hotel() {

    }

    public double getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(double startingPrice) {
        this.startingPrice = startingPrice;
    }

    public double getEndingPrice() {
        return endingPrice;
    }

    public void setEndingPrice(double endingPrice) {
        this.endingPrice = endingPrice;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }
}

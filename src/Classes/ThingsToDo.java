package Classes;

import javafx.scene.image.Image;
import javafx.scene.media.VideoTrack;

import java.util.ArrayList;

public class ThingsToDo extends Guidance {
    private Date startDate;
    private Date endDate;
    private double price;

    public ThingsToDo(){

    }

    public ThingsToDo(Date startDate, Date endDate, double price) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
    }

    public ThingsToDo(String name, Location location, VideoTrack video, ArrayList<Image> photos, int rating, ArrayList<Contact> contacts, ArrayList<Comment> comments, ArrayList<String> amenties, String info, Date startDate, Date endDate, double price) {
        super(name, location, video, photos, rating, contacts, comments, amenties, info);
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public double getPrice() {
        return price;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}

package Classes;

import javafx.scene.image.Image;
import java.util.ArrayList;

public class Guidance {
    private int rating;
    private String name;
    private String info;
    private Location location;
    private ArrayList<Image> photos;
    private ArrayList<Contact> contacts;
    private ArrayList<Comment> comments;
    private ArrayList<String> amenties;

    public Guidance() {

    }

    public Guidance(String name, Location location, ArrayList<Image> photos, int rating, ArrayList<Contact> contacts, ArrayList<Comment> comments, ArrayList<String> amenties, String info) {
        this.name = name;
        this.location = location;
        this.photos = photos;
        this.rating = rating;
        this.contacts = contacts;
        this.comments = comments;
        this.amenties = amenties;
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public ArrayList<Image> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<Image> photos) {
        this.photos = photos;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public ArrayList<String> getAmenties() {
        return amenties;
    }

    public void setAmenties(ArrayList<String> amenties) {
        this.amenties = amenties;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}

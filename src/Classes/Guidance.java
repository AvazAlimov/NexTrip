package Classes;

import java.util.ArrayList;

public class Guidance {
    private int id;
    private int rating;
    private String name;
    private String info;
    private String location;
    private ArrayList<String> photos;
    private ArrayList<Contact> contacts;
    private ArrayList<Comment> comments;
    private ArrayList<String> amenties;

    public Guidance() {

    }

    public String ammenityToString() {
        String string = "";
        for (String amenity : amenties)
            string += amenity + "■";
        return string.substring(0, string.length() - 1);
    }

    public String commentsToString() {
        String string = "";
        for (Comment comment : comments)
            string += comment.toString() + "■";
        return string.substring(0, string.length() - 1);
    }

    public String contactsToString() {
        String string = "";
        for (Contact contact : contacts)
            string += contact.toString() + "■";
        return string.substring(0, string.length() - 1);
    }

    public String getImageLinks() {
        String string = "";
        for (String image : photos)
            string += image + "■";
        return string.substring(0, string.length() - 1);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setImages(String string) {
        int index = 0;
        for (int i = 0; i < string.length(); i++)
            if (string.charAt(i) == '■' || i == string.length() - 1) {
                photos.add(string.substring(index, i));
                index = i + 1;
            }
    }

    public void setContacts(String string) {
        int index = 0;
        for (int i = 0; i < string.length(); i++)
            if (string.charAt(i) == '■' || i == string.length() - 1) {
                contacts.add(new Contact(string.substring(index, i)));
                index = i + 1;
            }
    }

    public void setComments(String string) {
        int index = 0;
        for (int i = 0; i < string.length(); i++)
            if (string.charAt(i) == '■' || i == string.length() - 1) {
                comments.add(new Comment(string.substring(index, i)));
                index = i + 1;
            }
    }

    public void setAmenities(String string) {
        int index = 0;
        for (int i = 0; i < string.length(); i++)
            if (string.charAt(i) == '■' || i == string.length() - 1) {
                amenties.add(string.substring(index, i));
                index = i + 1;
            }
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

    public void setPhotos(ArrayList<String> photos) {
        this.photos = photos;
    }

    public ArrayList<String> getPhotos() {
        return photos;
    }
}

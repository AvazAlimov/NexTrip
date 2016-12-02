package Classes;

import javafx.scene.image.Image;
import javafx.scene.media.VideoTrack;

import java.util.ArrayList;

public class Hotel extends Guidance {
    private ArrayList<Room> rooms;

    public Hotel(){

    }

    public Hotel(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public Hotel(String name, Location location, VideoTrack video, ArrayList<Image> photos, int rating, ArrayList<Contact> contacts, ArrayList<Comment> comments, ArrayList<String> amenties, String info, ArrayList<Room> rooms) {
        super(name, location, video, photos, rating, contacts, comments, amenties, info);
        this.rooms = rooms;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }
}

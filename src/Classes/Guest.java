package Classes;

public class Guest extends User {
    private String username;
    private Location location;

    public Guest(){

    }

    public Guest(String login, String password, String username, Location location) {
        super(login, password);
        this.username = username;
        this.location = location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public Location getLocation() {
        return location;
    }
}

package Classes;

public class Guest extends User {
    private String username;

    public Guest(){

    }

    public Guest(String login, String password, String username) {
        super(login, password);
        this.username = username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}

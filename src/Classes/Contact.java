package Classes;

public class Contact {
    public enum Type{
        Facebook, Site, PhoneNumber, Mail, Telegram, Other
    }
    private Type type;
    private String source;

    public Contact(){

    }

    public Contact(Type type, String source) {
        this.type = type;
        this.source = source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getSource() {
        return source;
    }

    public Type getType() {
        return type;
    }
}

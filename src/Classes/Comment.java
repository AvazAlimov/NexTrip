package Classes;

public class Comment {
    private String comment;
    private Date writtenDate;
    private Guest guest;

    public Comment(){

    }

    public Comment(String comment, Date writtenDate, Guest guest) {
        this.comment = comment;
        this.writtenDate = writtenDate;
        this.guest = guest;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public void setWrittenDate(Date writtenDate) {
        this.writtenDate = writtenDate;
    }

    public Date getWrittenDate() {
        return writtenDate;
    }

    public Guest getGuest() {
        return guest;
    }

    public String getComment() {
        return comment;
    }
}

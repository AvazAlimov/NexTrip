package Classes;

public class Client extends User{
    private int numberOfCredits;
    private int numberOfUsedCredits;
    private Date startDate;
    private Date endDate;

    public Client(){

    }

    public Client(String login, String password, int numberOfCredits, int numberOfUsedCredits, Date startDate, Date endDate) {
        super(login, password);
        this.numberOfCredits = numberOfCredits;
        this.numberOfUsedCredits = numberOfUsedCredits;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setNumberOfCredits(int numberOfCredits) {
        this.numberOfCredits = numberOfCredits;
    }

    public void setNumberOfUsedCredits(int numberOfUsedCredits) {
        this.numberOfUsedCredits = numberOfUsedCredits;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public int getNumberOfCredits() {
        return numberOfCredits;
    }

    public int getNumberOfUsedCredits() {
        return numberOfUsedCredits;
    }
}

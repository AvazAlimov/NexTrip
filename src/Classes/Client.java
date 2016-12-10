package Classes;

public class Client extends User {
    private int numberOfCredits;
    private int[] objectId;
    private Date startDate;
    private Date endDate;

    public Client() {
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setNumberOfCredits(int numberOfCredits) {
        this.numberOfCredits = numberOfCredits;
        objectId = new int[numberOfCredits];
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

    public int[] getObjectId() {
        return objectId;
    }

    public void setObjectId(int[] objectId) {
        this.objectId = objectId;
    }

    public String getIds() {
        String ids = "";
        for (int i = 0; i < numberOfCredits; i++)
            ids += objectId[i] + "/";
        return ids.substring(0,ids.length() - 1);
    }
}

package Classes;

public class Client extends User {
    private int numberOfCredits;
    private String[] objectId;
    private Date startDate;
    private Date endDate;

    public Client() {
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setNumberOfCredits(int numberOfCredits) {
        this.numberOfCredits = numberOfCredits;
        objectId = new String[numberOfCredits];
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

    public String getIds() {
        String ids = "";
        for (int i = 0; i < numberOfCredits; i++)
            ids += objectId[i] + "/";
        return ids.substring(0,ids.length() - 1);
    }

    public void setObjectId(String objects) {
        int index = 0;
        int start = 0;
        for (int i = 0; i < objects.length(); i++)
            if (objects.charAt(i) == '/' || i == objects.length() - 1) {
                int last = i == objects.length() - 1 ? i + 1 : i;
                objectId[index] = objects.substring(start, last);
                start = i + 1;
                index++;
            }
    }

    public String[] getObjectId() {
        return objectId;
    }

    public boolean addObjectId(String id) {
        for(int i = 0; i < objectId.length; i++)
            if(objectId[i].equals("null")){
                objectId[i] = id;
                return true;
            }
        return false;
    }

    public boolean canAddObject() {
        for (String anObjectId : objectId)
            if (anObjectId.equals("null")) {
                return true;
            }
        return false;
    }
}

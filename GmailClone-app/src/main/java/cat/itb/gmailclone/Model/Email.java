package cat.itb.gmailclone.Model;

import java.util.Date;

public class Email {
    int image;
    String origin;
    String title;
    String description;
    Date date;
    boolean star;
    String[] inboxes;

    public Email(int image, String origin, String title, String description, Date date, boolean star, String[] inboxes) {
        this.image = image;
        this.origin = origin;
        this.title = title;
        this.description = description;
        this.date = date;
        this.star = star;
        this.inboxes = inboxes;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public boolean isStar() {
        return star;
    }

    public String[] getInboxes() {
        return inboxes;
    }

    public void setInboxes(String[] inboxes) {
        this.inboxes = inboxes;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setStar(boolean star) {
        this.star = star;
    }

    @Override
    public String toString() {
        int day = date.getDay();
        long month = date.getTime();
        String realMonth = "Wrong Month";
        /*
        switch (month) {
            case 1:
                realMonth = "jan.";
                break;
            case 2:
                realMonth = "feb.";
                break;
            case 3:
                realMonth = "mar.";
                break;
            case 4:
                realMonth = "abr.";
                break;
            case 5:
                realMonth = "may.";
                break;
            case 6:
                realMonth = "jun.";
                break;
            case 7:
                realMonth = "jul.";
                break;
            case 8:
                realMonth = "ago.";
                break;
            case 9:
                realMonth = "sep.";
                break;
            case 10:
                realMonth = "oct.";
                break;
            case 11:
                realMonth = "nov.";
                break;
            case 12:
                realMonth = "dic.";
                break;
        }

         */
        return "" + day + " " + month;
    }
}

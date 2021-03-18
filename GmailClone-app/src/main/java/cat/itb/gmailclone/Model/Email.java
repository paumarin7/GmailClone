package cat.itb.gmailclone.Model;

import java.io.Serializable;
import java.util.Date;

public class Email implements Serializable {
    int image;
    String origin;
    String title;
    String description;
    Date date;
    boolean star;
    boolean read;
    String[] inboxes;

    public Email(int image, String origin, String title, String description, Date date, boolean star, boolean read, String[] inboxes) {
        this.image = image;
        this.origin = origin;
        this.title = title;
        this.description = description;
        this.date = date;
        this.star = star;
        this.read = read;
        this.inboxes = inboxes;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
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

    public boolean isStar() {
        return star;
    }

    public void setStar(boolean star) {
        this.star = star;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public String[] getInboxes() {
        return inboxes;
    }

    public void setInboxes(String[] inboxes) {
        this.inboxes = inboxes;
    }
}

package cat.itb.gmailclone.Model;

import java.io.Serializable;
import java.util.Date;

public class Email implements Serializable {
    String photoUrl;
    String origin;
    String to;
    String title;
    String body;
    Date date;
    boolean favorite;
    boolean read;
    String inbox;


    public Email(String photoUrl, String origin, String to, String title, String body, Date date, boolean favorite, boolean read, String inbox) {
        this.photoUrl = photoUrl;
        this.origin = origin;
        this.to = to;
        this.title = title;
        this.body = body;
        this.date = date;
        this.favorite = favorite;
        this.read = read;
        this.inbox = inbox;
    }

    public Email() {
    }


    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setImage(String photoUrl) {
        this.photoUrl = photoUrl;
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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public String getInbox() {
        return inbox;
    }

    public void setInboxes(String inbox) {
        this.inbox = inbox;
    }
}

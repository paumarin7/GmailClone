package cat.itb.gmailclone.Model;

import android.widget.ImageView;

import com.google.android.material.checkbox.MaterialCheckBox;

import java.util.Date;

public class Email {
    int image;
    String title;
    String description;
    Date date;
    boolean star;

    public Email(int image, String title, String description, Date date, boolean star) {
        this.image = image;
        this.title = title;
        this.description = description;
        this.date = date;
        this.star = star;
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

    public boolean getStar() {
        return star;
    }

    public void setStar(boolean star) {
        this.star = star;
    }
}

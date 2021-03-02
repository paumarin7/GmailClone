package cat.itb.gmailclone.Model;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cat.itb.gmailclone.R;

public class EmailViewModel extends ViewModel {

     public static List<Email> emails = new ArrayList<>();
    public EmailViewModel(){
        emails.add(new Email(R.drawable.google_logo,"Primer email", "Como estas", new Date(12-2),true));
        emails.add(new Email(R.drawable.google_logo,"Segundo email", "Como estas", new Date(2020-12-2),true));
        emails.add(new Email(R.drawable.google_logo,"Tercer email", "Como estas", new Date(2020-12-2),true));
    }


}

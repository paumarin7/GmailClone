package cat.itb.gmailclone.Model;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cat.itb.gmailclone.R;

public class EmailViewModel extends ViewModel {

    public static List<Email> emails = new ArrayList<>();

    public EmailViewModel() {
        emails.add(new Email(R.drawable.google_logo, "Google", "Primer email", "Como estas", new Date("03/16/2021"), true, true, new String[]{"Received", "Deleted"}));
        emails.add(new Email(R.drawable.google_logo, "Google", "Segundo email", "Como estas", new Date("01/21/2021"), true, false, new String[]{"Received", "Important"}));
        emails.add(new Email(R.drawable.google_logo, "Google", "Tercer email", "Como estas", new Date(String.valueOf(Calendar.getInstance().getTime())), true, true, new String[]{"Received", "Draft"}));
    }


}

package cat.itb.gmailclone.Model;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cat.itb.gmailclone.R;

public class EmailViewModel extends ViewModel {

    public static List<Email> emails = new ArrayList<>();

    public EmailViewModel() {
        emails.add(new Email(R.drawable.google_logo, "Google", "Primer email", "Como estas", new Date(12 - 2), true, true, new String[]{"Received", "Deleted"}));
        emails.add(new Email(R.drawable.google_logo, "Google", "Segundo email", "Como estas", new Date(2020 - 12 - 2), true, false, new String[]{"Received", "Important"}));
        emails.add(new Email(R.drawable.google_logo, "Google", "Tercer email", "Como estas", new Date(2020 - 12 - 2), true, true, new String[]{"Received", "Draft"}));
    }


}

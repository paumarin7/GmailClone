package cat.itb.gmailclone.Fragments;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;

import cat.itb.gmailclone.Model.Email;
import cat.itb.gmailclone.R;


import static cat.itb.gmailclone.Resources.GetAccountEmails.getAccount;


public class Send_email_Fragment extends Fragment {
        Spinner spinnerEmails;
        ImageButton send;
        TextInputEditText to;
        TextInputEditText title;
        TextInputEditText body;

    DatabaseReference imgref;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.send_email, container, false);
        imgref = FirebaseDatabase.getInstance().getReference();
        spinnerEmails = v.findViewById(R.id.spinner_Emails);
        send = v.findViewById(R.id.enviarEmail);
        to = v.findViewById(R.id.originNewEmail);
        title = v.findViewById(R.id.titleNewEmail);
        body = v.findViewById(R.id.bodyNewEmail);



        AccountManager accountManager = AccountManager.get(getContext());
        Account[] account = getAccount(accountManager);
        String[] items = new String[account.length];

        for (int i = 0; i < account.length; i++) {
            items[i] = account[i].name;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_dropdown_item, items);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEmails.setAdapter(adapter);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key = imgref.push().getKey();
                //       this.origin = origin;
                //        this.title = title;
                //        this.body = body;
//   int image;
//    String origin;
//    String title;
//    String body;
//    Date date;
//    boolean favorite;
//    boolean read;
//    String[] inboxes;

                Date currentTime = Calendar.getInstance().getTime();

                Toast.makeText(getContext(), spinnerEmails.getSelectedItem().toString(),Toast.LENGTH_LONG).show();
                Email m = new Email(0,spinnerEmails.getSelectedItem().toString(),to.getText().toString(),title.getText().toString(), body.getText().toString(), currentTime,false,false);
                imgref.child(key).setValue(m);
            }
        });
        return  v;
    }
}

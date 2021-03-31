package cat.itb.gmailclone.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.checkbox.MaterialCheckBox;

import cat.itb.gmailclone.Model.*;
import cat.itb.gmailclone.R;

public class EmailFragment extends Fragment {

    TextView subjectTextView;
    MaterialButton inboxLabel;
    MaterialCheckBox favCheckBox;

    ImageView originProfilePicture;
    TextView originTextView;
    TextView dateTextView;
    Button replyButton;
    Button optionsButton;

    TextView bodyTextView;




    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getParentFragmentManager().setFragmentResultListener("email", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {

                Email email = (Email) result.getSerializable("email");

                subjectTextView.setText(email.getTitle());
            //    inboxLabel.setText(email.getInboxes().toString());
                if (email.isFavorite()) {
                    favCheckBox.setChecked(true);
                } else {
                    favCheckBox.setChecked(false);
                }
                bodyTextView.setText(email.getBody());

                originTextView.setText(email.getOrigin());
                dateTextView.setText(String.valueOf(email.getDate()));



            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.email_view, container, false);

        subjectTextView = v.findViewById(R.id.subjectTextView);
        bodyTextView = v.findViewById(R.id.bodyTextView);
        inboxLabel = v.findViewById(R.id.inboxLabel);
        favCheckBox = v.findViewById(R.id.favCheckBox);

        originProfilePicture = v.findViewById(R.id.originProfilePicture);
        originTextView = v.findViewById(R.id.originTextView);
        dateTextView = v.findViewById(R.id.dateTextView);
        replyButton = v.findViewById(R.id.replyButton);
        optionsButton = v.findViewById(R.id.optionsButton);



        return v;
    }


}

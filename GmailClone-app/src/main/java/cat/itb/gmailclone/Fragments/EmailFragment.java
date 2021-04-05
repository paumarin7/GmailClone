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
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.Navigation;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.checkbox.MaterialCheckBox;

import cat.itb.gmailclone.Model.Email;
import cat.itb.gmailclone.R;

public class EmailFragment extends Fragment {

    MaterialToolbar toolbar;

    TextView subjectTextView;
    MaterialButton inboxLabel;
    MaterialCheckBox favCheckBox;

    ImageView originProfilePicture;
    TextView originTextView;
    TextView dateTextView;
    MaterialButton originReplyButton;
    Button optionsButton;

    TextView bodyTextView;

    View.OnClickListener replyButtonListener;

    MaterialButton replyButton;
    MaterialButton replyAllButton;
    MaterialButton forwardButton;

    ActionMenuItemView archiveButton;
    ActionMenuItemView deleteButton;
    ActionMenuItemView markAsUnreadButton;


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

                originTextView.setText(email.getOrigin());
                dateTextView.setText(String.valueOf(email.getDate()));
                //originReplyButton.setOnClickListener(replyButtonListener);


                bodyTextView.setText(email.getBody());

            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.email_view, container, false);

        toolbar = v.findViewById(R.id.emailToolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getActivity(), R.id.email_view).navigate(R.id.mainFragmentRecyclerView);
            }
        });

        subjectTextView = v.findViewById(R.id.subjectTextView);
        bodyTextView = v.findViewById(R.id.bodyTextView);
        inboxLabel = v.findViewById(R.id.inboxLabel);
        favCheckBox = v.findViewById(R.id.favCheckBox);

        originProfilePicture = v.findViewById(R.id.originProfilePicture);
        originTextView = v.findViewById(R.id.address);
        dateTextView = v.findViewById(R.id.dateTextView);
        originReplyButton = v.findViewById(R.id.replyButton);
        optionsButton = v.findViewById(R.id.optionsButton);

        replyButton = v.findViewById(R.id.replyButton);
        replyAllButton = v.findViewById(R.id.replyAllButton);
        forwardButton = v.findViewById(R.id.forwardButton);

        archiveButton = v.findViewById(R.id.archive);
        deleteButton = v.findViewById(R.id.delete);
        markAsUnreadButton = v.findViewById(R.id.markAsUnread);

        return v;
    }


}

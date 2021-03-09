package cat.itb.gmailclone.Fragments.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.zip.Inflater;

import cat.itb.gmailclone.Model.Email;
import cat.itb.gmailclone.Model.EmailViewModel;
import cat.itb.gmailclone.R;

import static cat.itb.gmailclone.Model.EmailViewModel.emails;

public class RecyclerView_email extends Fragment {
    RecyclerView recyclerView;
    EmailViewModel emailModel;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.recycler_view_basic , container, false);
        emailModel =  new ViewModelProvider(this).get(EmailViewModel.class);
        recyclerView = v.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        EmailAdapter adapter = new EmailAdapter(emails);
//        adapter.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                bundle.putSerializable("book",books.get(recyclerView.getChildAdapterPosition(v)));
//                getParentFragmentManager().setFragmentResult("key",bundle);
//                Navigation.findNavController(getActivity(),R.id.recyclerview).navigate(R.id.bookFragment);
//            }
//        });
        recyclerView.setAdapter(adapter);


        return v;

    }
}

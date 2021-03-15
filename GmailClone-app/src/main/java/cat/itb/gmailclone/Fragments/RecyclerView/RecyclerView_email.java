package cat.itb.gmailclone.Fragments.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import cat.itb.gmailclone.Model.EmailViewModel;
import cat.itb.gmailclone.R;

import static cat.itb.gmailclone.Model.EmailViewModel.emails;

public class RecyclerView_email extends Fragment {
    RecyclerView recyclerView;
    EmailViewModel emailModel;
    DrawerLayout drawer;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.main_fragment, container, false);
        emailModel = new ViewModelProvider(this).get(EmailViewModel.class);
        recyclerView = v.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        EmailAdapter adapter = new EmailAdapter(emails);

        Toolbar toolbar  = v.findViewById(R.id.topAppBar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);


        drawer = v.findViewById(R.id.draweLayout);
        final ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(getActivity(), drawer, R.string.open, R.string.close);
        drawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.open();
            }
        });

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

    // NO PARECE QUE ESTE METODO HAGA NADA (NAVIGATION DRAWER)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawer.openDrawer(GravityCompat.START);
            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }
    }



}

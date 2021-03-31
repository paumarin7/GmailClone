package cat.itb.gmailclone.Fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import cat.itb.gmailclone.Fragments.RecyclerView.EmailAdapter;
import cat.itb.gmailclone.Model.EmailViewModel;
import cat.itb.gmailclone.R;
import cat.itb.gmailclone.Resources.CircleTransformation;

import static cat.itb.gmailclone.Model.EmailViewModel.emails;

public class MainFragment extends Fragment {
    RecyclerView recyclerView;
    EmailViewModel emailModel;
    DrawerLayout drawer;
    ImageButton profileIcon;

    private FirebaseAuth mAuth;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.main_fragment, container, false);
        mAuth = FirebaseAuth.getInstance();
        emailModel = new ViewModelProvider(this).get(EmailViewModel.class);
        recyclerView = v.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        EmailAdapter adapter = new EmailAdapter(emails);
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getActivity());
        Toast.makeText(getContext(),acct.getPhotoUrl()+"",Toast.LENGTH_LONG).show();
        Toolbar toolbar  = v.findViewById(R.id.topAppBar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        profileIcon = v.findViewById(R.id.mainFragmentProfileIcon);
        Bitmap bitmap = null;

        Picasso.with(getContext())
                .load(acct.getPhotoUrl())
                .placeholder(R.drawable.userimage)
                .resize(100, 100)
                .centerCrop().transform(new CircleTransformation())
                .into(profileIcon);
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

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                b.putSerializable("email", emails.get(recyclerView.getChildAdapterPosition(v)));
                getParentFragmentManager().setFragmentResult("email", b);
                Navigation.findNavController(getActivity(),R.id.recyclerview).navigate(R.id.emailFragment);
            }
        });



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

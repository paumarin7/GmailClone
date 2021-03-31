package cat.itb.gmailclone.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.ObservableSnapshotArray;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import cat.itb.gmailclone.Fragments.RecyclerView.EmailAdapter;
import cat.itb.gmailclone.Model.Email;
import cat.itb.gmailclone.R;
import cat.itb.gmailclone.Resources.CircleTransformation;

import static android.content.ContentValues.TAG;


public class MainFragment extends Fragment {
    RecyclerView recyclerView;
    DrawerLayout drawer;
    ImageButton profileIcon;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    private static final int RC_SIGN_IN = 123;
    private FirebaseAuth mAuth;
    private Button signIn;
    private GoogleSignInClient mGoogleSignInClient;
    public static List<Email> emails = new ArrayList<>();
    public static  GoogleSignInAccount acct;
    EmailAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createRequest();
    }

    @Override
    public void onStart() {
        super.onStart();
//        adapter.startListening();

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.main_fragment, container, false);

        mAuth.getCurrentUser();

        acct = GoogleSignIn.getLastSignedInAccount(getActivity());
        Toast.makeText(getContext(), ""+emails.size(),Toast.LENGTH_LONG).show();
        recyclerView = v.findViewById(R.id.recyclerview);

      //  Query filter = database.getReference().child("origin").equalTo(acct.getEmail());
    //    FirebaseRecyclerOptions<Email> options = new FirebaseRecyclerOptions.Builder<Email>().setQuery(filter, Email.class).build();


      //   adapter = new EmailAdapter(options);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        Toolbar toolbar = v.findViewById(R.id.topAppBar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        profileIcon = v.findViewById(R.id.mainFragmentProfileIcon);
        Bitmap bitmap = null;

        if (acct != null) {
            Picasso.with(getContext())
                    .load(acct.getPhotoUrl())
                    .placeholder(R.drawable.userimage)
                    .resize(130, 130)
                    .centerCrop().transform(new CircleTransformation())
                    .into(profileIcon);
        }


        drawer = v.findViewById(R.id.draweLayout);
        final ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(getActivity(), drawer, R.string.open, R.string.close);
        drawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.open();
            }
        });

       // adapter.setOnClickListener(new View.OnClickListener() {
      //      @Override
      //      public void onClick(View v) {
     //           Bundle b = new Bundle();
      //          b.putSerializable("email", emails.get(recyclerView.getChildAdapterPosition(v)));
       //         getParentFragmentManager().setFragmentResult("email", b);
       //         Navigation.findNavController(getActivity(), R.id.recyclerview).navigate(R.id.emailFragment);
       //     }
 //       });


      //  recyclerView.setAdapter(adapter);


        profileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
        return v;

    }

    // NO PARECE QUE ESTE METODO HAGA NADA (NAVIGATION DRAWER)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawer.openDrawer(GravityCompat.START);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }


    private void createRequest() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail().requestProfile()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(getContext(), gso);
    }

    private void signIn() {
        mGoogleSignInClient.signOut();
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase

                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());

            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener((Activity) getContext(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Navigation.findNavController(getActivity(), R.id.mainFragment).navigate(R.id.recyclerView_email);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());

                        }
                    }
                });
    }


}

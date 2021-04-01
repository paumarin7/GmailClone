package cat.itb.gmailclone.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.DateSorter;
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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
import java.util.Collection;
import java.util.Collections;
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
    public static  GoogleSignInAccount acct;
    EmailAdapter adapter;
    FloatingActionButton writeEmail;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createRequest();
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.main_fragment, container, false);


        //FireBase
        mAuth = FirebaseAuth.getInstance();
        acct = GoogleSignIn.getLastSignedInAccount(getActivity());
        Query filter = database.getReference().orderByChild("to").equalTo(acct.getEmail());
        final FirebaseRecyclerOptions<Email> options = new FirebaseRecyclerOptions.Builder<Email>().setQuery(filter, Email.class).build();



        //Ir a escribir email
        writeEmail = v.findViewById(R.id.writeEmail);
        writeEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getActivity(), R.id.recyclerview).navigate(R.id.send_email_Fragment);
            }
        });


        //RecyclerView
        recyclerView = v.findViewById(R.id.recyclerview);
        adapter = new EmailAdapter(options);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setReverseLayout(true);
        recyclerView.setLayoutManager(mLayoutManager);
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                ObservableSnapshotArray<Email> mSnapshots = options.getSnapshots();
                Email e =  mSnapshots.get(recyclerView.getChildAdapterPosition(v));
                b.putSerializable("email",  e);
                getParentFragmentManager().setFragmentResult("email", b);
                Navigation.findNavController(getActivity(), R.id.recyclerview).navigate(R.id.emailFragment);
            }
        });
        recyclerView.setAdapter(adapter);


        //TopAppBar
        Toolbar toolbar = v.findViewById(R.id.topAppBar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.open();
            }
        });


        //Email Icon
        profileIcon = v.findViewById(R.id.mainFragmentProfileIcon);
        profileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
        Bitmap bitmap = null;
        if (acct != null) {
            Picasso.with(getContext())
                    .load(acct.getPhotoUrl())
                    .placeholder(R.drawable.userimage)
                    .resize(130, 130)
                    .centerCrop().transform(new CircleTransformation())
                    .into(profileIcon);
        }

        //NavigationDrawer
        drawer = v.findViewById(R.id.draweLayout);
        final ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(getActivity(), drawer, R.string.open, R.string.close);
        drawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        return v;
    }



    //Metodo para Conseguir conectarse con google acc
    private void createRequest() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail().requestProfile()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(getContext(), gso);
    }

    //Iniciar el intent para conectarse a la cuenta y poder elegir que cuenta usamos
    private void signIn() {
        mGoogleSignInClient.signOut();
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    //Metodo necesario en fragment para poder usar onActivityResult
    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);

    }

    //Metodo Para resolver Intents
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

    //Metodo que resuelve una task para conseguir conectarse con la cuenta de google
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

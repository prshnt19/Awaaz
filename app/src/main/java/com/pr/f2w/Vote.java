package com.pr.f2w;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.*;

public class Vote extends AppCompatActivity implements VoteAdapter.voting_listener {

    private RecyclerView recyclerView;
    private VoteAdapter adapter;
    private List<Card> cardList;
    static private  String userID;
static Firebase firebaseRef;
    static private DatabaseReference mDatabase;
    static private FirebaseAuth.AuthStateListener mAuthListener;
    static private FirebaseAuth mAuth;
    //User u1 = new User("p@g.c","0");
    //private int votecheck;
ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vote);
        pd = new ProgressDialog(this);
        pd.setMessage("Registering your vote....");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initCollapsingToolbar();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        cardList = new ArrayList<>();
        adapter = new VoteAdapter(this, cardList, this);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareCard();

        try {
            Glide.with(this).load(R.drawable.back).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View view){
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
        public void voteresults(View v){
            Intent intent1 = new Intent(Vote.this,VoteResult.class);
            startActivity(intent1);
        }

    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar title on scroll
     */
    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.votebar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    //code for adapter
    private void prepareCard() {
        int[] covers = new int[]{
                R.drawable.prashant,
                R.drawable.arpit,
                R.drawable.deepak,
                R.drawable.aditya,
                R.drawable.abhishek,
                R.drawable.smita,
                R.drawable.gaurav,
                R.drawable.shubham,
                R.drawable.poorva,
                R.drawable.amisha,
                R.drawable.queen,
                R.drawable.arihant
        };

        Card a = new Card("Prashant", covers[0]);
        cardList.add(a);

        a = new Card("Arpit", covers[1]);
        cardList.add(a);

       a = new Card("Deepak", covers[2]);
        cardList.add(a);

        a = new Card("Aditya", covers[3]);
        cardList.add(a);

        a = new Card("Abhishek", covers[4]);
        cardList.add(a);

        a = new Card("Smita", covers[5]);
        cardList.add(a);

        a = new Card("Gaurav", covers[6]);
        cardList.add(a);

        a = new Card("Shubham", covers[7]);
        cardList.add(a);

        a = new Card("Poorva", covers[8]);
        cardList.add(a);

        a = new Card("Amisha", covers[9]);
        cardList.add(a);

        a = new Card("Queen", covers[10]);
        cardList.add(a);

        a = new Card("Arihant", covers[11]);
        cardList.add(a);

        adapter.notifyDataSetChanged();
    }

    @Override
    public void oncallme(final int position) {
        mDatabase= FirebaseDatabase.getInstance().getReference();
        pd.show();

        //    firebaseRef = new Firebase("https://login-6bb26.firebaseio.com/users");
        //  Query query1 =mDatabase.orderByChild("users");
        //  query1.setValue("19");
        mAuth = FirebaseAuth.getInstance();


        FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();
        //  final    Query query2= query1.orderB
        mDatabase.child("users").child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Map<String,Integer> lap = dataSnapshot.getValue(Map.class);
                //  int vote =lap.get("vote");
                //   int votecheck =lap.get("voted");
                if(dataSnapshot.exists()){
                    Integer votecheck = dataSnapshot.getValue(Integer.class);

                    if (votecheck==0) {
                       final String sposition = Integer.toString(position);
                        //    Log.d("Tag",votecheck);
                        mDatabase.child("users").child(userID).setValue(1);
                        mDatabase.child("seniors").child(sposition).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                Integer votes=dataSnapshot.getValue(Integer.class);
                                votes++;
                                mDatabase.child("seniors").child(sposition).setValue(votes);
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                            pd.dismiss();
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Vote.this);
                        alertDialog.setTitle("Done").setMessage("Your vote has been counted!!").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }

                        }).show();
                    }
                    else
                    {   pd.dismiss();
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Vote.this);
                        alertDialog.setTitle("You already voted").setMessage("You can only vote once!!").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }

                        }).show();}
                }
                else
                    Toast.makeText(Vote.this,"0",LENGTH_SHORT).show();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

     public void newVote(final int position) {
        final Singleton pass = Singleton.getInstance();
        mDatabase= FirebaseDatabase.getInstance().getReference();


    //    firebaseRef = new Firebase("https://login-6bb26.firebaseio.com/users");
      //  Query query1 =mDatabase.orderByChild("users");
     //  query1.setValue("19");
        mAuth = FirebaseAuth.getInstance();


        FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();
  //  final    Query query2= query1.orderByChild("arpitarpit");

//firebaseRef.setValue("arpit");
 /*   static private void showData(DataSnapshot dataSnapshot,int position) {
        User uInfo = new User();
        for(DataSnapshot ds : dataSnapshot.getChildren()){
            uInfo.setEmail(ds.child(userID).getValue(User.class).getEmail()); //set the email
            uInfo.setVoted(ds.child(userID).getValue(User.class).getVoted());
            mDatabase.child("tt").setValue(1);
        }

    }*/


     mDatabase.child("users").child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Map<String,Integer> lap = dataSnapshot.getValue(Map.class);
                //  int vote =lap.get("vote");
                //   int votecheck =lap.get("voted");
                if(dataSnapshot.exists()){
                Integer votecheck = dataSnapshot.getValue(Integer.class);

                if (votecheck==0) {
                //    Log.d("Tag",votecheck);
                    mDatabase.child("users").child(userID).setValue(1);

                }
                else
                {

                }
            }
            else
                Toast.makeText(Vote.this,"0",LENGTH_SHORT).show();
     }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
    }
    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

}
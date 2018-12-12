package com.pr.f2w;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public  class HomeActivity2 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ImageButton QandA;
    ImageButton complaint;
    DatabaseReference mDatabase;
    Random random = new Random();
    Singleton x = Singleton.getInstance();
    String[] tips = {"At the end of the day, the goals are simple: safety and security.",
            "The safety of the people shall be the highest law.",
            "Safety is something that happens between your ears, not something you hold in your hands.",
            "Less intelligence capacity equals less safety.",
            "To forgive is the highest, most beautiful form of love. In return, you will receive untold peace and happiness.",
            "The world is a dangerous place, not because of those who look on and do nothing.",
            "No one heals himself by wounding another."
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "For Feedback Contact : \nPrashant: 7689016272    Arpit: 8561842219", 7000)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        int rand = random.nextInt(7);
        if(x.j==1) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(HomeActivity2.this);

            alertDialog.setTitle("A tip for you!").setMessage(tips[rand]).setPositiveButton("OK Got it!", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //do nothing
                }
            }).show();
            x.j=0;
        }

        QandA = (ImageButton) findViewById(R.id.qna);
        complaint = (ImageButton) findViewById(R.id.complaint);
    }

    public void do_qna(View v) {
        Intent intent = new Intent(HomeActivity2.this, QandA.class);
        startActivity(intent);
    }

    public void do_complaint(View view) {
        Intent intent1 = new Intent(HomeActivity2.this, Complaint.class);
        startActivity(intent1);
    }

    public void do_results(View v) {
        Intent intent = new Intent(HomeActivity2.this, VoteResult.class);
        startActivity(intent);
    }

    public void do_pics(View v) {
        Intent intent = new Intent(HomeActivity2.this, Picsend.class);
        startActivity(intent);
    }

    public void read(View v) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("login").child("arpitarpit").child("voted").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Map<String,Integer> lap = dataSnapshot.getValue(Map.class);
                //  int vote =lap.get("vote");
                //   int votecheck =lap.get("voted");
                String votecheck = dataSnapshot.getValue(String.class);
                mDatabase.child("22").child("voted").setValue("1");
                if (votecheck.isEmpty()) {
                    //    Log.d("Tag",votecheck);
                    mDatabase.child("arpitarpit").child("voted").setValue("1");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    /*public void logout(View v){
        FirebaseAuth.getInstance().signOut();
        Intent intent =new Intent(HomeActivity.this,MainActivity.class);
        startActivity(intent);

    }*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.settings) {

            // Handle the camera action
        } else if (id == R.id.logout) {
           // FirebaseAuth.getInstance().signOut();
            Intent intent =new Intent(HomeActivity2.this,MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        } else if (id == R.id.share) {

        } else if (id == R.id.about) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

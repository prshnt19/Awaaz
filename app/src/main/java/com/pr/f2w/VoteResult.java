package com.pr.f2w;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class VoteResult extends AppCompatActivity {
    String[] name ={"Prashant","Arpit","Deepak","Aditya","Abhishek","Smita","Gaurav","Shubham","Poorva","Amisha","Queen","Arihant"};
    TextView[] tvn=new TextView[3];
    TextView[] tvv=new TextView[3];
    ImageView[] iv = new ImageView[3];
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

    DatabaseReference mDatabase;
    String position,vote;
    HashMap<String,Integer> pos=new HashMap<>();
    Integer p;
    ImageView cd;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vote_result);
        pd = new ProgressDialog(this);
        pd.setMessage("Loading....");
        pd.show();
        iv[0] = (ImageView)findViewById(R.id.first_img);
        tvn[0]=(TextView)findViewById(R.id.first_name);
        tvv[0]=(TextView)findViewById(R.id.first_vote);


        iv[1] = (ImageView)findViewById(R.id.second_img);
        tvn[1]=(TextView)findViewById(R.id.second_name);
        tvv[1]=(TextView)findViewById(R.id.second_vote);

        iv[2] = (ImageView)findViewById(R.id.third_img);
        tvn[2]=(TextView)findViewById(R.id.third_name);
        tvv[2]=(TextView)findViewById(R.id.third_vote);
        mDatabase= FirebaseDatabase.getInstance().getReference();
        mDatabase.child("seniors").orderByValue().limitToLast(3).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
            p =ds.getValue(Integer.class);
            vote=ds.getKey();
            pos.put(vote,p);
                    p =ds.getValue(Integer.class);


                }
                int j=0;
                Set<String> keys = pos.keySet();  //get all keys
                for(String i: keys) {
                    Integer a= pos.get(i);
                    //iv[j].setImageDrawable(getDrawable(covers[i]));
                    tvn[j].setText(name[Integer.parseInt(i)]);
                    tvv[j].setText("Votes : "+a);
                    //Toast.makeText(VoteResult.this,a.toString(),Toast.LENGTH_LONG).show();
                    j++;
                    pd.dismiss();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }






}

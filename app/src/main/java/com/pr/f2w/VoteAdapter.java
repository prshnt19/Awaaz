package com.pr.f2w;

import android.content.Context;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.client.core.Constants;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;
import java.util.Map;

/**
 * Created by Arpit on 09-Jan-18.
 */

public class VoteAdapter extends RecyclerView.Adapter<VoteAdapter.MyViewHolder> {

    private DatabaseReference mDatabase;
    private Context mContext;
    private List<Card> cardList;
    private voting_listener listener;

    public class User{
        public String email;
        public String voted;

        public User(){

        }

        public User(String email,String voted){
            this.email = email;
            this.voted = voted;
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView name,count;
        public ImageView thumbnail, overflow;
        public LinearLayout linearLayout;

        public MyViewHolder(View view){
            super(view);
            name=(TextView)view.findViewById(R.id.name);
            //count = (TextView) view.findViewById(R.id.count);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            //overflow = (ImageView) view.findViewById(R.id.overflow);
            linearLayout =  (LinearLayout) view.findViewById(R.id.card);
        }

    }

    public VoteAdapter(Context mContext, List<Card> cardList, voting_listener listner) {
        this.mContext = mContext;
        this.cardList = cardList;
        this.listener = listner;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Card card = cardList.get(position);
        holder.name.setText(card.getName());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.oncallme(position);
            }
        });
        Glide.with(mContext).load(card.getThumbnail()).into(holder.thumbnail);
//        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
         holder.thumbnail.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //Singleton id = Singleton.getInstance();
                //id.pos = position;
                // PURANA CODE YAHA THA
                //User u1 = new User("p@g.c","0");
                //Vote.newVote(position);
                //mDatabase= FirebaseDatabase.getInstance().getReference("login");
            //    DatabaseReference mRef =mDatabase.getReference(Constants.);
                //mDatabase.child("login").child("66").setValue("hi");

                //mref.child("login").child("67").child("voted").setValue("1");
                //mDatabase.setValue("all");
                //User user =
                listener.oncallme(position);


                //String s = mDatabase.child("login").child("67").child("voted");
                //mDatabase.child("login").child("67").child("voted").setValue("1");
                    }

        });

    }

public interface voting_listener{
        void oncallme(int position);

}
    @Override
    public int getItemCount() {
        return cardList.size();
    }
}

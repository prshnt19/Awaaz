package com.pr.f2w;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;

/**
 * Created by Arpit on 12-Jan-18.
 */

public class QuesAdapter extends RecyclerView.Adapter<QuesAdapter.QuesViewHolder> {

    private List<Ques> quesList;
    private Context context;

    private static int currentPosition = -1;

    public QuesAdapter(List<Ques> quesList,Context context){
        this.quesList = quesList;
        this.context = context;
    }

    @Override
    public QuesAdapter.QuesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v =LayoutInflater.from(parent.getContext()).inflate(R.layout.ques,parent,false);
        return new QuesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final QuesAdapter.QuesViewHolder holder, final int position) {
        Ques ques = quesList.get(position);
        holder.ques.setText(ques.getQues());
        holder.ans.setText(ques.getAns());
        holder.linearLayout.setVisibility(View.GONE);

        if (currentPosition == position) {
            //creating an animation
            Animation slideDown = AnimationUtils.loadAnimation(context, R.anim.anim);

            //toggling visibility
            holder.linearLayout.setVisibility(View.VISIBLE);

            //adding sliding effect
            holder.linearLayout.startAnimation(slideDown);
        }

        holder.ques.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getting the position of the item to expand it
                if(currentPosition==position)
                    currentPosition = -1;

                else
                    currentPosition = position;

                //reloading the list
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return quesList.size();
    }

    class QuesViewHolder extends RecyclerView.ViewHolder {
        TextView ques, ans;
        LinearLayout linearLayout;

        QuesViewHolder(View itemView) {
            super(itemView);

            ques = (TextView) itemView.findViewById(R.id.ques);
            ans = (TextView) itemView.findViewById(R.id.ans);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);
        }
    }
}

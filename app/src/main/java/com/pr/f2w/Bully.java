
package com.pr.f2w;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arpit on 09-Jan-18.
 */

public class Bully extends Fragment {

    RecyclerView recyclerView;
    QuesAdapter adapter;
    List<Ques> quesList;
    String[] questions ={"What are some tips for dealing with bullies?",
            "What do you do when you catch another kid bullying your kid?",
            "What's the best way to prevent bullying?",
            "How can You stop people from bullying You?",
            "How can bullying be stopped in schools?"};

    String[] ans = {"•\tCarry yourself with more confidence\n" +
            "•\tDon't look like an easy target\n" +
            "•\tBe more assertive\n" +
            "•\tTell the bully to 'Knock it off!\" and say it like you mean it\n" +
            "•\tCommunicate with your parents  and teachers and solicit their help.\n" +
            "•\tEncourage your peers to step in and stand up for you\n" +
            "•\tProtect yourself if/when you are in danger  (You can learn these skills.)",

            "Before you do anything, ask your child. If they don’t want you to make it stop, respect their decision. They might resent you if you try and butt in when they don’t want you to.\n" +
                    "If you determine that your child is being bullied, and that they’re being abused and want it to stop, take it first to the bully’s parents. Talk it out. The younger the children are the more likely talking to the parents will change the bully’s behavior.\n" +
                    "If talking does nothing, take it to the school. The school will most likely step in and give the bully a talking to.\n" +
                    "If all else fails egg their house.\n",

            "Having an assertive attitude can be the first step towards preventing bullying. Bullying is usually a show of power where the bully likes to prove that he or she is stronger than the victim. They usually pick on weak kids, children with low self esteem and low confidence levels. However, if you develop an assertive attitude, it can go a long way in keeping you safe. Having a good network of friends can also help, as bullies usually target those who do not have a support group to fall back to.",

            "Report it to teachers\n" +
                    "Tell your parents\n" +
                    "Make an official complaint to the school\n" +
                    "Speak to a school counselor\n"+
                    "This app is for all... You too",

            "1) Help kids who are being bullied right now by teaching them how to get the bullying to stop\n" +
                    "2) Encourage and promote upstander behavior\n" +
                    "3) Teach our youngest children how to stop bullying by their peers so kids don't learn how to bully in the first place\n" +
                    "4) Identify and provide assistance to individuals who are in crisis or need mental health care\n" +
                    "5) Create protocols and best practices for administrators and teachers so that their actions help extinguish bullying instead of exacerbating it."};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void loadQues(){
        for (int i=0;i <5;i++){
            Ques ques =new Ques(questions[i],ans[i]);
            quesList.add(ques);
        }
        adapter = new QuesAdapter(quesList,getContext());
        recyclerView.setAdapter(adapter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.bully,container,false);
        recyclerView = (RecyclerView) root.findViewById(R.id.recycler_view_bully);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        quesList = new ArrayList<>();
        loadQues();
        return root ;
    }
}

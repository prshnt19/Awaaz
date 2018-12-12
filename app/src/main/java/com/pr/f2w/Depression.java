
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

public class Depression extends Fragment {
    RecyclerView recyclerView;
    QuesAdapter adapter;
    List<Ques> quesList;
    String[] questions ={"How do I avoid getting depressed?",
            "How does one overcome depression and anxiety?",
            "What are the signs/symptoms of depression?",
            "What are some of the most effective ways to fight depression?",
            "Do children get depression?",
            "Can a lack of sleep cause depression?"
    };
    String[] ans = {"1. Exercise\n" +
            "2. Eat well and hydrate \n" +
            "3. Sleep well\n" +
            "4. Meditate \n" +
            "They're not complicated ideas, just difficult to implement.\n",

            "•\tTalk to one person about your feelings.\n" +
            "•\tHelp someone else by volunteering.\n" +
            "•\tHave lunch or coffee with a friend.\n" +
            "•\tAsk a loved one to check in with you regularly.\n" +
            "•\tAccompany someone to the movies, a concert, or a small get-together.\n" +
            "•\tCall or email an old friend.\n" +
            "•\tGo for a walk with a workout buddy.\n" +
            "•\tSchedule a weekly dinner date.\n" +
            "•\tMeet new people by taking a class or joining a club.\n" +
            "•\tConfide in a counselor, therapist, or clergy member.\n",

            "•\tcontinuous low mood or sadness\n" +
                    "•\tfeelings of hopelessness and helplessness\n" +
                    "•\tlow self-esteem\n" +
                    "•\ttearfulness\n" +
                    "•\tfeelings of guilt\n" +
                    "•\tfeeling irritable and intolerant of others\n" +
                    "•\tlack of motivation and little interest in things\n" +
                    "•\tdifficulty making decisions\n" +
                    "•\tlack of enjoyment\n" +
                    "•\tsuicidal thoughts or thoughts of harming yourself\n" +
                    "•\tfeeling anxious or worried\n" +
                    "•\treduced sex drive\n",

            "Depression can be sometime difficult to deal with. Here are some tips you may try to deal with it.\n" +
                    "Set new goals:\n" +
                    "Set daily goals for yourself. Try concentrating on it. Start with small achievable goals. Reward yourself for each accomplishment. As you feel better, add more challenging goals everyday.\n" +
                    "Exercise:\n" +
                    "Exercise can relieve depression, by altering the mood-regulating brain chemicals norepinephrine and serotonin. A 10-minute walk can improve your mood for two hours.\n" +
                    "Spend time with people who support you:\n" +
                    "Spend time talking and listening to trusted people and share your problems. Ask for the help you need. They can make you feel better. Joining a support group is a good idea. You can share your experiences, encourage each other and can come out of the feeling of isolation.\n" +
                    "Do things that help you feel good.:\n" +
                    "Practice things that can relax and energize you. Get an average of 8 hours of sleep. Expose yourself to sunlight. Take a short walk outdoors, have your coffee outside or enjoy a meal at a restaurant. Try yoga, meditation, deep breathing etc.\n" +
                    "If you find it very difficult to handle,  consult a doctor.",

            "Yes. Children are subject to the same factors that cause depression in adults. These include: A change in physical health, life events, heredity, or inheritance, environment, and chemical disturbance in the brain. It is estimated that 2.5% of children in the U.S. suffer from depression. In adolescents, it is estimated to be 4% to 8%.\n" +
                    "\n" +
                    "Depression in children is different from the \"normal\" blues and everyday emotions that are typical in children of various ages. Children who are depressed experience changes in their behavior that are persistent and disruptive to their normal lifestyle, usually interfering with relationships with friends, schoolwork, special interests, and family life. It may also occur at the same time as (or be hidden by) attention deficit hyperactivity disorder (ADHD), obsessive-compulsive disorder (OCD), or conduct disorder (CD).",

            "No. Lack of sleep alone cannot cause depression, but it does play a role. Lack of sleep resulting from another medical illness or the presence of personal problems can intensify depression. Chronic inability to sleep is also an important clue that someone may be depressed.\n" +
                    "\n" +
                    "Common triggers of depression include:\n" +
                    "\n" +
                    "Family history of depression.\n" +
                    "An abnormal reaction over the loss of a loved one through death, divorce, or separation.\n" +
                    "Interpersonal disputes.\n" +
                    "Physical, sexual, or emotional abuse.\n" +
                    "Major life events such as moving, graduating or retiring, etc."

    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void loadQues(){
        for (int i=0;i <6;i++){
            Ques ques =new Ques(questions[i],ans[i]);
            quesList.add(ques);
        }
        adapter = new QuesAdapter(quesList,getContext());
        recyclerView.setAdapter(adapter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.depression,container,false);
        recyclerView = (RecyclerView) root.findViewById(R.id.recycler_view_depression);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        quesList = new ArrayList<>();
        loadQues();
        return root ;
    }
}
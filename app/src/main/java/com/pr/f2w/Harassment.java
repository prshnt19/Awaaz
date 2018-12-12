
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

public class Harassment extends Fragment {
    RecyclerView recyclerView;
    QuesAdapter adapter;
    List<Ques> quesList;
    String[] questions ={"When does harassment violate federal law?",
            "When is an employer legally responsible for harassment by a supervisor?",
            "What should employers do to prevent and correct harassment?",
            "What should an anti-harassment policy state?",
            "What are important elements of a complaint procedure?",
            "How should an employer investigate a harassment complaint?"
    };

    String[] ans = {"Sexual harassment is a type of discrimination based on sex. Harassment violates federal law if it involves discriminatory treatment based on race, color, sex (with or without sexual conduct), religion, national origin, age, disability, or because the employee opposed discrimination or participated in an investigation or complaint proceeding.\n" +
            "\n" +
            "Federal law does not prohibit simple teasing, offhand comments, or isolated incidents that are not extremely serious. The conduct must be sufficiently frequent or severe to create a hostile work environment or result in a tangible employment action, such as hiring, firing, or demotion.\n" +
            "\n" +
            "Verbal or other non-physical conduct must normally create a pattern to be unlawful. However, a single incident can be unlawful if sufficiently severe, including unwanted physical contact by a coworker, or a supervisor’s threat of adverse action as part of a demand for sexual favors.",

            "An employer is always responsible for harassment by a supervisor that resulted in a tangible employment action. If the harassment did not lead to a tangible employment action, the employer is liable unless it proves that: 1) it exercised reasonable care to prevent and promptly correct any harassment; and 2) the employee unreasonably failed to complain to management or to avoid harm otherwise.\n" +
                    "\n" +
                    "An individual qualifies as a supervisor if the individual has the authority to recommend tangible employment decisions affecting the employee or if has the authority to direct the employee’s daily work activities.\n" +
                    "\n" +
                    "A tangible employment action means a significant change in employment status. Examples include hiring, firing, demotion, undesirable reassignment, a decision causing a significant change in benefits, and compensation decisions.",

            "Employers should establish, distribute to all employees, and enforce a policy prohibiting harassment and setting out a procedure for making complaints. In most cases, the policy and procedure should be in writing. If the business conducts a prompt, thorough, and impartial investigation of any complaint that arises and undertakes swift and appropriate corrective action, it will have fulfilled its responsibility to effectively prevent and correct harassment.\n" +
                    "\n" +
                    "View training programs that help employees and supervisors learn how to deal with sexual harassment in the workplace.",

            "An anti-harassment policy should make clear that the employer will not tolerate harassment based on race, sex, religion, national origin, age, or disability, or harassment based on opposition to discrimination on participation in complaint proceedings. The policy should also state that the employer will not tolerate retaliation against anyone who complains of harassment or who participates in an investigation.",

            "The employer should encourage employees to report harassment before it becomes severe or pervasive.\n" +
                    "The employer should designate more than one individual to take complaints (not limited to the immediate supervisor) and should ensure that these individuals are in accessible locations. The employer also should instruct all of its supervisors to report complaints of harassment to appropriate officials.\n" +
                    "The employer should assure employees that it will protect the confidentiality of harassment complaints to the extent possible.\n" +
                    "Employers should not require that a complaint be given in a specific format (or in writing) before agreeing to respond. Any report of alleged harassment, including verbal complaints, should be addressed.",
            "An employer should conduct a prompt, thorough, and impartial investigation. The alleged harasser should not have any direct or indirect control over the investigation.\n" +
                    "\n" +
                    "The investigator should interview the employee who complained of harassment, the alleged harasser, and others who could reasonably be expected to have relevant information.\n" +
                    "\n" +
                    "Before completing the investigation, the employer should take steps to make sure that harassment does not continue. If the parties have to be separated, the separation should not burden the employee who complained of harassment. An involuntary transfer of the complainant could constitute unlawful retaliation. Other examples of interim measures are making scheduling changes to avoid contact between the parties or placing the alleged harasser on non-disciplinary leave with pay pending the conclusion of the investigation."
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
        View root = inflater.inflate(R.layout.harassment,container,false);
        recyclerView = (RecyclerView) root.findViewById(R.id.recycler_view_harassment);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        quesList = new ArrayList<>();
        loadQues();
        return root ;
    }
}


package com.pr.f2w;

/**
 * Created by Arpit on 12-Jan-18.
 */

public class Ques {
    private String ques,ans;

    public Ques(String ques, String ans){
        this.ques = ques;

        this.ans = ans;
    }

    public String getQues() {
        return ques;
    }

    public void setQues(String ques) {
        this.ques = ques;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

}

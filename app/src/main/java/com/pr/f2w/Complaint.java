package com.pr.f2w;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.firebase.client.Firebase;

public class Complaint extends AppCompatActivity {

    EditText comp;
    EditText nam;
    Button submit;
    Firebase mref;
        ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);
        pd = new ProgressDialog(this);
        pd.setMessage("Loading....");
        comp = (EditText)findViewById(R.id.complaint);
        nam = (EditText)findViewById(R.id.name);
        submit = (Button)findViewById(R.id.submit);
        Firebase.setAndroidContext(this);
        mref=new Firebase("https://login-6bb26.firebaseio.com/complaint");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pd.show();
                String complaint = comp.getText().toString();
                String name = nam.getText().toString();
                if(!name.isEmpty())
                    complaint=name + " - " +complaint;
                else
                    complaint="Anonymous -"+ complaint;
                mref.push().setValue(complaint);
                pd.dismiss();
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(Complaint.this);
                alertDialog.setTitle("Notification").setMessage("Your complaint has been registered").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Complaint.this,HomeActivity.class);
                        startActivity(intent);
                    }

            }).show();
            }
        });
    }
}

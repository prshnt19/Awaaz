package com.pr.f2w;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.firebase.client.Firebase;

public class Register extends AppCompatActivity {
    Firebase mref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

    }

    public void register(View view){AlertDialog.Builder alertDialog = new AlertDialog.Builder(Register.this);
        Firebase.setAndroidContext(this);
        mref=new Firebase("https://login-6bb26.firebaseio.com/complaint");

        alertDialog.setTitle("Done here!!").setMessage("If everything goes right..\nYou will be registered within 24 hours").setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(Register.this,MainActivity.class);
                startActivity(intent);
            }
        }).show();
    }
}

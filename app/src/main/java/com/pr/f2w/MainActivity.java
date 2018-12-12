package com.pr.f2w;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
FirebaseAuth mauth;
Button login;
EditText password;
EditText email;
FirebaseAuth.AuthStateListener mauthlistner;
Intent intent;
ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pd = new ProgressDialog(this);
        pd.setMessage("Signing in....");
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        login=(Button)findViewById(R.id.login);
        password=(EditText)findViewById(R.id.password);
        email=(EditText)findViewById(R.id.email);
        mauth = FirebaseAuth.getInstance();
//final String pass= password.getText().toString();
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                setlogin();
                pd.show();
            }
        });
mauthlistner =new FirebaseAuth.AuthStateListener() {


    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
         if(firebaseAuth.getCurrentUser()!=null)
         {intent=new Intent(MainActivity.this,HomeActivity.class);
         startActivity(intent);

        // Intent i = new Intent(MainActivity.this,VoteAdapter.class).putExtra(pass,pass);
         }
    }
};

    }
    //public String pass = password.getText().toString();
    public void setlogin(){
        String semail = email.getText().toString();
        String spassword =password.getText().toString();
        Intent intent = new Intent(this, VoteAdapter.class);
        final f2w globalVariable = (f2w) getApplicationContext();
       // globalVariable.getSomeVariable(spassword);
     //   intent.putExtra("key",spassword);
        mauth.signInWithEmailAndPassword(semail, spassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser user = mauth.getCurrentUser();
                            pd.dismiss();

                        } else {
                            // If sign in fails, display a message to the user.
pd.dismiss();
                            Toast.makeText(MainActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mauth.addAuthStateListener((mauthlistner));
    }

    public void guest(View view){
        Intent intent=new Intent(MainActivity.this,HomeActivity2.class);
        startActivity(intent);
    }

    public void register(View view){
        Intent intent = new Intent(MainActivity.this,Register.class);
        startActivity(intent);
    }
}

package com.demoapp.kirangopakumar.linkedindemo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {


    private EditText password,email, fname, lname;
    //private AutoCompleteTextView email;
    private ProgressBar mProgress;

    private FirebaseAuth auth;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseRefence;



    public static final String TAG = "SignUpActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        //Getting firebase authentication object
        auth = FirebaseAuth.getInstance();

        //Geting firebase database object
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseRefence = mFirebaseDatabase.getReference().child("user");



        fname = (EditText)findViewById(R.id.first_name);
        lname = (EditText)findViewById(R.id.last_name);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);

        //mProgress = (ProgressBar)findViewById(R.id.login_progress);



    }


    public void updateUI(FirebaseUser user) {

        Intent intent = new Intent(SignUpActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();

    }



    @Override
    protected void onResume() {
        super.onResume();
        //mProgress.setVisibility(View.GONE);
    }

    public void join_button(View view) {

        // show click effect on button pressed
        final AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.2F);
        view.startAnimation(buttonClick);

        final String FirstName = fname.getText().toString();
        final String LastName = lname.getText().toString();
        final String Email = email.getText().toString();
        final String Password = password.getText().toString();

        if (TextUtils.isEmpty(FirstName)) {
            Toast.makeText(SignUpActivity.this, "Enter First Name", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(LastName)) {
            Toast.makeText(SignUpActivity.this, "Enter Last Name ", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(Email)) {
            Toast.makeText(SignUpActivity.this, "Enter Email Address", Toast.LENGTH_SHORT).show();
            return;
        }


        if (TextUtils.isEmpty(Password)) {
            Toast.makeText(SignUpActivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
            return;
        }


        if (Password.length() <= 8 ) {
            Toast.makeText(SignUpActivity.this, "Password too short, enter minimum eight charcater", Toast.LENGTH_SHORT).show();
            return;
        }


        //Create/Register User(email/password)
        auth.createUserWithEmailAndPassword(Email, Password)
                .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");

                            FirebaseUser user = auth.getCurrentUser();

                            String UID =  user.getUid();

                            //Add adtional data
                            RegistrationModel registrationModel = new RegistrationModel(FirstName,LastName,Email,Password);

                            mDatabaseRefence.child(UID).setValue(registrationModel);

                            //mDatabaseRefence.push().setValue(registrationModel);
                            fname.setText("");
                            lname.setText("");
                            email.setText("");
                            password.setText("");
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUpActivity.this, "Registration failed.",
                                    Toast.LENGTH_SHORT).show();

                        }


                    }
                });
    }
}


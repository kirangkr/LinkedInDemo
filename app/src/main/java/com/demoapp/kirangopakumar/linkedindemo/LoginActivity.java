package com.demoapp.kirangopakumar.linkedindemo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {


    private TextView join_now, forgot_password;
    private EditText login_email, login_password, reset_email;
    private Button sign_in, reset_button;

    public static final String TAG = "LoginActivity";

    private FirebaseAuth mAuth;

    private LinearLayout forgot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        join_now = (TextView) findViewById(R.id.join_now);
        forgot_password = (TextView) findViewById(R.id.forgot_password);
        login_email = (EditText) findViewById(R.id.login_email);
        login_password = (EditText) findViewById(R.id.login_password);
        sign_in = (Button) findViewById(R.id.sign_in);

        forgot = (LinearLayout) findViewById(R.id.forgot);


        mAuth = FirebaseAuth.getInstance();

    }

    public void signin_button(View view) {

        final AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.2F);
        view.startAnimation(buttonClick);

        final String LoginEmail = login_email.getText().toString();
        final String LoginPasswoard = login_password.getText().toString();

        mAuth.signInWithEmailAndPassword(LoginEmail, LoginPasswoard)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    public void register(View view) {

        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
        finish();

    }


    public void forgotPassword(View view) {

        reset_button = (Button) findViewById(R.id.reset_button);
        reset_email = (EditText) findViewById(R.id.reset_email);

        join_now.setVisibility(View.GONE);
        forgot_password.setVisibility(View.GONE);
        login_email.setVisibility(View.GONE);
        login_password.setVisibility(View.GONE);
        sign_in.setVisibility(View.GONE);

        forgot.setVisibility(View.VISIBLE);


    }


    public void resetPass(View view) {
        String email = reset_email.getText().toString();

        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "Email sent.");
                            reset_email.setText("");

                            join_now.setVisibility(View.VISIBLE);
                            forgot_password.setVisibility(View.VISIBLE);
                            login_email.setVisibility(View.VISIBLE);
                            login_password.setVisibility(View.VISIBLE);
                            sign_in.setVisibility(View.VISIBLE);

                            forgot.setVisibility(View.GONE);

                        }
                    }
                });


    }

}
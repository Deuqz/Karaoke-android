package com.example.karaoke_android;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;

import database.DataBase;
import database.ReadyDatabase;
import database.User;

public class SingUpActivity extends AppCompatActivity {

    private DataBase database;
    private static final String LOG_TAG = "SingUpActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);
        database = new ReadyDatabase();
    }

    @SuppressLint("SetTextI18n")
    public void okPushed(View view) {
        String firstName = ((EditText) findViewById(R.id.firstName)).getText().toString();
        String secondName = ((EditText) findViewById(R.id.secondName)).getText().toString();
        String email = ((EditText) findViewById(R.id.email)).getText().toString();
        String password = ((EditText) findViewById(R.id.password)).getText().toString();
        TextView textView = findViewById(R.id.errorMessage);
        if (firstName.isEmpty() || secondName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            textView.setText("Please, fill all lines");
            textView.setVisibility(View.VISIBLE);
            return;
        }
        if (database.containsUser(email)) {
            textView.setText("This user already exists");
            textView.setVisibility(View.VISIBLE);
            return;
        }
        textView.setVisibility(View.INVISIBLE);
        User newUser = new User(firstName, secondName, email, password);
        try {
            database.add(newUser);
            FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Log.d(LOG_TAG, "createUserWithEmail:success");
                    } else {
                        Log.w(LOG_TAG, "createUserWithEmail:failure", task.getException());
                        Toast.makeText(SingUpActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SingUpActivity.this, StartActivity.class);
                        startActivity(intent);
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(this, com.example.karaoke_android.MainActivity.class);
        intent.putExtra("User", (Parcelable) newUser);
        startActivity(intent);
    }
}

package com.example.deepikasto_doapp;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    DatabaseHelper helper;
    EditText mail,pass;
    Button signup,login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        helper = new DatabaseHelper(this);

        mail = findViewById(R.id.eemail);
        pass = findViewById(R.id.ppassword);
        signup = findViewById(R.id.button5);
        login = findViewById(R.id.button4);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {

                    String Email = mail.getText().toString();
                    String Password = pass.getText().toString();

                    User currentUser = helper.Authenticate(new User(null, null, Email, Password));

                    if (currentUser != null) {
                        Toast.makeText(getApplicationContext(), "Successfully Logged in!", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(Login.this,ListMenu.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Failed to log in , please try again", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });



        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(Login.this,Signup.class);
                startActivity(intent);
            }
        });
    }

    public boolean validate() {
        boolean valid = false;
        String Email = mail.getText().toString();
        String Password = pass.getText().toString();

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            valid = false;
            Toast.makeText(getApplicationContext(), "Successfully Logged in!", Toast.LENGTH_LONG).show();
        } else {
            valid = true;
        }

        if (Password.isEmpty()) {
            valid = false;
        }

        return valid;
    }



}
package com.example.deepikasto_doapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Handler;

public class Signup extends AppCompatActivity {
    String Email,Password,Name;
    EditText name, email,pass1;
    Button create;
    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        name = findViewById(R.id.name);
        email = findViewById(R.id.eemail);
        pass1 = findViewById(R.id.ppassword);
        create = findViewById(R.id.button);

        helper = new DatabaseHelper(this);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Email=email.getText().toString();
                Password=pass1.getText().toString();
                Name=name.getText().toString();

                if (!helper.isEmailExists(Email)) {
                    helper.addUser(new User(null, Name, Email, Password));
                    Toast.makeText(getApplicationContext(), "User created successfully! Please Login ", Toast.LENGTH_LONG).show();
                    Intent intent =  new Intent(Signup.this,Login.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(), "User already exists with same email ", Toast.LENGTH_LONG).show();
                }


            }
        });

    }
}
package com.example.firstapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registration_Activity extends AppCompatActivity {
EditText username;
EditText email;
EditText password;

EditText confirmpassword;

Button submitButton;

    private FirebaseAuth mAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registration);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        username=findViewById(R.id.username);
        email=findViewById(R.id.email);
        password=findViewById(R.id.passwd);
        confirmpassword=findViewById(R.id.repasswd);

        submitButton=findViewById(R.id.signupbtn);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Username = username.getText().toString().trim();
                String Email = email.getText().toString().trim();
                String Password = password.getText().toString().trim();
                String Confirmpassword = confirmpassword.getText().toString().trim();
                if (Username.isEmpty() || Email.isEmpty() || Password.isEmpty() || Confirmpassword.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Fields can't be empty", Toast.LENGTH_SHORT).show();
                } else if (!Password.equals(Confirmpassword)) {
                    Toast.makeText(getApplicationContext(), "Password doesn't match", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(), "Registering", Toast.LENGTH_SHORT).show();
                    mAuth = FirebaseAuth.getInstance();
                    mAuth.createUserWithEmailAndPassword(Email, Password)
                            .addOnCompleteListener(Registration_Activity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Toast.makeText(Registration_Activity.this, "", Toast.LENGTH_SHORT).show();

                                    if (!task.isSuccessful()) {
                                        Toast.makeText(Registration_Activity.this, "Issue while registering", Toast.LENGTH_SHORT).show();

                                    } else {
                                        Toast.makeText(Registration_Activity.this, "Registration successfull", Toast.LENGTH_SHORT).show();
                                        Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                                        startActivity(intent1);
                                        Registration_Activity.this.finish();
                                    }
                                }


                            });
                }
            }
        });
    }
}
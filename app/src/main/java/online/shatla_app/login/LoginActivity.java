package online.shatla_app.login;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import online.shatla_app.R;

public class LoginActivity extends AppCompatActivity {
    private EditText name, pass;
    private Button login, rigistration;
    private String dname, dpass;
    private FirebaseAuth mAuth;



    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_login);
        name = findViewById(R.id.userId);
        pass = findViewById(R.id.password);
        login = findViewById(R.id.login);
        rigistration = findViewById(R.id.already_register);


        rigistration.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dname = name.getText().toString().trim();
                dpass = pass.getText().toString().trim();
                if (TextUtils.isEmpty(dname)) {
                    name.setError("PLZ provide your mail id");

                    return;
                }

                if (TextUtils.isEmpty(dpass)) {
                    Toast.makeText(LoginActivity.this, "password fild is empaty o  ", Toast.LENGTH_SHORT).show();
                    pass.setError("plz write Valid password ");
                    return;

                }

                mAuth.signInWithEmailAndPassword(dname, dpass).
                        addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){

                                    Toast.makeText(LoginActivity.this, "login Successful", Toast.LENGTH_SHORT).show();
                                   // Intent intent1=new Intent(login.this, NavigationMainActivity.class);
                                    //startActivity(intent1);
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        });


        rigistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }
}

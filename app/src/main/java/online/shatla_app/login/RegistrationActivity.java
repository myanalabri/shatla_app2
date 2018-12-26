package online.shatla_app.login;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.regex.Pattern;

import online.shatla_app.R;
import online.shatla_app.profile.ProductItem;

public class RegistrationActivity extends AppCompatActivity {
    private EditText name,phone,pass,addres,location,email;
    private Button alresdy_rigester,register;
    private String mPhone,mPass,mName,mAddres,mLocation,mEmail;
    private FirebaseAuth mAuth;
private SellerInfor sellerInfor;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;


private FirebaseUser current_id;
private String cuid;

    public static final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(

            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        mAuth=FirebaseAuth.getInstance();
        setContentView(R.layout.activity_registration);

        alresdy_rigester=findViewById(R.id.already_register);

        register=findViewById(R.id.sing_up);

        phone=findViewById(R.id.phone);
        pass=findViewById(R.id.password);
        addres=findViewById(R.id.addres);
        location=findViewById(R.id.location);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("userifor");




        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPass = pass.getText().toString().trim();
                mEmail = email.getText().toString().trim();

                if (TextUtils.isEmpty(mEmail)) {
                    email.setError("Please Enter The Email");

                }


                if (!isVALIDeMAIL(mEmail)) {
                    email.setError("Please provid a valid mail id");
                    Toast.makeText(getApplicationContext(), "InValid Email Address.", Toast.LENGTH_SHORT).show();

                }
                if (TextUtils.isEmpty(mPass) || mPass.length() < 6) {
                    Toast.makeText(RegistrationActivity.this, "password fild is empaty or  ."
                            + "it shoud have six or more characters",Toast.LENGTH_SHORT).show();
                    pass.setError("plz write Valid password it shoud have six or more characters");
                    return;

                }



                mAuth.createUserWithEmailAndPassword(mEmail, mPass).
                        addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){


                                           crest();
                                    Toast.makeText(RegistrationActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();


                                    Intent intent1=new Intent(RegistrationActivity.this, LoginActivity.class);
                                    startActivity(intent1);
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



        alresdy_rigester.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    private boolean isVALIDeMAIL(String email){


        return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
    }
    //-----------------------------------------------------






public void crest(){
current_id= FirebaseAuth.getInstance().getCurrentUser();
cuid=current_id.getUid();

    sellerInfor.setName(.getText().toString());
    sellerInfor.setPhone(phone.getText().toString());
    sellerInfor.setAddres(addres.getText().toString());
    sellerInfor.setLocation(location.getText().toString());
   // String key=databaseReference.push().getKey();
    databaseReference.child(cuid).setValue(sellerInfor);


}
}

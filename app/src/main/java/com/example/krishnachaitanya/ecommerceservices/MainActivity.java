package com.example.krishnachaitanya.ecommerceservices;

import android.app.ProgressDialog;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonRegister;
    private EditText editTextEmail1,editTextName1,editTextPhone,editTextPassword1;
    private TextView textViewSignin;

    private ProgressDialog progressDialog;
    private DatabaseReference databaseReferenceUsers;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth=FirebaseAuth.getInstance();
        databaseReferenceUsers= FirebaseDatabase.getInstance().getReference("Users");

        if(firebaseAuth.getCurrentUser()!=null){
            //profile activity
            finish();
            startActivity(new Intent(getApplicationContext(),ProfileActivity.class));

        }

        progressDialog = new ProgressDialog(this);

        buttonRegister=(Button) findViewById(R.id.buttonRegister);

        editTextName1= (EditText) findViewById(R.id.editTextName1);
        editTextPhone= (EditText) findViewById(R.id.editTextPhone);
        editTextEmail1= (EditText) findViewById(R.id.editTextEmail1);
        editTextPassword1= (EditText) findViewById(R.id.editTextPassword1);

        textViewSignin= (TextView) findViewById(R.id.textViewSignin);

        buttonRegister.setOnClickListener(this);
        textViewSignin.setOnClickListener(this);
    }

    private void registerUser(){
        final String email=editTextEmail1.getText().toString().trim();
        String password=editTextPassword1.getText().toString().trim();
        final String name=editTextName1.getText().toString().trim();
        final String phone=editTextPhone.getText().toString().trim();

        if(TextUtils.isEmpty(name)){
            //email is empty
            Toast.makeText(this, "Please enter Name", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(email)){
            //password is empty
            Toast.makeText(this, "Please enter Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            //email is empty
            Toast.makeText(this, "Please enter Password", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(phone)){
            //password is empty
            Toast.makeText(this, "Please enter Phone number", Toast.LENGTH_SHORT).show();
            return;
        }
        //if validations are ok
        //progress bar

        progressDialog.setMessage("Registering user...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //user successfully reg
                            //will start profile activity
                            String id=databaseReferenceUsers.push().getKey();

                            User user=new User(name,email,phone,id);

                            databaseReferenceUsers.child(id).setValue(user);

                            finish();
                            startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
                            Toast.makeText(MainActivity.this,"Registered successfully",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this,"could not register, pls try again",Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }
                });


    }

    @Override
    public void onClick(View v) {

        if(v==buttonRegister){
            registerUser();
        }
        if(v==textViewSignin){
            //will open login activity
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}

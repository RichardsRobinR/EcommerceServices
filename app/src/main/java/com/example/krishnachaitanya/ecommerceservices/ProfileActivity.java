package com.example.krishnachaitanya.ecommerceservices;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth firebaseAuth;
    private TextView textViewProfilename;
    private Button buttonLogout1,buttonProfile,buttonProduct,buttonReview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth=FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser()==null)
        {
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();

        textViewProfilename=(TextView) findViewById(R.id.textViewProfilename);
        buttonProduct=(Button) findViewById(R.id.buttonProducts);
        buttonProfile=(Button) findViewById(R.id.buttonProfile);
        buttonReview=(Button) findViewById(R.id.buttonReview);
        buttonLogout1=(Button) findViewById(R.id.buttonLogout1);

        buttonProduct.setOnClickListener(this);
        buttonProfile.setOnClickListener(this);
        buttonReview.setOnClickListener(this);
        buttonLogout1.setOnClickListener(this);


        textViewProfilename.setText("Welcome "+ user.getDisplayName());
    }


    @Override
    public void onClick(View v) {
        if(v==buttonProduct){
            finish();
            startActivity(new Intent(this,ProductActivity.class));
        }
        if(v==buttonProfile){
            finish();
            startActivity(new Intent(this,ViewProfileActivity.class));
        }
        if(v==buttonReview){
            finish();
            startActivity(new Intent(this,ReviewActivity.class));
        }
        if(v==buttonLogout1){
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this,LoginActivity.class));

        }
    }
}

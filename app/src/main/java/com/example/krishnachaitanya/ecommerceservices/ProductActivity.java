package com.example.krishnachaitanya.ecommerceservices;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProductActivity extends AppCompatActivity implements View.OnClickListener{

        private EditText editTextProductName;
        private Spinner spinnerWeb;
        private Spinner spinnerProd;
        private Button buttonSubmitProduct;
        private Button buttonBack;

        private FirebaseAuth firebaseAuth;

        private DatabaseReference databaseReferenceProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        firebaseAuth=FirebaseAuth.getInstance();
        databaseReferenceProducts= FirebaseDatabase.getInstance().getReference("Products");


        //spinnerProd=(Spinner) findViewById(R.id.spinnerProd);
        //spinnerWeb=(Spinner) findViewById(R.id.spinnerWeb);
        //editTextProductName=(EditText) findViewById(R.id.editTextProductName);
        //buttonSubmitProduct=(Button) findViewById(R.id.buttonSubitProduct);
        //buttonBack=(Button) findViewById(R.id.buttonBack);

        //buttonSubmitProduct.setOnClickListener(this);
        //buttonBack.setOnClickListener(this);


        String[] Ecom = new String[]{"Amazon", "Flipkart", "Snapdeal", "Myntra"};


        AutoCompleteTextView editText = findViewById(R.id.autocompleteView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, Ecom);
        editText.setAdapter(adapter);

        String[] PROD = new String[] {"Smartphone", "Laptop" ,"Apparel", "Others" };
    }
    public void submitProduct()
    {
        final String prodname=editTextProductName.getText().toString().trim();
        final String web=spinnerWeb.getSelectedItem().toString();
        final String prodtype=spinnerProd.getSelectedItem().toString();

        if(!TextUtils.isEmpty(prodname)) {

            String id = databaseReferenceProducts.push().getKey();

            Product product = new Product(id, prodname, prodtype, web);

            databaseReferenceProducts.child(id).setValue(product);

            Toast.makeText(ProductActivity.this, "info", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(ProductActivity.this, "info no", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onClick(View v) {
        if(v==buttonSubmitProduct){
            submitProduct();
        }
        if(v==buttonBack){
            //will open login activity
            finish();
            startActivity(new Intent(this, ProfileActivity.class));
        }
    }




    }

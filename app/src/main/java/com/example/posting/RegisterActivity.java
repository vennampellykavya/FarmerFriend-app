package com.example.posting;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseAuth ;
import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    private EditText InputName, InputPhoneNumber, InputPassword,ConfirmPassword, InputEmail,InputAddress;
    private ProgressDialog loadingBar;
    private FirebaseAuth mAuth;
    String emailpattern="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private DatabaseReference RootRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
        mAuth=FirebaseAuth.getInstance();

        RootRef = FirebaseDatabase.getInstance().getReference();

        Button createAccountButton = (Button) findViewById(R.id.register_btn);
        InputName = (EditText) findViewById(R.id.register_username_input);
        InputPassword = (EditText) findViewById(R.id.register_password_input);
        InputPhoneNumber = (EditText) findViewById(R.id.register_phone_number_input);
        InputAddress=(EditText) findViewById(R.id.adress);
        ConfirmPassword=(EditText) findViewById(R.id.Cf_Password2);
        InputEmail = (EditText) findViewById(R.id.register_email);
        loadingBar = new ProgressDialog(this);


        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateAccount();
            }
        });


    }

    private void CreateAccount() {
        String name = InputName.getText().toString();
        String phone = InputPhoneNumber.getText().toString();
        String email = InputEmail.getText().toString();
        String password = InputPassword.getText().toString();
        String address= InputAddress.getText().toString();
        String cpassword=ConfirmPassword.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String phonePattern = "^[+]?[0-9]{10,13}$";

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Please write your name...", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please write your email...", Toast.LENGTH_SHORT).show();
        } else if (!email.matches(emailPattern)) {
            Toast.makeText(this, "Please enter a valid email...", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "Please write your phone number...", Toast.LENGTH_SHORT).show();
        } else if (!phone.matches(phonePattern)) {
            Toast.makeText(this, "Please enter a valid phone number...", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(address)) {
            Toast.makeText(this, "Please write your address...", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please write your password...", Toast.LENGTH_SHORT).show();
        } else if(!cpassword.equals(password)) {
            ConfirmPassword.setError("password not matched");
        }
        else {
            loadingBar.setTitle("Create Account");
            loadingBar.setMessage("Please wait, while we are checking the credentials.");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            //ValidatephoneNumber(name, phone, email, password);

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            // User successfully registered
                            String currentUserID = mAuth.getCurrentUser().getUid();
                            HashMap<String, Object> userdataMap = new HashMap<>();
                            userdataMap.put("phone", phone);
                            userdataMap.put("name", name);
                            userdataMap.put("email", email);
                            userdataMap.put("address",address);
                            userdataMap.put("password",password);

                            // Store user details in Firebase Realtime Database
                            RootRef.child("Users").child(currentUserID).setValue(userdataMap)
                                    .addOnCompleteListener(task1 -> {
                                        if (task1.isSuccessful()) {
                                            Toast.makeText(RegisterActivity.this, "Congratulations, your account has been created.", Toast.LENGTH_SHORT).show();
                                            loadingBar.dismiss();

                                            Intent intent = new Intent(RegisterActivity.this, Login.class);
                                            startActivity(intent);
                                            finish();
                                        } else {
                                            loadingBar.dismiss();
                                            Toast.makeText(RegisterActivity.this, "Failed to save user information.", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        } else {
                            loadingBar.dismiss();
                            Toast.makeText(RegisterActivity.this, "Registration failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}




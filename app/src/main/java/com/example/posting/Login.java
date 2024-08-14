/*package com.example.posting;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.posting.Model.Users;
import com.example.posting.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.AlgorithmParameterGenerator;
import java.security.InvalidAlgorithmParameterException;
import java.security.spec.AlgorithmParameterSpec;

import io.paperdb.Paper;

public class Login extends AppCompatActivity {
    private EditText InputPhoneNumber, InputPassword;
    private Button LoginButton;
    private ProgressDialog loadingBar;
    private String parentDbName = "Users";
    private CheckBox chkBoxRememberMe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        LoginButton = (Button) findViewById(R.id.login_btn);
        InputPassword = (EditText) findViewById(R.id.login_password_input);
        InputPhoneNumber = (EditText) findViewById(R.id.login_phone_number_input);

        loadingBar = new ProgressDialog(this);

        chkBoxRememberMe = (CheckBox) findViewById(R.id.remember_me_chkb);
        Paper.init(this);

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginUser();
            }

            private void LoginUser() throws InterruptedException {
                String phone = InputPhoneNumber.getText().toString();
                String password = InputPassword.getText().toString();

                if (TextUtils.isEmpty(phone))
                    Toast.makeText(Login.this, "Please write your phone number...", Toast.LENGTH_SHORT).show();
                else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Login.this, "Please write your password...", Toast.LENGTH_SHORT).show();
                } else {
                    loadingBar.setTitle("Login Account");
                    loadingBar.setMessage("Please wait, while we are checking the credentials.");
                    loadingBar.setCanceledOnTouchOutside(false);
                    loadingBar.show();


                    AllowAccessToAccount(phone, password);
                }
            }

            private void AllowAccessToAccount(final String phone, final String password) throws InterruptedException {
                if (chkBoxRememberMe.isChecked()) {
                    Object Paper = null;
                    Paper.book().write(Prevalent.UserPhoneKey, phone);
                    Paper.book().write(Prevalent.UserPasswordKey, password);
                }


                final DatabaseReference RootRef;
                RootRef = FirebaseDatabase.getInstance().getReference();


                RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(parentDbName).child(phone).exists()) {
                            Users usersData = dataSnapshot.child(parentDbName).child(phone).getValue(Users.class);

                            if (usersData.getPhone().equals(phone)) {
                                if (usersData.getPassword().equals(password)) {

                                    if (parentDbName.equals("Users")) {
                                        Toast.makeText(Login.this, "logged in Successfully...", Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();

                                        Intent intent = new Intent(Login.this, HomeActivity.class);
                                        Prevalent.currentOnlineUser = usersData;
                                        startActivity(intent);
                                    }
                                } else {
                                    loadingBar.dismiss();
                                    Toast.makeText(Login.this, "Password is incorrect.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        } else {
                            Toast.makeText(Login.this, "Account with this " + phone + " number do not exists.", Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
} */
package com.example.posting;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.posting.Model.Users;
import com.example.posting.Prevalent.Prevalent;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rey.material.widget.CheckBox;

import io.paperdb.Paper;

public class Login extends AppCompatActivity {
    private EditText InputPhoneNumber, InputPassword;
    private Button LoginButton;
    private ProgressDialog loadingBar;
    private String parentDbName = "Users";
    private CheckBox chkBoxRememberMe;
    private FirebaseAuth mAuth;
    private DatabaseReference usersRef;
    private FirebaseDatabase RootRef;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        FirebaseApp.initializeApp(this);


        LoginButton = findViewById(R.id.login_btn);
        InputPassword = findViewById(R.id.login_password_input);
        InputPhoneNumber = findViewById(R.id.login_phone_number_input);

        loadingBar = new ProgressDialog(this);
       // chkBoxRememberMe = findViewById(R.id.remember_me_chkb);

        Paper.init(this);

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginUser();
            }
        });
    }

    private void LoginUser() {
        String phone = InputPhoneNumber.getText().toString();
        String password = InputPassword.getText().toString();

        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(Login.this, "Please write your phone number...", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(Login.this, "Please write your password...", Toast.LENGTH_SHORT).show();
        } else {
            loadingBar.setTitle("Login Account");
            loadingBar.setMessage("Please wait, while we are checking the credentials.");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();


            AllowAccessToAccount(phone, password);
        }
    }

    private void AllowAccessToAccount(final String phone, final String password) {
        final DatabaseReference RootRef = FirebaseDatabase.getInstance().getReference().child("Users");

        // Get the current user's UID from Firebase Authentication
        String currentUserID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        RootRef.child(currentUserID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String storedPhone = dataSnapshot.child("phone").getValue(String.class);
                    String storedPassword = dataSnapshot.child("password").getValue(String.class);

                    if (storedPhone.equals(phone)) {
                        if (storedPassword.equals(password)) {
                            Toast.makeText(Login.this, "Logged in Successfully...", Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();

                            // Assuming you have a Users class for storing user data
                            Users usersData = dataSnapshot.getValue(Users.class);

                            Intent intent = new Intent(Login.this, Main2.class);
                            Prevalent.currentOnlineUser = usersData;
                            startActivity(intent);
                            finish();
                        } else {
                            loadingBar.dismiss();
                            Toast.makeText(Login.this, "Incorrect password.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        loadingBar.dismiss();
                        Toast.makeText(Login.this, "Phone number does not match.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    loadingBar.dismiss();
                    Toast.makeText(Login.this, "Account with this UID does not exist in the database.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                loadingBar.dismiss();
                Toast.makeText(Login.this, "Database error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}


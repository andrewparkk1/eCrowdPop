package com.example.ecrowdpop;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.example.ecrowdpop.Model.User;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    private Spinner spinner;

    private EditText username;
    private EditText category;
    private EditText email;
    private EditText password;
    private Button register;
    private TextView txt_login;

    private FirebaseAuth auth;
    private DatabaseReference reference;
    private ProgressDialog pd;

    private TextView usernameStatus;
//    private Button usernameCheck;

    private static int usernameFlag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.username);
        category = findViewById(R.id.category);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);
        txt_login = findViewById(R.id.txt_login);

        usernameStatus = findViewById(R.id.availability_username);
//        usernameCheck = findViewById(R.id.check_username);

        auth = FirebaseAuth.getInstance();

        txt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this , LoginActivity.class));
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd = new ProgressDialog(RegisterActivity.this);
                pd.setMessage("Please wait...");
                pd.show();

                String str_username = username.getText().toString();
                String str_category = category.getText().toString();
                String str_email = email.getText().toString();
                String str_password = password.getText().toString();

                if (TextUtils.isEmpty(str_username) || TextUtils.isEmpty(str_category) ||
                    TextUtils.isEmpty(str_email) || TextUtils.isEmpty(str_password)){
                    Toast.makeText(RegisterActivity.this, "Empty credential$!", Toast.LENGTH_SHORT).show();
                } else if (str_password.length() < 6){
                    Toast.makeText(RegisterActivity.this, "Password too short!", Toast.LENGTH_SHORT).show();
                } else {
                    register(str_username , str_category , str_email , str_password);
                }
            }
        });

//        usernameCheck.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                checkUsernameAvailability();
//            }
//        });

        spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.widgets, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        int i = 0;


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapter.getItem(i).toString();


                if (item.equals("Marketing/Advertisement")) {
                    category.setText("Marketing/Advertisement");
                }
                if (item.equals("Retail/ECommerce")) {
                    category.setText("Retail/ECommerce");
                }
                if (item.equals("Science")) {
                    category.setText("Science");
                }
                if (item.equals("Consumer Technology")) {
                    category.setText("Consumer Technology");
                }
                if (item.equals("Games")) {
                    category.setText("Games");
                }

                if (item.equals("Healthcare")) {
                    category.setText("Healthcare");
                }
                if (item.equals("Media")) {
                    category.setText("Media");
                }
                if (item.equals("Art/Style")) {
                    category.setText("Art/Style");
                }
                if (item.equals("Manufacturing/Industry")) {
                    category.setText("Manufacturing/Industry");
                }

                if (item.equals("Social Impact")) {
                    category.setText("Social Impact");
                }
                if (item.equals("Social Media")) {
                    category.setText("Social Media");
                }
                if (item.equals("Education")) {
                    category.setText("Education");
                }

                if (item.equals("Energy")) {
                    category.setText("Energy");
                }
                if (item.equals("Education")) {
                    category.setText("Education");
                }

                if (item.equals("Food/Drink")) {
                    category.setText("Food/Drink");
                }

                if (item.equals("Finance")) {
                    category.setText("Finance");
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void checkUsernameAvailability() {

        final String txt_username = username.getText().toString();

        usernameStatus.setVisibility(View.VISIBLE);
        DatabaseReference mUsersRed = FirebaseDatabase.getInstance().getReference().child("Users");
        mUsersRed.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    User user = snapshot.getValue(User.class);
                    if (txt_username.equals(user.getUsername())){
                        usernameFlag = 1;
                        usernameStatus.setText("Unavailable!");
                        Toast.makeText(RegisterActivity.this, "Username alredy taken! Try something else.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                usernameStatus.setText("Available!");
                usernameFlag = 0;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void register(final String username, final String category , String email , String password) {
        if (usernameFlag == 0) {
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        FirebaseUser firebaseUser = auth.getCurrentUser();
                        String userid = firebaseUser.getUid();

                        reference = FirebaseDatabase.getInstance().getReference().child("Users").child(userid);

                        HashMap<String, Object> hashMap = new HashMap<>();
                        hashMap.put("id", userid);
//                        hashMap.put("username", username.toLowerCase());
                        hashMap.put("username", username);
                        hashMap.put("category", category);
                        hashMap.put("bio", "");
                        hashMap.put("imageurl", "default");

                        reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    pd.dismiss();
                                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                }
                            }
                        });

                    } else {
                        pd.dismiss();
                        Toast.makeText(RegisterActivity.this, "You can't register with this email and password!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            pd.dismiss();
            Toast.makeText(this, "Please change the username!", Toast.LENGTH_SHORT).show();
        }
    }

}

package com.example.prm_group_project.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.DnsResolver;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prm_group_project.Model.LoginRequest;
import com.example.prm_group_project.Model.LoginResponse;
import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.prm_group_project.R;

public class LoginActivity extends AppCompatActivity {
    EditText userName, userPassword;
    Button login;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        userName = findViewById(R.id.txtUsername);
        userPassword = findViewById(R.id.txtPassword);
        login = findViewById(R.id.btnLogin);
        register = findViewById(R.id.btnSignup);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
                finish();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(userName.getText().toString()) || TextUtils.isEmpty(userPassword.getText().toString())) {
                    Toast.makeText(LoginActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    login();
                }
            }
        });
    }

    public void login() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUserName(userName.getText().toString().trim());
        loginRequest.setUserPassword(userPassword.getText().toString().trim());

        String usernameInput = userName.getText().toString().trim();
        String passwordInput = userPassword.getText().toString().trim();
        Log.d("TAGTAG", "Username: " + usernameInput);
        Log.d("TAGTAG", "Password: " + passwordInput);
    }

    private void saveAccessToken(String accessToken) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("access_token", accessToken);
        editor.apply();
    }

    private void saveUserId(String userId) {
        SharedPreferences sharedPreferences = getSharedPreferences("uid", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("user", userId);
        editor.apply();
    }
}
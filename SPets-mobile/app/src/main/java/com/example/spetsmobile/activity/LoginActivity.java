package com.example.spetsmobile.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.example.spetsmobile.R;
import com.example.spetsmobile.itf.AuthInterface;
import com.example.spetsmobile.model.request.AccountResquest;
import com.example.spetsmobile.model.response.ApiReponse;
import com.example.spetsmobile.model.response.AuthResponse;
import com.example.spetsmobile.restapi.AuthAPI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.spetsmobile.restapi.TokenAPI;
import com.example.spetsmobile.util.ConstantUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.messaging.FirebaseMessaging;

public class LoginActivity extends AppCompatActivity implements AuthInterface {

    private EditText edtUsername;
    private EditText edtPassword;

    private TextInputLayout textInputUsername;
    private TextInputLayout textInputPassword;

    private TextView tvError;

    private Button btnSubmit;

    private ProgressBar progressBar;
    private boolean isLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);

        textInputUsername = findViewById(R.id.textInputUsername);
        textInputPassword = findViewById(R.id.textInputPassword);

        tvError = findViewById(R.id.tvError);

        progressBar = findViewById(R.id.progressBar);

        // Set sự kiện cho nút Submit
        btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kiểm tra xem có đang chạy API không để tránh việc bấm nút nhiều lần
                if (!isLoading) {
                    // Hiển thị ProgressBar khi bắt đầu thực hiện công việc
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setVisibility(View.VISIBLE);

                            // Đặt isLoading thành true
                            isLoading = true;

                            // Vô hiệu hóa nút Submit khi bắt đầu thực hiện công việc
                            btnSubmit.setEnabled(false);
                        }
                    });

                    String username = edtUsername.getText().toString();
                    String password = edtPassword.getText().toString();

                    // Kiểm tra dữ liệu
                    if (validator(username, password)) {
                        // Thực hiện công việc gửi dữ liệu API
                        AuthAPI authAPI = new AuthAPI(LoginActivity.this);
                        authAPI.login(username, password);
                    }
                }
            }
        });

        // forgot password
        TextView btnForgotPass = findViewById(R.id.btnForgotPass);
        btnForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(getApplicationContext(), ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });

        // register
        TextView btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onSuccess(String type, ApiReponse apiReponse) {
        // Ẩn ProgressBar khi công việc hoàn tất
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.INVISIBLE);

                // Đặt isLoading thành false
                isLoading = false;

                // Đặt lại enabled của nút Submit thành true
                btnSubmit.setEnabled(true);
            }
        });

        // Thiết lập token
        AuthResponse authResponse = (AuthResponse) apiReponse.getPayload();
        ConstantUtil.setAccessToken(authResponse.getAccessToken());
        ConstantUtil.setRefreshToken(authResponse.getRefreshToken());
        ConstantUtil.setAuth(authResponse);

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();

                        // Log and toast
                        Log.d("LOG_TOKEN", token);

                        Toast.makeText(LoginActivity.this, "TOKEN: " + token, Toast.LENGTH_LONG).show();

                        AccountResquest accountResquest = new AccountResquest();
                        accountResquest.setUsername(ConstantUtil.getAuth().getUsername());
                        accountResquest.setToken(token);

                        TokenAPI tokenAPI = new TokenAPI();
                        tokenAPI.updateToken(accountResquest);
                    }
                });

        // Đăng nhập thành công chuyển sang MainActivity
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onError(String type, String error) {
        // Ẩn ProgressBar khi công việc hoàn tất
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.INVISIBLE);

                // Đặt isLoading thành false
                isLoading = false;

                // Đặt lại enabled của nút Submit thành true
                btnSubmit.setEnabled(true);
            }
        });

        // hiển thị thông báo lỗi
        tvError.setVisibility(View.VISIBLE);
        tvError.setTextColor(Color.RED);
        tvError.setText("Tên đăng nhập hoặc mật khẩu không đúng!");
    }

    private boolean validator(String username, String password) {
        int countError = 0;

        // kiểm tra tên đăng nhập
        if (username.length() == 0) {
            countError++;
            textInputUsername.setError("Vui lòng nhập tên đăng nhập!");
        } else {
            textInputUsername.setError(null);
        }

        // kiểm tra mật khẩu
        if (password.length() == 0) {
            countError++;
            textInputPassword.setError("Vui lòng nhập mật khẩu!");
        } else {
            textInputPassword.setError(null);
        }

        return countError == 0;
    }

}
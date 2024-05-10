package com.example.spetsmobile.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.example.spetsmobile.R;
import com.example.spetsmobile.itf.AuthInterface;
import com.example.spetsmobile.model.response.ApiReponse;
import com.example.spetsmobile.model.response.AuthResponse;
import com.example.spetsmobile.restapi.AuthAPI;
import com.example.spetsmobile.util.ConstantUtil;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends AppCompatActivity implements AuthInterface {

    private EditText edtFullname;
    private TextInputLayout textInputFullname;

    private EditText edtUsername;
    private TextInputLayout textInputUsername;

    private EditText edtPassword;
    private TextInputLayout textInputPassword;

    private TextView tvError;

    private Button btnSubmit;

    private ProgressBar progressBar;
    private boolean isLoading = false;
    private boolean isHospital = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        edtFullname = findViewById(R.id.edtFullname);
        textInputFullname = findViewById(R.id.textInputFullname);

        edtUsername = findViewById(R.id.edtUsername);
        textInputUsername = findViewById(R.id.textInputUsername);

        edtPassword = findViewById(R.id.edtPassword);
        textInputPassword = findViewById(R.id.textInputPassword);

        tvError = findViewById(R.id.tvError);

        progressBar = findViewById(R.id.progressBar);

        CheckBox checkBox = findViewById(R.id.checkBox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Xử lý khi trạng thái của CheckBox thay đổi
                isHospital = isChecked;
            }
        });

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

                    String fullname = edtFullname.getText().toString();
                    String username = edtUsername.getText().toString();
                    String password = edtPassword.getText().toString();

                    // Kiểm tra dữ liệu
                    if (validator(fullname, username, password)) {
                        // Thực hiện công việc gửi dữ liệu API
                        AuthAPI authAPI = new AuthAPI(RegisterActivity.this);
                        authAPI.register(fullname, username, password, isHospital ? "HOSPITAL" : "USER");
                    }
                }
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

        String fullname = edtFullname.getText().toString();
        ConstantUtil.setMSG("Xin chào, " + fullname.toUpperCase() + ". Chúc mừng bạn đã đăng ký tài khoản thành công! Vui lòng đăng nhập hệ thống");

        // Đăng nhập thành công chuyển sang MsgActivity
        Intent intent = new Intent(getApplicationContext(), MsgActivity.class);
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
        tvError.setText("Thông tin tài khoản không đúng!");
    }

    private boolean validator(String fullname, String username, String password) {
        int countError = 0;

        // kiểm tra họ tên
        if (fullname.length() == 0) {
            countError++;
            textInputFullname.setError("Vui lòng nhập họ tên!");
        } else {
            textInputFullname.setError(null);
        }

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
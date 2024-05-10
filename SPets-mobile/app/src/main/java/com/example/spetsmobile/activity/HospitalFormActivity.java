package com.example.spetsmobile.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;

import com.example.spetsmobile.R;
import com.example.spetsmobile.itf.HospitalInterface;
import com.example.spetsmobile.model.request.HospitalRequest;
import com.example.spetsmobile.model.request.PetRequest;
import com.example.spetsmobile.model.response.ApiReponse;
import com.example.spetsmobile.model.response.AuthResponse;
import com.example.spetsmobile.model.response.HospitalResponse;
import com.example.spetsmobile.model.response.PetResponse;
import com.example.spetsmobile.restapi.HospitalAPI;
import com.example.spetsmobile.restapi.PetAPI;
import com.example.spetsmobile.util.ConstantUtil;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Map;

public class HospitalFormActivity extends AppCompatActivity implements HospitalInterface {

    private ImageView imgAvatar;

    private TextInputLayout textInputName;
    private EditText edtName;

    private TextInputLayout textInputPhone;
    private EditText edtPhone;

    private TextInputLayout textInputEmail;
    private EditText edtEmail;

    private TextInputLayout textInputAddress;
    private EditText edtAddress;

    private Button btnSubmit;

    private HospitalResponse hospitalResponse = null;

    private AuthResponse authResponse = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_hospital_form);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Thiết lập nút "Back" ở tiêu đề
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imgAvatar = findViewById(R.id.imgAvatar);

        edtName = findViewById(R.id.edtName);
        textInputName = findViewById(R.id.textInputName);

        edtPhone = findViewById(R.id.edtPhone);
        textInputPhone = findViewById(R.id.textInputPhone);

        edtEmail = findViewById(R.id.edtEmail);
        textInputEmail = findViewById(R.id.textInputEmail);

        edtAddress = findViewById(R.id.edtAddress);
        textInputAddress = findViewById(R.id.textInputAddress);

        btnSubmit = findViewById(R.id.btnSubmit);

        hospitalResponse = ConstantUtil.getHospitalResponse();
        if (hospitalResponse == null) {
            hospitalResponse = new HospitalResponse();
        }

        authResponse = ConstantUtil.getAuth();
        if (authResponse == null) {
            authResponse = new AuthResponse();
        }

        edtName.setText(hospitalResponse.getName());
        edtPhone.setText(hospitalResponse.getPhone());
        edtEmail.setText(hospitalResponse.getEmail());
        edtAddress.setText(hospitalResponse.getAddress());

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                String email = edtEmail.getText().toString();
                String phone = edtPhone.getText().toString();
                String address = edtAddress.getText().toString();

                if (validator(name, email, phone, address)) {
                    HospitalRequest hospitalRequest = new HospitalRequest();
                    hospitalRequest.setId(hospitalResponse.getId());
                    hospitalRequest.setAccountId(authResponse.getId());
                    hospitalRequest.setName(edtName.getText().toString());
                    hospitalRequest.setEmail(edtEmail.getText().toString());
                    hospitalRequest.setPhone(edtPhone.getText().toString());
                    hospitalRequest.setAddress(edtAddress.getText().toString());
                    hospitalRequest.setStatus(true);

                    HospitalAPI hospitalAPI = new HospitalAPI(HospitalFormActivity.this);
                    hospitalAPI.saveHospital(hospitalRequest);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_hospital, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            // xử lý sự kiện khi nút "Back" được nhấn
            finish(); // đóng hoạt động và quay về hoạt động trước đó
            return true;
        }

        if (id == R.id.action_logout) {
            Intent intent = new Intent(HospitalFormActivity.this, LoginActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSuccess(String type, ApiReponse apiReponse) {
        if (apiReponse != null && type.equalsIgnoreCase("HOSPITAL_SAVE")) {
            // update data
            HospitalResponse hospitalResponse = (HospitalResponse) apiReponse.getPayload();
            ConstantUtil.setHospitalResponse(hospitalResponse);

            finish();
        }
    }

    @Override
    public void onError(String type, Map<String, String> error) {

    }

    private boolean validator(String name, String email, String phone, String address) {
        int countError = 0;

        // kiểm tra Tên phòng khám
        if (name.length() == 0) {
            countError++;
            textInputName.setError("Vui lòng nhập Tên phòng khám!");
        } else {
            textInputName.setError(null);
        }

        // kiểm tra Địa chỉ Email
        if (email.length() == 0) {
            countError++;
            textInputEmail.setError("Vui lòng nhập Địa chỉ Email!");
        } else {
            textInputEmail.setError(null);
        }

        // kiểm tra Số điện thoại
        if (phone.length() == 0) {
            countError++;
            textInputPhone.setError("Vui lòng nhập Số điện thoại!");
        } else {
            textInputPhone.setError(null);
        }

        // kiểm tra Địa chỉ
        if (address.length() == 0) {
            countError++;
            textInputAddress.setError("Vui lòng nhập Địa chỉ!");
        } else {
            textInputAddress.setError(null);
        }

        return countError == 0;
    }
}
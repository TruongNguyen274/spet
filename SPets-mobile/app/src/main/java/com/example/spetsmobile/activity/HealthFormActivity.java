package com.example.spetsmobile.activity;

import android.content.Intent;
import android.os.Bundle;

import com.example.spetsmobile.api.APIClient;
import com.example.spetsmobile.itf.HealthRecordInterface;
import com.example.spetsmobile.model.request.HealthRecordResquest;
import com.example.spetsmobile.model.response.ApiReponse;

import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.widget.Toolbar;

import com.example.spetsmobile.R;
import com.example.spetsmobile.model.response.PetResponse;
import com.example.spetsmobile.restapi.HealthRecordAPI;
import com.example.spetsmobile.util.ConstantUtil;
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;

import java.util.Map;

public class HealthFormActivity extends AppCompatActivity implements HealthRecordInterface {

    private ImageView imgAvatar;

    private TextInputLayout textInputWeight;
    private EditText edtWeight;

    private TextInputLayout textInputHeight;
    private EditText edtHeight;

    private TextInputLayout textInputHealth;
    private EditText edtHealth;

    private Button btnSubmit;

    private PetResponse response = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_healthy_form);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Thiết lập nút "Back" ở tiêu đề
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        response = ConstantUtil.getPetResponse();
        if (response == null) {
            response = new PetResponse();
        }

        imgAvatar = findViewById(R.id.imgAvatar);
        String imageURL = APIClient.HOST_URL + "/resources/file" + response.getAvatar();
        Picasso.get().load(imageURL).placeholder(R.drawable.icon_avatar).into(imgAvatar);

        edtHeight = findViewById(R.id.edtHeight);
        textInputHeight = findViewById(R.id.textInputHeight);

        edtWeight = findViewById(R.id.edtWeight);
        textInputWeight = findViewById(R.id.textInputWeight);

        edtHealth = findViewById(R.id.edtHealth);
        textInputHealth = findViewById(R.id.textInputHealth);


        btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String height = edtHeight.getText().toString();
                String weight = edtWeight.getText().toString();
                String health = edtHealth.getText().toString();

                // Kiểm tra dữ liệu
                if (validator(height, weight, health)) {
                    HealthRecordResquest healthRecordResquest = new HealthRecordResquest();
                    healthRecordResquest.setId(0);
                    healthRecordResquest.setPetId(response.getId());
                    healthRecordResquest.setHealth(health);
                    healthRecordResquest.setWeight(Double.parseDouble(weight));
                    healthRecordResquest.setHeight(Double.parseDouble(height));

                    HealthRecordAPI healthRecordAPI = new HealthRecordAPI(HealthFormActivity.this);
                    healthRecordAPI.saveHealthRecord(healthRecordResquest);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_custom, menu);
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
            Intent intent = new Intent(HealthFormActivity.this, LoginActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSuccess(String type, ApiReponse apiReponse) {
        if (apiReponse != null) {
            Intent intent = new Intent(HealthFormActivity.this, HealthActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onError(String type, Map<String, String> error) {

    }

    private boolean validator(String height, String weight, String health) {
        int countError = 0;

        // kiểm tra chiều cao
        if (height.length() == 0) {
            countError++;
            textInputHeight.setError("Vui lòng nhập chiều cao!");
        } else {
            textInputHeight.setError(null);
        }

        // kiểm tra cân nặng
        if (weight.length() == 0) {
            countError++;
            textInputWeight.setError("Vui lòng nhập cân nặng!");
        } else {
            textInputWeight.setError(null);
        }

        // kiểm tra sức khoẻ
        if (health.length() == 0) {
            countError++;
            textInputHealth.setError("Vui lòng nhập sức khoẻ!");
        } else {
            textInputHealth.setError(null);
        }

        return countError == 0;
    }

}
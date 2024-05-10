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
import com.example.spetsmobile.itf.BookingInterface;
import com.example.spetsmobile.model.response.ApiReponse;
import com.example.spetsmobile.model.response.BookingResponse;
import com.example.spetsmobile.util.ConstantUtil;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Map;

public class HospitalCalendarFormActivity extends AppCompatActivity {

    private ImageView imgAvatar;

    private TextInputLayout textInputTitle;
    private EditText edtTitle;

    private TextInputLayout textInputAccount;
    private EditText edtAccount;

    private TextInputLayout textInputPet;
    private EditText edtPet;

    private TextInputLayout textInputStartTime;
    private EditText edtStartTime;

    private TextInputLayout textInputEndTime;
    private EditText edtEndTime;

    private TextInputLayout textInputDate;
    private EditText edtDate;

    private TextInputLayout textInputDescription;
    private EditText edtDescription;

    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_hospital_calendar_form);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Thiết lập nút "Back" ở tiêu đề
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imgAvatar = findViewById(R.id.imgAvatar);

        edtTitle = findViewById(R.id.edtTitle);
        textInputTitle = findViewById(R.id.textInputTitle);

        edtAccount = findViewById(R.id.edtAccount);
        textInputAccount = findViewById(R.id.textInputAccount);

        edtPet = findViewById(R.id.edtPet);
        textInputPet = findViewById(R.id.textInputPet);

        edtStartTime = findViewById(R.id.edtStartTime);
        textInputStartTime = findViewById(R.id.textInputStartTime);

        edtEndTime = findViewById(R.id.edtEndTime);
        textInputEndTime = findViewById(R.id.textInputEndTime);

        edtDate = findViewById(R.id.edtDate);
        textInputDate = findViewById(R.id.textInputDate);

        edtDescription = findViewById(R.id.edtDescription);
        textInputDescription = findViewById(R.id.textInputDescription);

        btnSubmit = findViewById(R.id.btnSubmit);

        BookingResponse bookingResponse = ConstantUtil.getBookingResponse();
        if (bookingResponse == null) {
            bookingResponse = new BookingResponse();
        }

        edtTitle.setText(bookingResponse.getTitle());
        edtAccount.setText(bookingResponse.getAccountResponse().getName());
        edtPet.setText("1");
        edtStartTime.setText(bookingResponse.getStartTime());
        edtEndTime.setText(bookingResponse.getEndTime());
        edtDate.setText(bookingResponse.getDate());
        edtDescription.setText(bookingResponse.getDescription());

        if (bookingResponse.getStatus().equals("APPROVED")) {
            btnSubmit.setVisibility(View.VISIBLE);
            btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HospitalCalendarFormActivity.this, HospitalMedicalRecordActivity.class);
                    intent.putExtra("petId", edtPet.getText().toString());
                    startActivity(intent);
                }
            });
        } else {
            btnSubmit.setVisibility(View.INVISIBLE);
        }
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
            Intent intent = new Intent(HospitalCalendarFormActivity.this, LoginActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

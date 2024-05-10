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
import com.example.spetsmobile.itf.MedicalRecordInterface;
import com.example.spetsmobile.model.request.BookingResquest;
import com.example.spetsmobile.model.request.MedicalRecordRequest;
import com.example.spetsmobile.model.response.ApiReponse;
import com.example.spetsmobile.model.response.BookingResponse;
import com.example.spetsmobile.model.response.HospitalResponse;
import com.example.spetsmobile.model.response.MedicalRecordResponse;
import com.example.spetsmobile.restapi.BookingAPI;
import com.example.spetsmobile.restapi.MedicalRecordAPI;
import com.example.spetsmobile.util.ConstantUtil;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

public class HospitalMedicalRecordActivity extends AppCompatActivity implements MedicalRecordInterface, BookingInterface {

    private ImageView imgAvatar;

    private TextInputLayout textInputPet;
    private EditText edtPet;

    private TextInputLayout textInputHospital;
    private EditText edtHospital;

    private TextInputLayout textInputAppointmentDate;
    private EditText edtAppointmentDate;

    private TextInputLayout textInputDiagnosis;
    private EditText edtDiagnosis;

    private TextInputLayout textInputPrescription;
    private EditText edtPrescription;

    private TextInputLayout textInputNotes;
    private EditText edtNotes;

    private Button btnSubmit;

    private HospitalResponse hospitalResponse = null;

    private BookingResponse bookingResponse = null;

    private final Calendar myCalendar= Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_hospital_medical_record);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Thiết lập nút "Back" ở tiêu đề
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imgAvatar = findViewById(R.id.imgAvatar);

        edtPet = findViewById(R.id.edtPet);
        textInputPet = findViewById(R.id.textInputPet);

        edtHospital = findViewById(R.id.edtHospital);
        textInputHospital = findViewById(R.id.textInputHospital);

        edtAppointmentDate = findViewById(R.id.edtAppointmentDate);
        textInputAppointmentDate = findViewById(R.id.textInputAppointmentDate);

        edtDiagnosis = findViewById(R.id.edtDiagnosis);
        textInputDiagnosis = findViewById(R.id.textInputDiagnosis);

        edtPrescription = findViewById(R.id.edtPrescription);
        textInputPrescription = findViewById(R.id.textInputPrescription);

        edtNotes = findViewById(R.id.edtNotes);
        textInputNotes = findViewById(R.id.textInputNotes);

        btnSubmit = findViewById(R.id.btnSubmit);

        Intent intent = getIntent();
        String petId = intent.getStringExtra("petId");

        hospitalResponse = ConstantUtil.getHospitalResponse();
        if (hospitalResponse == null) {
            hospitalResponse = new HospitalResponse();
        }

        bookingResponse = ConstantUtil.getBookingResponse();
        if (bookingResponse == null) {
            bookingResponse = new BookingResponse();
        }

        edtPet.setText(petId);
        edtHospital.setText(hospitalResponse.getName());
        edtAppointmentDate.setText(updateLabel());

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String diagnosis = edtDiagnosis.getText().toString();
                if (validator(diagnosis)) {
                    MedicalRecordRequest medicalRecordRequest = new MedicalRecordRequest();
                    medicalRecordRequest.setPetId(Long.parseLong(edtPet.getText().toString()));
                    medicalRecordRequest.setHospitalId(hospitalResponse.getId());
                    medicalRecordRequest.setDiagnosis(edtDiagnosis.getText().toString());
                    medicalRecordRequest.setAppointmentDate(edtAppointmentDate.getText().toString());
                    medicalRecordRequest.setPrescription(edtPrescription.getText().toString());
                    medicalRecordRequest.setNotes(edtNotes.getText().toString());

                    MedicalRecordAPI medicalRecordAPI = new MedicalRecordAPI(HospitalMedicalRecordActivity.this);
                    medicalRecordAPI.saveMedicalRecord(medicalRecordRequest);

                    BookingAPI bookingAPI = new BookingAPI(HospitalMedicalRecordActivity.this);
                    BookingResquest bookingResquest = new BookingResquest();
                    bookingResquest.setId(bookingResponse.getId());
                    bookingResquest.setStatus("COMPLETED");

                    bookingAPI.bookingStatus(bookingResquest);
                }
            }
        });
    }

    private String updateLabel(){
        String myFormat="yyyy-MM-dd hh:mm:ss";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
        return dateFormat.format(myCalendar.getTime());
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
            Intent intent = new Intent(HospitalMedicalRecordActivity.this, LoginActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSuccess(String type, ApiReponse apiReponse) {
        if (apiReponse != null && type.equalsIgnoreCase("MEDICAL_RECORD_SAVE")) {
            // update data
            MedicalRecordResponse medicalRecordResponse = (MedicalRecordResponse) apiReponse.getPayload();
            ConstantUtil.setMedicalRecordResponse(medicalRecordResponse);

            Intent intent = new Intent(HospitalMedicalRecordActivity.this, HospitalActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onError(String type, Map<String, String> error) {

    }

    private boolean validator(String diagnosis) {
        int countError = 0;
        if (diagnosis.length() == 0) {
            countError++;
            textInputDiagnosis.setError("Vui lòng nhập chẩn đoán!");
        } else {
            textInputDiagnosis.setError(null);
        }

        return countError == 0;
    }
}
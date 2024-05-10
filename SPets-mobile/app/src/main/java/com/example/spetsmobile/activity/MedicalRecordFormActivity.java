package com.example.spetsmobile.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.spetsmobile.R;
import com.example.spetsmobile.model.response.BookingResponse;
import com.example.spetsmobile.model.response.HospitalResponse;
import com.example.spetsmobile.model.response.MedicalRecordResponse;
import com.example.spetsmobile.util.ConstantUtil;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;
import java.util.Date;

public class MedicalRecordFormActivity extends AppCompatActivity {

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

    private BookingResponse bookingResponse = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_medical_record_form);

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

        MedicalRecordResponse medicalRecordResponse = ConstantUtil.getMedicalRecordResponse();
        if (medicalRecordResponse == null) {
            medicalRecordResponse = new MedicalRecordResponse();
        }

        edtPet.setText(medicalRecordResponse.getPet().getName());
        edtHospital.setText(medicalRecordResponse.getHospital().getName());
        edtAppointmentDate.setText(medicalRecordResponse.getAppointmentDate());
        edtDiagnosis.setText(medicalRecordResponse.getDiagnosis());
        edtPrescription.setText(medicalRecordResponse.getPrescription());
        edtNotes.setText(medicalRecordResponse.getNotes());

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
            Intent intent = new Intent(MedicalRecordFormActivity.this, LoginActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

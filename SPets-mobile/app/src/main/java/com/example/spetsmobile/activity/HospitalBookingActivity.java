package com.example.spetsmobile.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.spetsmobile.R;
import com.example.spetsmobile.itf.BookingInterface;
import com.example.spetsmobile.model.request.BookingResquest;
import com.example.spetsmobile.model.response.ApiReponse;
import com.example.spetsmobile.model.response.HospitalResponse;
import com.example.spetsmobile.model.response.VaccineResponse;
import com.example.spetsmobile.restapi.BookingAPI;
import com.example.spetsmobile.util.ConstantUtil;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Map;

public class HospitalBookingActivity extends AppCompatActivity implements BookingInterface {

    private TextInputLayout textInputName;
    private EditText edtName;

    private TextInputLayout textInputTitle;
    private EditText edtTitle;

    private TextInputLayout textInputDesc;
    private EditText edtDesc;

    private TextInputLayout textInputDate;
    private EditText edtDate;

    private TextInputLayout textInputTime;
    private EditText edtTime;

    private Button btnSubmit;

    private final Calendar myCalendar= Calendar.getInstance();
    private HospitalResponse response = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_hospital_booking);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Thiết lập nút "Back" ở tiêu đề
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edtName = findViewById(R.id.edtName);
        textInputName = findViewById(R.id.textInputName);

        edtTitle = findViewById(R.id.edtTitle);
        textInputTitle = findViewById(R.id.textInputTitle);

        edtDesc = findViewById(R.id.edtDesc);
        textInputDesc = findViewById(R.id.textInputDesc);

        edtDate = findViewById(R.id.edtDate);
        textInputDate = findViewById(R.id.textInputDate);

        edtTime = findViewById(R.id.edtTime);
        textInputTime = findViewById(R.id.textInputTime);

        btnSubmit = findViewById(R.id.btnSubmit);

        response = ConstantUtil.getHospitalResponse();
        if (response == null) {
            response = new HospitalResponse();
        }

        edtName.setText(response.getName());
//        edtPhone.setText(response.getPhone());
//        edtEmail.setText(response.getEmail());
//        edtAddress.setText(response.getAddress());

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };

        edtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(HospitalBookingActivity.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        edtTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog(edtTime);
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = edtTitle.getText().toString();
                String desc = edtDesc.getText().toString();
                String date = edtDate.getText().toString();

                // Kiểm tra dữ liệu
                if (validator(title, desc, date)) {
                    BookingResquest bookingResquest = new BookingResquest();
                    bookingResquest.setAccountId(ConstantUtil.getAuth().getId());
                    bookingResquest.setHospitalId(response.getId());
                    bookingResquest.setTitle(edtTitle.getText().toString());
                    bookingResquest.setDescription(edtDesc.getText().toString());
                    bookingResquest.setDate(edtDate.getText().toString());
                    bookingResquest.setStartTime(edtTime.getText().toString());
                    bookingResquest.setEndTime(edtTime.getText().toString());
                    bookingResquest.setStatus("PENDING");

                    BookingAPI api = new BookingAPI(HospitalBookingActivity.this);
                    api.bookingSave(bookingResquest);
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
            Intent intent = new Intent(HospitalBookingActivity.this, LoginActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void updateLabel(){
        String myFormat="dd/MM/yyyy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        edtDate.setText(dateFormat.format(myCalendar.getTime()));
    }

    private boolean validator(String title, String desc, String date) {
        int countError = 0;

        // kiểm tra tiêu đề
        if (title.length() == 0) {
            countError++;
            textInputTitle.setError("Vui lòng nhập tiêu đề!");
        } else {
            textInputTitle.setError(null);
        }

        // kiểm tra ghi chú
        if (desc.length() == 0) {
            countError++;
            textInputDesc.setError("Vui lòng nhập ghi chú!");
        } else {
            textInputDesc.setError(null);
        }

        // kiểm tra ngày
        if (date.length() == 0) {
            countError++;
            textInputDate.setError("Vui lòng chọn ngày hẹn!");
        } else {
            textInputDate.setError(null);
        }

        return countError == 0;
    }

    @Override
    public void onSuccess(String type, ApiReponse apiReponse) {
        if (apiReponse != null) {
            finish();
        }
    }

    @Override
    public void onError(String type, Map<String, String> error) {

    }

    private void showTimePickerDialog(EditText editText) {
        // Lấy thời gian hiện tại
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        // Tạo và hiển thị TimePickerDialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String selectedTime = String.format("%02d:%02d", hourOfDay, minute);
                        editText.setText(selectedTime); // Hiển thị giờ được chọn trong EditText
                    }
                }, hour, minute, true); // true để hiển thị 24 giờ, false để hiển thị AM/PM

        timePickerDialog.show();
    }

}
package com.example.spetsmobile.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;

import com.example.spetsmobile.R;
import com.example.spetsmobile.api.APIClient;
import com.example.spetsmobile.itf.VaccineInterface;
import com.example.spetsmobile.model.request.VaccineRequest;
import com.example.spetsmobile.model.response.ApiReponse;
import com.example.spetsmobile.model.response.PetResponse;
import com.example.spetsmobile.model.response.VaccineResponse;
import com.example.spetsmobile.restapi.VaccineAPI;
import com.example.spetsmobile.util.ConstantUtil;
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class VaccineFormActivity extends AppCompatActivity implements VaccineInterface {

    private ImageView imageView;
    private ImageView imgAvatar;

    private TextInputLayout textInputName;
    private EditText edtName;

    private TextInputLayout textInputDate;
    private EditText edtDate;

    private TextInputLayout textInputNote;
    private EditText edtNote;


    private Button btnSubmit;

    private final Calendar myCalendar= Calendar.getInstance();
    private PetResponse response = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_vaccine_form);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Thiết lập nút "Back" ở tiêu đề
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageView = findViewById(R.id.imageView);
        imgAvatar = findViewById(R.id.imgAvatar);

        edtName = findViewById(R.id.edtName);
        textInputName = findViewById(R.id.textInputName);

        edtDate = findViewById(R.id.edtDate);
        textInputDate = findViewById(R.id.textInputDate);

        edtNote = findViewById(R.id.edtNote);
        textInputNote = findViewById(R.id.textInputNote);

        btnSubmit = findViewById(R.id.btnSubmit);

        response = ConstantUtil.getPetResponse();
        if (response == null) {
            response = new PetResponse();
        }

        edtName.setText(response.getName());

        String imageURL = APIClient.HOST_URL + "/resources/file" + response.getAvatar();
        Picasso.get().load(imageURL).placeholder(R.drawable.icon_avatar).into(imgAvatar);

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
                new DatePickerDialog(VaccineFormActivity.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = edtDate.getText().toString();
                String note = edtNote.getText().toString();

                // Kiểm tra dữ liệu
                if (validator(date, note)) {
                    VaccineRequest vaccineRequest = new VaccineRequest();
                    vaccineRequest.setPetId(response.getId());
                    vaccineRequest.setDate(edtDate.getText().toString());
                    vaccineRequest.setName(edtNote.getText().toString());

                    VaccineAPI api = new VaccineAPI(VaccineFormActivity.this);
                    api.saveVaccine(vaccineRequest);
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            // xử lý sự kiện khi nút "Back" được nhấn
            finish(); // đóng hoạt động và quay về hoạt động trước đó
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void updateLabel(){
        String myFormat="dd/MM/yyyy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        edtDate.setText(dateFormat.format(myCalendar.getTime()));
    }

    @Override
    public void onSuccess(String type, ApiReponse apiReponse) {
        if (apiReponse != null) {
            VaccineResponse response = (VaccineResponse) apiReponse.getPayload();

            finish();
        }
    }

    @Override
    public void onError(String type, String error) {

    }

    private boolean validator(String date, String note) {
        int countError = 0;

        // kiểm tra ngày
        if (date.length() == 0) {
            countError++;
            textInputDate.setError("Vui lòng chọn ngày tiêm!");
        } else {
            textInputDate.setError(null);
        }

        // kiểm tra ghi chú
        if (note.length() == 0) {
            countError++;
            textInputNote.setError("Vui lòng nhập ghi chú!");
        } else {
            textInputNote.setError(null);
        }

        return countError == 0;
    }

}
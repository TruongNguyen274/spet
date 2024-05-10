package com.example.spetsmobile.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import com.example.spetsmobile.api.APIClient;
import com.example.spetsmobile.itf.PetInterface;
import com.example.spetsmobile.model.request.PetRequest;
import com.example.spetsmobile.model.response.ApiReponse;
import com.example.spetsmobile.model.response.PetResponse;
import com.example.spetsmobile.restapi.PetAPI;
import com.example.spetsmobile.util.ConstantUtil;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.widget.Toolbar;

import com.example.spetsmobile.R;
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class PetFormActivity extends AppCompatActivity implements PetInterface {

    private ImageView imgAvatar;

    private TextInputLayout textInputName;
    private EditText edtName;

    private TextInputLayout textInputColor;
    private EditText edtColor;

    private TextInputLayout textInputSpecies;
    private EditText edtSpecies;

    private TextInputLayout textInputBirthDate;
    private EditText edtBirthDate;

    private TextInputLayout textInputHeight;
    private EditText edtHeight;

    private TextInputLayout textInputWeight;
    private EditText edtWeight;

    private TextInputLayout textInputHealth;
    private EditText edtHealth;
    private Button btnSubmit;

    private final Calendar myCalendar= Calendar.getInstance();

    private static final int REQUEST_IMAGE_CAPTURE = 1;

    private byte[] byteArray = null;
    private Bitmap imageBitmap = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_form);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Thiết lập nút "Back" ở tiêu đề
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imgAvatar = findViewById(R.id.imgAvatar);

        edtName = findViewById(R.id.edtName);
        textInputName = findViewById(R.id.textInputName);

        edtColor = findViewById(R.id.edtColor);
        textInputColor = findViewById(R.id.textInputColor);

        edtSpecies = findViewById(R.id.edtSpecies);
        textInputSpecies = findViewById(R.id.textInputSpecies);

        edtBirthDate = findViewById(R.id.edtBirthDate);
        textInputBirthDate = findViewById(R.id.textInputBirthDate);

        edtHeight = findViewById(R.id.edtHeight);
        textInputHeight = findViewById(R.id.textInputHeight);

        edtWeight = findViewById(R.id.edtWeight);
        textInputWeight = findViewById(R.id.textInputWeight);

        edtHealth = findViewById(R.id.edtHealth);
        textInputHealth = findViewById(R.id.textInputHealth);

        btnSubmit = findViewById(R.id.btnSubmit);

        PetResponse response = ConstantUtil.getPetResponse();
        if (response == null) {
            response = new PetResponse();
        }

        edtName.setText(response.getName());
        edtColor.setText(response.getColor());
        edtSpecies.setText(response.getSpecies());
        edtBirthDate.setText(response.getBirthdate());
        edtHeight.setText(String.valueOf(response.getHeight()));
        edtWeight.setText(String.valueOf(response.getWeight()));
        edtHealth.setText(response.getHealth());

        String imageURL = APIClient.HOST_URL + "/resources/file" + response.getAvatar();
        Picasso.get().load(imageURL).placeholder(R.drawable.icon_camera).into(imgAvatar);

        imgAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };

        edtBirthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(PetFormActivity.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PetRequest petRequest = new PetRequest();
                petRequest.setName(edtName.getText().toString());
                petRequest.setColor(edtColor.getText().toString());
                petRequest.setSpecies(edtSpecies.getText().toString());
                petRequest.setHealth(edtHealth.getText().toString());
                petRequest.setHeight(Double.parseDouble(edtHeight.getText().toString()));
                petRequest.setWeight(Double.parseDouble(edtWeight.getText().toString()));
                petRequest.setBirthDay(edtBirthDate.getText().toString());
                petRequest.setStatus(true);

                PetAPI petAPI = new PetAPI(PetFormActivity.this);
                petAPI.petSave(petRequest);
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
            Intent intent = new Intent(PetFormActivity.this, LoginActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSuccess(String type, ApiReponse apiReponse) {
        if (apiReponse != null && type.equalsIgnoreCase("PET_SAVE")) {
            // update data
            PetResponse petResponse = (PetResponse) apiReponse.getPayload();
            ConstantUtil.setPetResponse(petResponse);

            finish();
        }
    }

    @Override
    public void onError(String type, Map<String, String> error) {
        if (error != null) {
            if (error.containsKey("name")) {
                textInputName.setError("Vui lòng nhập tên thú cưng!");
            }

            if (error.containsKey("color")) {
                textInputColor.setError("Vui lòng nhập màu sắc!");
            }

            if (error.containsKey("species")) {
                textInputSpecies.setError("Vui lòng nhập giống loài!");
            }

            if (error.containsKey("birthDay")) {
                textInputBirthDate.setError("Vui lòng nhập ngày sinh!");
            }

            if (error.containsKey("height")) {
                textInputHeight.setError("Vui lòng nhập chiều cao!");
            }

            if (error.containsKey("weight")) {
                textInputWeight.setError("Vui lòng nhập cân nặng!");
            }

            if (error.containsKey("health")) {
                textInputHealth.setError("Vui lòng nhập sức khoẻ!");
            }
        }
    }

    private void updateLabel(){
        String myFormat="dd/MM/yyyy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        edtBirthDate.setText(dateFormat.format(myCalendar.getTime()));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");

            // Ảnh đã chụp có thể được sử dụng ở đây
            ImageView imageView = findViewById(R.id.imgAvatar);
            imageView.setImageBitmap(imageBitmap);

            // Chuyển đổi Bitmap thành mảng byte
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byteArray = stream.toByteArray();

            PetResponse response = ConstantUtil.getPetResponse();
            if (response == null) {
                response = new PetResponse();
            }

            PetAPI petAPI = new PetAPI(PetFormActivity.this);
            petAPI.petSavePhoto(response.getId(), byteArray);
        }
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

}
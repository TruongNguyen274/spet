package com.example.spetsmobile.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.example.spetsmobile.R;
import com.example.spetsmobile.api.APIClient;
import com.example.spetsmobile.itf.ScheduleInterface;
import com.example.spetsmobile.model.request.ScheduleResquest;
import com.example.spetsmobile.model.response.ApiReponse;
import com.example.spetsmobile.model.response.PetResponse;
import com.example.spetsmobile.model.response.ScheduleResponse;
import com.example.spetsmobile.restapi.ScheduleAPI;
import com.example.spetsmobile.util.ConstantUtil;
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Map;

public class ScheduleFormActivity extends AppCompatActivity implements ScheduleInterface {

    private ImageView imgAvatar;

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
    private Button btnDelete;

    private final Calendar myCalendar= Calendar.getInstance();
    private ScheduleResponse response = null;
    private PetResponse petResponse = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_schedule_form);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Thiết lập nút "Back" ở tiêu đề
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imgAvatar = findViewById(R.id.imgAvatar);

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
        btnDelete = findViewById(R.id.btnDelete);

        response = ConstantUtil.getScheduleResponse();
        if (response == null) {
            response = new ScheduleResponse();
        }

        petResponse = ConstantUtil.getPetResponse();
        if (petResponse == null) {
            petResponse = new PetResponse();
        }

        edtName.setText(petResponse.getName());
        edtTitle.setText(response.getTitle());
        edtDesc.setText(response.getDescription());
        edtDate.setText(response.getActivityDate());
        edtTime.setText(String.valueOf(response.getStartTime()));

        String imageURL = APIClient.HOST_URL + "/resources/file" + petResponse.getAvatar();
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
                new DatePickerDialog(ScheduleFormActivity.this, date, myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        edtTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog(edtTime);
            }
        });

        Spinner spinType = findViewById(R.id.spinType);
        // Tạo danh sách lựa chọn
        String[] optionTypes = {"TIÊM CHỦNG", "TẮM RỬA", "ĐI DẠO", "CHO ĂN"};
        // Tạo Adapter
        ArrayAdapter<String> adapterType = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, optionTypes);
        adapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Gắn Adapter vào Spinner
        spinType.setAdapter(adapterType);

        // Tìm vị trí của một mục cụ thể trong danh sách items
        String selectedValue = response.getActivityType();
        int positionType = -1;
        for (int i = 0; i < optionTypes.length; i++) {
            if (optionTypes[i].equals(selectedValue)) {
                positionType = i;
                break;
            }
        }

        // Nếu tìm được vị trí, chọn mục đó trong Spinner
        if (positionType != -1) {
            spinType.setSelection(positionType);
        }

        Spinner spinRepect = findViewById(R.id.spinRepect);
        String[] optionRepects = {"KHÔNG LẶP LẠI", "LẶP THEO NGÀY", "LẶP THEO TUẦN", "LẶP THEO THÁNG"};
        ArrayAdapter<String> adapterRepect = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, optionRepects);
        adapterRepect.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinRepect.setAdapter(adapterRepect);

        // Tìm vị trí của một mục cụ thể trong danh sách items
        String selectedRepeat = response.getRepeatInterval();
        int positionRepeat = -1;
        for (int i = 0; i < optionRepects.length; i++) {
            if (optionRepects[i].equals(selectedRepeat)) {
                positionRepeat = i;
                break;
            }
        }

        // Nếu tìm được vị trí, chọn mục đó trong Spinner
        if (positionRepeat != -1) {
            spinRepect.setSelection(positionRepeat);
        }

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScheduleResquest scheduleResquest = new ScheduleResquest();
                scheduleResquest.setId(response.getId());
                scheduleResquest.setPetId(petResponse.getId());
                scheduleResquest.setTitle(edtTitle.getText().toString());
                scheduleResquest.setDescription(edtDesc.getText().toString());
                scheduleResquest.setActivityDate(edtDate.getText().toString());
                scheduleResquest.setActivityType(spinType.getSelectedItem().toString());
                scheduleResquest.setRepeatInterval(spinRepect.getSelectedItem().toString());
                scheduleResquest.setStartTime(edtTime.getText().toString());
                scheduleResquest.setActivityDate(edtDate.getText().toString());
                scheduleResquest.setStatus(true);

                ScheduleAPI scheduleAPI = new ScheduleAPI(ScheduleFormActivity.this);
                scheduleAPI.scheduleSave(scheduleResquest);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (response.getId() > 0) {
                    showYesNoDialog();
                }
            }
        });

        if (response.getId() > 0) {
            btnDelete.setVisibility(View.VISIBLE);
        } else {
            btnDelete.setVisibility(View.GONE);
        }

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
                        String selectedTime = hourOfDay + ":" + minute;
                        editText.setText(selectedTime); // Hiển thị giờ được chọn trong EditText
                    }
                }, hour, minute, true); // true để hiển thị 24 giờ, false để hiển thị AM/PM

        timePickerDialog.show();
    }

    @Override
    public void onSuccess(String type, ApiReponse apiReponse) {
        if (apiReponse != null && type.equalsIgnoreCase("SCHEDULE_SAVE")) {
            finish();
        } else {
            if (type.equalsIgnoreCase("SCHEDULE_DELETE")) {
                finish();
            }
        }
    }

    @Override
    public void onError(String type, Map<String, String> error) {

    }

    // Tạo hàm để hiển thị cửa sổ dialog với lựa chọn Yes/No
    private void showYesNoDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xác Nhận"); // Tiêu đề của cửa sổ dialog
        builder.setMessage("Bạn chắc chắn muốn xoá dữ liệu?"); // Nội dung thông báo

        // Nút Yes
        builder.setPositiveButton("Đồng Ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ScheduleAPI scheduleAPI = new ScheduleAPI(ScheduleFormActivity.this);
                scheduleAPI.scheduleDelete(response.getId());
            }
        });

        // Nút No
        builder.setNegativeButton("Huỷ Bỏ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        // Tạo và hiển thị cửa sổ dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
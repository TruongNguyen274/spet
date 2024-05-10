package com.example.spetsmobile.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.spetsmobile.R;
import com.example.spetsmobile.adapter.CalendarAdapter;
import com.example.spetsmobile.itf.BookingInterface;
import com.example.spetsmobile.itf.HospitalInterface;
import com.example.spetsmobile.model.response.ApiReponse;
import com.example.spetsmobile.model.response.AuthResponse;
import com.example.spetsmobile.model.response.BookingResponse;
import com.example.spetsmobile.model.response.HospitalResponse;
import com.example.spetsmobile.restapi.BookingAPI;
import com.example.spetsmobile.restapi.HospitalAPI;
import com.example.spetsmobile.util.ConstantUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HospitalCalendarActivity extends AppCompatActivity implements BookingInterface {

    private CalendarAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_hospital_calendar);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Thiết lập nút "Back" ở tiêu đề
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Kết nối RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        // Tạo Adapter để hiển thị danh sách
        adapter = new CalendarAdapter(getApplicationContext(), new ArrayList<>());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

//        HospitalAPI hospitalAPI = new HospitalAPI(HospitalCalendarActivity.this);
//        hospitalAPI.findByAccount(authResponse.getId());

        BookingAPI bookingAPI = new BookingAPI(HospitalCalendarActivity.this);
        bookingAPI.findAllBookingByHospital(ConstantUtil.getHospitalResponse().getId(), "");
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
            Intent intent = new Intent(HospitalCalendarActivity.this, LoginActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSuccess(String type, ApiReponse apiReponse) {
        List<BookingResponse> responseList = (List<BookingResponse>) apiReponse.getPayload();
        adapter.setDataList(responseList);
    }

    @Override
    public void onError(String type, Map<String, String> error) {

    }

}
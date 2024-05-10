package com.example.spetsmobile.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spetsmobile.R;
import com.example.spetsmobile.adapter.MedicalRecordAdapter;
import com.example.spetsmobile.itf.MedicalRecordInterface;
import com.example.spetsmobile.model.response.ApiReponse;
import com.example.spetsmobile.model.response.MedicalRecordResponse;
import com.example.spetsmobile.model.response.PetResponse;
import com.example.spetsmobile.restapi.MedicalRecordAPI;
import com.example.spetsmobile.util.ConstantUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MedicalRecordActivity extends AppCompatActivity implements MedicalRecordInterface {

    private MedicalRecordAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_medical_record);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Thiết lập nút "Back" ở tiêu đề
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Kết nối RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        // Tạo Adapter để hiển thị danh sách
        adapter = new MedicalRecordAdapter(getApplicationContext(), new ArrayList<>());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        MedicalRecordAPI medicalRecordAPI = new MedicalRecordAPI(MedicalRecordActivity.this);
        PetResponse petResponse = ConstantUtil.getPetResponse();
        if (petResponse == null) {
            petResponse = new PetResponse();
        }
        medicalRecordAPI.findByPet(petResponse.getId());
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
            Intent intent = new Intent(MedicalRecordActivity.this, LoginActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSuccess(String type, ApiReponse apiReponse) {
        List<MedicalRecordResponse> responseList = (List<MedicalRecordResponse>) apiReponse.getPayload();
        adapter.setDataList(responseList);
    }

    @Override
    public void onError(String type, Map<String, String> error) {

    }

}
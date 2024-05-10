package com.example.spetsmobile.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.spetsmobile.R;
import com.example.spetsmobile.adapter.LayoutAdapter;
import com.example.spetsmobile.api.APIClient;
import com.example.spetsmobile.itf.ScheduleInterface;
import com.example.spetsmobile.model.response.ApiReponse;
import com.example.spetsmobile.model.response.HealthRecordResponse;
import com.example.spetsmobile.model.response.PetResponse;
import com.example.spetsmobile.restapi.ScheduleAPI;
import com.example.spetsmobile.restapi.VaccineAPI;
import com.example.spetsmobile.util.ConstantUtil;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ScheduleActivity extends AppCompatActivity implements ScheduleInterface {

    private TextView tvName;
    private ImageView imgAvatar;
    private TextView tvHealth;

    private LayoutAdapter adapter;


    private PetResponse response = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_schedule);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Thiết lập nút "Back" ở tiêu đề
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvName = findViewById(R.id.tvName);
        imgAvatar = findViewById(R.id.imgAvatar);
        tvHealth = findViewById(R.id.tvHealth);

        response = ConstantUtil.getPetResponse();
        if (response == null) {
            response = new PetResponse();
        }

        tvName.setText(response.getName());
        tvHealth.setText(response.getHealth());

        String imageURL = APIClient.HOST_URL + "/resources/file" + response.getAvatar();
        Picasso.get().load(imageURL).placeholder(R.drawable.icon_avatar).into(imgAvatar);

        // Kết nối RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        // Tạo Adapter để hiển thị danh sách
        adapter = new LayoutAdapter(getApplicationContext(), "", new ArrayList<>());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        ScheduleAPI api = new ScheduleAPI(ScheduleActivity.this);
        api.findAllScheduleByPet(response.getId());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add, menu);
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

        if (id == R.id.action_add) {
            ConstantUtil.setScheduleResponse(null);

            Intent intent = new Intent(ScheduleActivity.this, ScheduleFormActivity.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_logout) {
            Intent intent = new Intent(ScheduleActivity.this, LoginActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSuccess(String type, ApiReponse apiReponse) {
        List<HealthRecordResponse> responseList = (List<HealthRecordResponse>) apiReponse.getPayload();
        adapter.setDataList(responseList, "SCHEDULE");
    }

    @Override
    public void onError(String type, Map<String, String> error) {

    }

    @Override
    protected void onResume() {
        super.onResume();

        ScheduleAPI api = new ScheduleAPI(ScheduleActivity.this);
        api.findAllScheduleByPet(response.getId());
    }

}
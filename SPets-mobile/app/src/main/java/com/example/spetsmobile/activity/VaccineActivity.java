package com.example.spetsmobile.activity;

import android.content.Intent;
import android.os.Bundle;

import com.example.spetsmobile.adapter.LayoutAdapter;
import com.example.spetsmobile.api.APIClient;
import com.example.spetsmobile.itf.VaccineInterface;
import com.example.spetsmobile.model.response.ApiReponse;
import com.example.spetsmobile.model.response.PetResponse;
import com.example.spetsmobile.model.response.VaccineResponse;
import com.example.spetsmobile.restapi.VaccineAPI;
import com.example.spetsmobile.util.ConstantUtil;

import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spetsmobile.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class VaccineActivity extends AppCompatActivity implements VaccineInterface {

    private TextView tvName;
    private ImageView imgAvatar;
    private TextView tvHealth;

    private LayoutAdapter adapter;

    private PetResponse response = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_vaccine);

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
        adapter = new LayoutAdapter(getApplicationContext(), "VACCINE", new ArrayList<>());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        VaccineAPI api = new VaccineAPI(VaccineActivity.this);
        api.findAllVaccinesByPet(response.getId());
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
            Intent intent = new Intent(VaccineActivity.this, VaccineFormActivity.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_logout) {
            Intent intent = new Intent(VaccineActivity.this, LoginActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSuccess(String type, ApiReponse apiReponse) {
        List<VaccineResponse> responseList = (List<VaccineResponse>) apiReponse.getPayload();
        adapter.setDataList(responseList, "VACCINE");
    }

    @Override
    public void onError(String type, String error) {

    }

    @Override
    protected void onResume() {
        super.onResume();

        VaccineAPI api = new VaccineAPI(VaccineActivity.this);
        api.findAllVaccinesByPet(response.getId());
    }

}
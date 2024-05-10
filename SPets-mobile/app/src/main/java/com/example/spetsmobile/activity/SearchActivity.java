package com.example.spetsmobile.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spetsmobile.R;
import com.example.spetsmobile.adapter.SearchAdapter;
import com.example.spetsmobile.itf.DiseaseInterface;
import com.example.spetsmobile.itf.SearchInterface;
import com.example.spetsmobile.model.request.SearchRequest;
import com.example.spetsmobile.model.response.ApiReponse;
import com.example.spetsmobile.model.response.DiseaseResponse;
import com.example.spetsmobile.model.response.SymptomResponse;
import com.example.spetsmobile.restapi.SearchAPI;
import com.example.spetsmobile.util.ConstantUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchActivity extends AppCompatActivity implements SearchInterface, DiseaseInterface {

    private SearchAdapter adapter;

    private Button btnSubmit;

    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Thiết lập nút "Back" ở tiêu đề
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        adapter = new SearchAdapter(getApplicationContext(), new ArrayList<>());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        SearchAPI searchAPI = new SearchAPI(SearchActivity.this);
        searchAPI.findAllSymptom();

        btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(v -> {
            List<String> selectedDataList = adapter.getSelectedItems();

            SearchRequest searchRequest = new SearchRequest();
            searchRequest.setSymptomList(selectedDataList);
            searchAPI.search(searchRequest);
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);

        // Thiết lập nút "Search"
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }
        });

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
            Intent intent = new Intent(SearchActivity.this, LoginActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSuccess(String type, ApiReponse apiReponse) {
        if (type.equals("SYMPTOM_LIST")) {
            List<SymptomResponse> responseList = (List<SymptomResponse>) apiReponse.getPayload();
            adapter.setDataList(responseList);
        }

        if (type.equals("DISEASE_LIST")) {
            List<DiseaseResponse> diseaseResponseList = (List<DiseaseResponse>) apiReponse.getPayload();
            ConstantUtil.setDiseaseResponseList(diseaseResponseList);

            Intent intent = new Intent(SearchActivity.this, DiseaseActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onError(String type, Map<String, String> error) {

    }
}

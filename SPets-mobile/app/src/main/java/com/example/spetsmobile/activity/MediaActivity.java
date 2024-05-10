package com.example.spetsmobile.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.spetsmobile.R;
import com.example.spetsmobile.adapter.ImageAdapter;
import com.example.spetsmobile.api.APIClient;
import com.example.spetsmobile.itf.MediaInterface;
import com.example.spetsmobile.model.response.ApiReponse;
import com.example.spetsmobile.model.response.MediaResponse;
import com.example.spetsmobile.model.response.PetResponse;
import com.example.spetsmobile.restapi.MediaAPI;
import com.example.spetsmobile.util.ConstantUtil;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MediaActivity extends AppCompatActivity implements MediaInterface {

    private ImageAdapter adapter;

    private static final int REQUEST_IMAGE_CAPTURE = 1;

    private byte[] byteArray = null;
    private Bitmap imageBitmap = null;

    private PetResponse response = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Thiết lập nút "Back" ở tiêu đề
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        response = ConstantUtil.getPetResponse();
        if (response == null) {
            response = new PetResponse();
        }

        ImageView imgAvatar = findViewById(R.id.imgAvatar);
        String imageURL = APIClient.HOST_URL + "/resources/file" + response.getAvatar();
        Picasso.get().load(imageURL).placeholder(R.drawable.icon_camera).into(imgAvatar);

        // Kết nối RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        // Tạo Adapter để hiển thị danh sách
        adapter = new ImageAdapter(getApplicationContext(),  new ArrayList<>());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));

        MediaAPI api = new MediaAPI(MediaActivity.this);
        api.findAllMediaByPet(response.getId());
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
            dispatchTakePictureIntent();
            return true;
        }

        if (id == R.id.action_logout) {
            Intent intent = new Intent(MediaActivity.this, LoginActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSuccess(String type, ApiReponse apiReponse) {
        if (apiReponse != null) {
            switch (type) {
                case "MEDIA_LIST":
                    List<MediaResponse> responseList = (List<MediaResponse>) apiReponse.getPayload();
                    adapter.setDataList(responseList);
                    break;
                case "MEDIA_SAVE":
                    MediaAPI api = new MediaAPI(MediaActivity.this);
                    api.findAllMediaByPet(response.getId());
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onError(String type, String error) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");

            // Chuyển đổi Bitmap thành mảng byte
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byteArray = stream.toByteArray();

            PetResponse response = ConstantUtil.getPetResponse();
            if (response == null) {
                response = new PetResponse();
            }

            MediaAPI api = new MediaAPI(MediaActivity.this);
            api.petSaveMedia(response.getId(), byteArray);
        }

    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

}
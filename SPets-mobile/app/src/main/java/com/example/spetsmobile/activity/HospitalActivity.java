package com.example.spetsmobile.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.spetsmobile.R;
import com.example.spetsmobile.adapter.BookingAdapter;
import com.example.spetsmobile.adapter.HospitalAdapter;
import com.example.spetsmobile.itf.BookingInterface;
import com.example.spetsmobile.itf.HospitalInterface;
import com.example.spetsmobile.model.request.BookingResquest;
import com.example.spetsmobile.model.response.ApiReponse;
import com.example.spetsmobile.model.response.BookingResponse;
import com.example.spetsmobile.model.response.HospitalResponse;
import com.example.spetsmobile.restapi.BookingAPI;
import com.example.spetsmobile.restapi.HospitalAPI;
import com.example.spetsmobile.util.ConstantUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HospitalActivity extends AppCompatActivity implements HospitalInterface, BookingInterface,
        BookingAdapter.OnItemClickListener {

    private BookingAdapter adapter = null;

    private HospitalResponse hospitalResponse = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final TextView tvHello = findViewById(R.id.tvHello);
        tvHello.setText("Xin chào, " + ConstantUtil.getAuth().getFullname());

        final CardView cvHospital = findViewById(R.id.cvHospital);
        cvHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HospitalActivity.this, HospitalFormActivity.class);
                startActivity(intent);
            }
        });

        final CardView cvBooking = findViewById(R.id.cvBooking);
        cvBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HospitalActivity.this, HospitalCalendarActivity.class);
                startActivity(intent);
            }
        });

        // Kết nối RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        // Tạo Adapter để hiển thị danh sách
        adapter = new BookingAdapter(HospitalActivity.this, getApplicationContext(), new ArrayList<>());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        HospitalAPI hospitalAPI = new HospitalAPI(HospitalActivity.this);
        hospitalAPI.findByAccount(ConstantUtil.getAuth().getId());

        adapter.setOnItemClickListener(this);
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

        if (id == R.id.action_logout) {
            Intent intent = new Intent(HospitalActivity.this, LoginActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSuccess(String type, ApiReponse apiReponse) {
        if (apiReponse != null) {
            if (type.equalsIgnoreCase("BOOKING_LIST")) {
                List<BookingResponse> responseList = (List<BookingResponse>) apiReponse.getPayload();
                adapter.setDataList(responseList);
            }

            if (type.equalsIgnoreCase("HOSPITAL_ACCOUNT")) {
                HospitalResponse response = (HospitalResponse) apiReponse.getPayload();
                hospitalResponse = response;
                ConstantUtil.setHospitalResponse(response);

                BookingAPI bookingAPI = new BookingAPI(HospitalActivity.this);
                bookingAPI.findAllBookingByHospital(response.getId(), "PENDING");
            }

            if (type.equalsIgnoreCase("BOOKING_STATUS")) {
                BookingAPI bookingAPI = new BookingAPI(HospitalActivity.this);
                bookingAPI.findAllBookingByHospital(hospitalResponse.getId(), "PENDING");
            }

            if (type.equalsIgnoreCase("BOOKING_SHOW")) {
                BookingAPI bookingAPI = new BookingAPI(HospitalActivity.this);
                bookingAPI.findAllBookingByHospital(hospitalResponse.getId(), "PENDING");
            }
        }
    }

    @Override
    public void onError(String type, Map<String, String> error) {

    }

    @Override
    public void onItemClick(BookingResponse response) {
        BookingResquest bookingResquest = new BookingResquest();
        bookingResquest.setId(response.getId());
        bookingResquest.setStatus(response.getStatus());

        BookingAPI bookingAPI = new BookingAPI(HospitalActivity.this);
        bookingAPI.bookingStatus(bookingResquest);
    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String key = intent.getExtras().getString("key");
            String value = intent.getExtras().getString("value");

            BookingResquest bookingResquest = new BookingResquest();
            bookingResquest.setId(Long.parseLong(key));

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    // show message
                    AlertDialog.Builder builder = new AlertDialog.Builder(HospitalActivity.this);

                    // Setting message manually and performing action on button click
                    builder.setMessage(value)
                            .setCancelable(true)
                            .setPositiveButton("Đồng Ý", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    bookingResquest.setStatus("APPROVED");
                                    BookingAPI bookingAPI = new BookingAPI(HospitalActivity.this);
                                    bookingAPI.bookingStatus(bookingResquest);

                                    dialog.cancel();
                                }
                            })
                            .setNegativeButton("Từ Chối", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    bookingResquest.setStatus("CANCELLED");
                                    BookingAPI bookingAPI = new BookingAPI(HospitalActivity.this);
                                    bookingAPI.bookingStatus(bookingResquest);

                                    dialog.cancel();
                                }
                            });
                    // Creating dialog box
                    AlertDialog alert = builder.create();
                    // Setting the title manually
                    alert.setTitle("Thông Báo");
                    alert.show();
                }
            });
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(this).registerReceiver((mMessageReceiver),
                new IntentFilter("MyMessage")
        );
    }

    @Override
    protected void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
    }

}
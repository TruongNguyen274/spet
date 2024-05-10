package com.example.spetsmobile.activity;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;

import com.example.spetsmobile.R;
import com.example.spetsmobile.itf.ScheduleInterface;
import com.example.spetsmobile.model.response.ApiReponse;
import com.example.spetsmobile.model.response.AuthResponse;
import com.example.spetsmobile.model.response.ScheduleResponse;
import com.example.spetsmobile.restapi.ScheduleAPI;
import com.example.spetsmobile.util.ConstantUtil;
import com.example.spetsmobile.util.NotificationUtils;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.FragmentNavigator;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.spetsmobile.databinding.ActivityMainBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements ScheduleInterface {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private NavigationView navigationView;
    private NavController navController;

    private List<ScheduleResponse> scheduleResponseList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fragment = getCurrentFragmentName();
                Snackbar.make(view, "Replace with your own action >> " + fragment, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                if (fragment != null) {
                    fragment = fragment.replace("com.example.spetsmobile.ui.", "");
                    Intent intent = null;

                    switch (fragment) {
                        case "pet.PetFragment":
                            ConstantUtil.setPetResponse(null);
                            intent = new Intent(MainActivity.this, PetFormActivity.class);
                            break;
                        default:
                            break;
                    }

                    if (intent != null) {
                        startActivity(intent);
                    }
                }
            }
        });

        DrawerLayout drawer = binding.drawerLayout;
        navigationView = binding.navView;

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_pet, R.id.nav_vaccine, R.id.nav_hospital)
                .setOpenableLayout(drawer)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        AuthResponse authResponse = ConstantUtil.getAuth();
        if (authResponse.getRole().equalsIgnoreCase("HOSPITAL")) {
            Intent intent = new Intent(MainActivity.this, HospitalActivity.class);
            startActivity(intent);
        }

        ScheduleAPI api = new ScheduleAPI(MainActivity.this);
        api.findAllScheduleByAccount(ConstantUtil.getAuth().getId());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_logout) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    protected void onResume() {
        super.onResume();

        setProfile();
    }

    private void setProfile() {
        AuthResponse account = ConstantUtil.getAuth();
        if (account != null) {
            View headerView = navigationView.getHeaderView(0);

            // set user
            TextView tvUser = (TextView) headerView.findViewById(R.id.tvUser);
            tvUser.setText(account.getFullname());

            // set role
            TextView tvRole = (TextView) headerView.findViewById(R.id.tvRole);
            tvRole.setText(account.getRole());
        }
    }

    // sẽ trả về tên của fragment đang hiển thị
    public String getCurrentFragmentName() {
        NavDestination currentDestination = navController.getCurrentDestination();
        // Lấy tên lớp của fragment từ tên thành phần của địa chỉ đích
        String className = ((FragmentNavigator.Destination) currentDestination).getClassName();
        return className;
    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String key = intent.getExtras().getString("key");
            String value = intent.getExtras().getString("value");

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    // show message
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                    // Setting message manually and performing action on button click
                    builder.setMessage(value)
                            .setCancelable(true)
                            .setNegativeButton("Xác Nhận", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
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

    @Override
    public void onSuccess(String type, ApiReponse apiReponse) {
        scheduleResponseList = (List<ScheduleResponse>) apiReponse.getPayload();
        startRepeatingTask();
    }

    @Override
    public void onError(String type, Map<String, String> error) {

    }

    // Định nghĩa một hàm chuyển đổi ngày thành millisecond
    public long convertDateToMillis(String date, String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        try {
            Date convertedDate = sdf.parse(date + " " + time);
            return convertedDate.getTime(); // Trả về thời gian dưới dạng millisecond
        } catch (ParseException e) {
            return 0;
        }
    }

    private Handler handler = new Handler();
    private Runnable runnable;

    // Hàm khởi tạo hàm chạy lặp lại
    private void startRepeatingTask() {
        runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    if (!scheduleResponseList.isEmpty()) {
                        for (ScheduleResponse scheduleResponse : scheduleResponseList) {
                            long timeInMillis = convertDateToMillis(scheduleResponse.getActivityDate(), scheduleResponse.getStartTime());

                            Date startTime = new Date(timeInMillis);

                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(startTime);

                            // Xác định thời điểm tiếp theo dựa vào loại lặp lại
                            switch (scheduleResponse.getActivityType()) {
                                case "LẶP THEO NGÀY":
                                    calendar.add(Calendar.DATE, 1); // Lặp lại hàng ngày
                                    break;
                                case "LẶP THEO TUẦN":
                                    calendar.add(Calendar.WEEK_OF_YEAR, 1); // Lặp lại hàng tuần
                                    break;
                                case "LẶP THEO THÁNG":
                                    calendar.add(Calendar.MONTH, 1); // Lặp lại hàng tháng
                                    break;
                                default:
                                    break;
                            }

                            NotificationUtils.scheduleNotification(
                                    getApplicationContext(),
                                    calendar.getTimeInMillis(), // Thời điểm bạn muốn hiển thị thông báo
                                    scheduleResponse.getTitle(), // Tiêu đề thông báo từ ScheduleResponse
                                    scheduleResponse.getDescription() // Nội dung thông báo từ ScheduleResponse
                            );
                        }

                        startRepeatingTask();
                    }

                    handler.postDelayed(this, 60 * 1000);
                } catch (Exception ex) {

                }
            }
        };

        // Đầu tiên, gọi hàm run để bắt đầu hàm chạy lặp lại
        handler.post(runnable);
    }

    // Hàm dừng hàm chạy lặp lại
    private void stopRepeatingTask() {
        handler.removeCallbacks(runnable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        stopRepeatingTask();
    }

}
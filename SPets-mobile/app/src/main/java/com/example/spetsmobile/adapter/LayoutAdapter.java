package com.example.spetsmobile.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spetsmobile.R;
import com.example.spetsmobile.activity.ScheduleFormActivity;
import com.example.spetsmobile.activity.VaccineFormActivity;
import com.example.spetsmobile.api.APIClient;
import com.example.spetsmobile.model.response.HealthRecordResponse;
import com.example.spetsmobile.model.response.ScheduleResponse;
import com.example.spetsmobile.model.response.VaccineResponse;
import com.example.spetsmobile.util.ConstantUtil;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LayoutAdapter extends RecyclerView.Adapter<LayoutAdapter.PetViewHolder> {

    private Context context;
    private String type;
    private List responseList;

    public LayoutAdapter() {
    }

    public LayoutAdapter(Context context, String type, List petList) {
        this.context = context;
        this.type = type;
        this.responseList = responseList;
    }

    @NonNull
    @Override
    public PetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new PetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PetViewHolder holder, int position) {
        switch (type) {
            case "VACCINE":
                VaccineResponse vaccineResponse = (VaccineResponse) responseList.get(position);
                holder.tvTitle.setText(vaccineResponse.getName());
                holder.tvDesc.setText(vaccineResponse.getDate());
                break;
            case "HEALTH":
                HealthRecordResponse healthRecordResponse = (HealthRecordResponse) responseList.get(position);
                holder.tvTitle.setText(healthRecordResponse.getCreatedAt());
                holder.tvDesc.setText(healthRecordResponse.getHealth() + " - " +
                        healthRecordResponse.getHeight() + " cm - " + healthRecordResponse.getWeight() + " kg");
                break;
            case "SCHEDULE":
                ScheduleResponse scheduleResponse = (ScheduleResponse) responseList.get(position);
                holder.tvTitle.setText(scheduleResponse.getTitle());
                holder.tvDesc.setText(scheduleResponse.getActivityType()+ " - " + scheduleResponse.getActivityDate());
                break;
            default:
                break;
        }

        holder.btnLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                switch (type) {
                    case "VACCINE":
                        ConstantUtil.setVaccineResponse((VaccineResponse) responseList.get(position));

                        intent = new Intent(context, VaccineFormActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                        break;
                    case "HEALTH":

                        break;
                    case "SCHEDULE":
                        ConstantUtil.setScheduleResponse((ScheduleResponse) responseList.get(position));

                        intent = new Intent(context, ScheduleFormActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (responseList == null) {
            return 0;
        }

        return responseList.size();
    }

    // Phương thức để cập nhật danh sách thú cưng
    public void setDataList(List responseList, String type) {
        this.type = type;
        this.responseList = responseList;
        notifyDataSetChanged(); // Thông báo cho Adapter cập nhật giao diện
    }

    public class PetViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout btnLayout;
        TextView tvTitle;
        TextView tvDesc;

        public PetViewHolder(View itemView) {
            super(itemView);
            btnLayout = itemView.findViewById(R.id.btnLayout);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDesc = itemView.findViewById(R.id.tvDesc);
        }

    }

}

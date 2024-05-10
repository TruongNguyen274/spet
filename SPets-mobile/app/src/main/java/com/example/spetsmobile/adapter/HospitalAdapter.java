package com.example.spetsmobile.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spetsmobile.R;
import com.example.spetsmobile.activity.HospitalFormActivity;
import com.example.spetsmobile.model.response.HospitalResponse;
import com.example.spetsmobile.util.ConstantUtil;

import java.util.List;

public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.PetViewHolder> {

    private Context context;
    private List<HospitalResponse> responseList;

    public HospitalAdapter(Context context, List<HospitalResponse> responseList) {
        this.context = context;
        this.responseList = responseList;
    }

    @NonNull
    @Override
    public PetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hospital, parent, false);
        return new PetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PetViewHolder holder, int position) {
        HospitalResponse response = responseList.get(position);

        // Hiển thị thông tin
        holder.tvName.setText(response.getName());
        holder.tvPhone.setText("Điện Thoại:" +  response.getPhone());
        holder.tvEmail.setText("Email:" + response.getEmail());
        holder.tvAddress.setText("Địa Chỉ:" + response.getAddress());

        holder.imgCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConstantUtil.setHospitalResponse(responseList.get(position));

                Intent intent = new Intent(context, HospitalFormActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
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

    // Phương thức để cập nhật danh sách
    public void setDataList(List<HospitalResponse> responseList) {
        this.responseList = responseList;
        notifyDataSetChanged(); // Thông báo cho Adapter cập nhật giao diện
    }

    public class PetViewHolder extends RecyclerView.ViewHolder {

        ImageView imgAvatar;
        ImageView imgCard;
        TextView tvName;
        TextView tvPhone;
        TextView tvEmail;
        TextView tvAddress;

        public PetViewHolder(View itemView) {
            super(itemView);
            imgAvatar = itemView.findViewById(R.id.imgAvatar);
            imgCard = itemView.findViewById(R.id.imgCard);
            tvName = itemView.findViewById(R.id.tvName);
            tvPhone = itemView.findViewById(R.id.tvPhone);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            tvAddress = itemView.findViewById(R.id.tvAddress);
        }
    }

}

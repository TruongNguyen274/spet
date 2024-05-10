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
import com.example.spetsmobile.activity.VaccineFormActivity;
import com.example.spetsmobile.api.APIClient;
import com.example.spetsmobile.model.response.VaccineResponse;
import com.example.spetsmobile.util.ConstantUtil;
import com.squareup.picasso.Picasso;

import java.util.List;

public class VaccineAdapter extends RecyclerView.Adapter<VaccineAdapter.PetViewHolder> {

    private Context context;
    private List<VaccineResponse> responseList;

    public VaccineAdapter(Context context, List<VaccineResponse> petList) {
        this.context = context;
        this.responseList = responseList;
    }

    @NonNull
    @Override
    public PetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vaccine, parent, false);
        return new PetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PetViewHolder holder, int position) {
        VaccineResponse response = responseList.get(position);

        // Hiển thị thông tin
        holder.tvPet.setText(response.getPet().getName());
        holder.tvDate.setText(response.getDate());
        holder.tvDesc.setText(response.getName());

        String imageURL = APIClient.HOST_URL + "/resources/file" + response.getPet().getAvatar();
        Picasso.get().load(imageURL).placeholder(R.drawable.icon_picture).into(holder.imgAvatar);

        holder.imgCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConstantUtil.setVaccineResponse(responseList.get(position));

                Intent intent = new Intent(context, VaccineFormActivity.class);
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

    // Phương thức để cập nhật danh sách thú cưng
    public void setDataList(List<VaccineResponse> responseList) {
        this.responseList = responseList;
        notifyDataSetChanged(); // Thông báo cho Adapter cập nhật giao diện
    }

    public class PetViewHolder extends RecyclerView.ViewHolder {

        ImageView imgAvatar;
        ImageView imgCard;
        TextView tvPet;
        TextView tvDate;
        TextView tvDesc;

        public PetViewHolder(View itemView) {
            super(itemView);
            imgAvatar = itemView.findViewById(R.id.imgAvatar);
            imgCard = itemView.findViewById(R.id.imgCard);
            tvPet = itemView.findViewById(R.id.tvPet);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvDesc = itemView.findViewById(R.id.tvDesc);
        }
    }

}

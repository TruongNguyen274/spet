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
import com.example.spetsmobile.activity.PetActivity;
import com.example.spetsmobile.activity.ProfileActivity;
import com.example.spetsmobile.api.APIClient;
import com.example.spetsmobile.model.response.PetResponse;
import com.example.spetsmobile.util.ConstantUtil;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.PetViewHolder> {

    private Context context;
    private List<PetResponse> responseList;


    public PetAdapter(Context context, List<PetResponse> responseList) {
        this.context = context;
        this.responseList = responseList;
    }

    @NonNull
    @Override
    public PetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pet, parent, false);
        return new PetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PetViewHolder holder, int position) {
        PetResponse response = responseList.get(position);

        // Hiển thị thông tin
        holder.tvPet.setText(response.getName());
        holder.tvBirthdate.setText(response.getBirthdate());
        holder.tvDesc.setText(response.getHeight() + " cm | " + response.getWeight() + " kg");
        holder.tvColor.setText(response.getColor());
        String imageURL = APIClient.HOST_URL + "/resources/file" + response.getAvatar();
        Picasso.get().load(imageURL).placeholder(R.drawable.icon_picture).into(holder.imgAvatar);

        holder.imgCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConstantUtil.setPetResponse(responseList.get(position));

                Intent intent = new Intent(context, PetActivity.class);
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
    public void setDataList(List<PetResponse> responseList) {
        this.responseList = responseList;
        notifyDataSetChanged(); // Thông báo cho Adapter cập nhật giao diện
    }

    public class PetViewHolder extends RecyclerView.ViewHolder {

        ImageView imgAvatar;
        ImageView imgCard;
        TextView tvPet;
        TextView tvBirthdate;
        TextView tvColor;
        TextView tvDesc;

        public PetViewHolder(View itemView) {
            super(itemView);
            imgAvatar = itemView.findViewById(R.id.imgAvatar);
            imgCard = itemView.findViewById(R.id.imgCard);
            tvPet = itemView.findViewById(R.id.tvPet);
            tvBirthdate = itemView.findViewById(R.id.tvBirthdate);
            tvColor = itemView.findViewById(R.id.tvColor);
            tvDesc = itemView.findViewById(R.id.tvDesc);
        }
    }
}

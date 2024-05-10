package com.example.spetsmobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spetsmobile.R;
import com.example.spetsmobile.api.APIClient;
import com.example.spetsmobile.model.response.MediaResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.PetViewHolder> {

    private Context context;
    private List<MediaResponse> responseList;

    public ImageAdapter(Context context, List<MediaResponse> responseList) {
        this.context = context;
        this.responseList = responseList;
    }

    @NonNull
    @Override
    public PetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
        return new PetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PetViewHolder holder, int position) {
        MediaResponse response = responseList.get(position);

        // Hiển thị thông tin
        holder.textTitle.setText(String.valueOf(response.getId()));
        String imageURL = APIClient.HOST_URL + "/resources/file" + response.getPath();
        Picasso.get().load(imageURL).placeholder(R.drawable.icon_picture).into(holder.imgAvatar);
    }

    @Override
    public int getItemCount() {
        if (responseList == null) {
            return 0;
        }

        return responseList.size();
    }

    public void setDataList(List<MediaResponse> responseList) {
        this.responseList = responseList;
        notifyDataSetChanged();
    }

    public class PetViewHolder extends RecyclerView.ViewHolder {

        ImageView imgAvatar;
        TextView textTitle;

        public PetViewHolder(View itemView) {
            super(itemView);
            imgAvatar = itemView.findViewById(R.id.imgAvatar);
            textTitle = itemView.findViewById(R.id.textTitle);
        }
    }

}

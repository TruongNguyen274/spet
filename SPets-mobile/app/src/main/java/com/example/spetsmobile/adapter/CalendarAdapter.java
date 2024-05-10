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
import com.example.spetsmobile.activity.HospitalCalendarFormActivity;
import com.example.spetsmobile.model.response.BookingResponse;
import com.example.spetsmobile.util.ConstantUtil;

import java.util.List;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.PetViewHolder> {

    private Context context;
    private List<BookingResponse> responseList;


    public CalendarAdapter(Context context, List<BookingResponse> responseList) {
        this.context = context;
        this.responseList = responseList;
    }

    @NonNull
    @Override
    public PetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_calendar, parent, false);
        return new PetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PetViewHolder holder, int position) {
        BookingResponse response = responseList.get(position);

        // Hiển thị thông tin
        holder.tvTitle.setText(response.getTitle());
        holder.tvAccount.setText(response.getAccountResponse().getName());
        holder.tvDate.setText(response.getDate() + " / " + response.getStartTime()+ " - " + response.getEndTime());
        holder.tvDesc.setText(response.getDescription());

        String progress = "";
        switch (response.getStatus()) {
            case "PENDING" -> progress += "Đang xác nhận";
            case "APPROVED" -> progress += "Đã được chấp nhận";
            case "COMPLETED" -> progress += "Đã hoàn thành";
            default -> progress += "Đã bị từ chối";
        }

        holder.tvStatus.setText(progress);
//        String imageURL = APIClient.HOST_URL + "/resources/file" + response.getPet().getAvatar();
//        Picasso.get().load(imageURL).placeholder(R.drawable.icon_picture).into(holder.imgAvatar);

        holder.imgCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConstantUtil.setBookingResponse(responseList.get(position));

                Intent intent = new Intent(context, HospitalCalendarFormActivity.class);
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

    public void setDataList(List<BookingResponse> responseList) {
        this.responseList = responseList;
        notifyDataSetChanged();
    }

    public class PetViewHolder extends RecyclerView.ViewHolder {

        ImageView imgAvatar;
        ImageView imgCard;
        TextView tvAccount;
        TextView tvDate;
        TextView tvTitle;
        TextView tvDesc;
        TextView tvStatus;

        public PetViewHolder(View itemView) {
            super(itemView);
            imgAvatar = itemView.findViewById(R.id.imgAvatar);
            imgCard = itemView.findViewById(R.id.imgCard);
            tvAccount = itemView.findViewById(R.id.tvAccount);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDesc = itemView.findViewById(R.id.tvDesc);
            tvStatus = itemView.findViewById(R.id.tvStatus);
        }
    }
}

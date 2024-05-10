package com.example.spetsmobile.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spetsmobile.R;
import com.example.spetsmobile.model.response.BookingResponse;

import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.PetViewHolder> {

    private Activity activity;
    private Context context;
    private List<BookingResponse> responseList;

    public interface OnItemClickListener {
        void onItemClick(BookingResponse response);
    }

    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public BookingAdapter(Activity activity, Context context, List<BookingResponse> responseList) {
        this.activity = activity;
        this.context = context;
        this.responseList = responseList;
    }

    @NonNull
    @Override
    public PetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_booking, parent, false);
        return new PetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PetViewHolder holder, int position) {
        BookingResponse response = responseList.get(position);

        // Hiển thị thông tin
        holder.tvTitle.setText(response.getTitle());
        holder.tvDesc.setText(response.getDescription());
        holder.tvDate.setText(response.getDate() + " - " + response.getStartTime());

        holder.imgCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setMessage("Vui lòng xác nhận Đồng Ý hoặc Từ Chối cho yêu cầu [" + response.getTitle() + " - " + response.getDate() +"]")
                        .setCancelable(true)
                        .setPositiveButton("Đồng Ý", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                if (mListener != null) {
                                    response.setStatus("APPROVED");
                                    mListener.onItemClick(response);
                                }

                                dialog.cancel();
                            }
                        })
                        .setNegativeButton("Từ Chối", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                if (mListener != null) {
                                    response.setStatus("CANCELLED");
                                    mListener.onItemClick(response);
                                }

                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.setTitle("Thông Báo");
                alert.show();
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
        TextView tvTitle;
        TextView tvDesc;
        TextView tvDate;

        public PetViewHolder(View itemView) {
            super(itemView);
            imgAvatar = itemView.findViewById(R.id.imgAvatar);
            imgCard = itemView.findViewById(R.id.imgCard);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDesc = itemView.findViewById(R.id.tvDesc);
            tvDate = itemView.findViewById(R.id.tvDate);
        }
    }

}

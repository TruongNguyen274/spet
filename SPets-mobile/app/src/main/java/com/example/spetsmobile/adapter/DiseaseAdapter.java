package com.example.spetsmobile.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spetsmobile.R;
import com.example.spetsmobile.model.response.DiseaseResponse;
import com.example.spetsmobile.model.response.PrecautionResponse;

import java.util.List;

public class DiseaseAdapter extends RecyclerView.Adapter<DiseaseAdapter.PetViewHolder> {

    private Context context;
    private List<DiseaseResponse> responseList;

    public DiseaseAdapter(Context context, List<DiseaseResponse> diseaseResponseList) {
        this.context = context;
        this.responseList = diseaseResponseList;
    }

    @NonNull
    @Override
    public DiseaseAdapter.PetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_disease, parent, false);
        return new DiseaseAdapter.PetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiseaseAdapter.PetViewHolder holder, int position) {
        DiseaseResponse response = responseList.get(position);

        // Hiển thị thông tin
        holder.tvName.setText(response.getName());
        holder.tvDesc.setText(response.getDescription());
        holder.imgCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(v, response);
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

    // Phương thức để cập nhật danh sách tình trạng bệnh
    public void setDataList(List<DiseaseResponse> responseList) {
        this.responseList = responseList;
        notifyDataSetChanged();
    }

    private void openDialog(View view, DiseaseResponse response) {
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getRootView().getContext());
        View dialogView = LayoutInflater.from(view.getRootView().getContext()).inflate(R.layout.dialog_disease, null);
        // set dữ liệu
        TextView tvDiseaseName = dialogView.findViewById(R.id.tvDiseaseName);
        tvDiseaseName.setText("Loại Bệnh : " + response.getName());

        TextView tvDiseaseDesc = dialogView.findViewById(R.id.tvDiseaseDesc);
        tvDiseaseDesc.setText("- " + response.getDescription());

        StringBuilder precaution = new StringBuilder();
        for (PrecautionResponse precautionResponse : response.getPrecautionResponseList()) {
            precaution.append("- ")
                    .append(precautionResponse.getName())
                    .append("\n");
        }

        TextView tvPrecaution = dialogView.findViewById(R.id.tvPrecaution);
        tvPrecaution.setText(precaution);
        builder.setPositiveButton("Xác Nhận", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Xử lý khi nhấn nút Xác Nhận
                dialog.dismiss(); // Dismiss AlertDialog
            }
        });
        builder.setView(dialogView);
        builder.setCancelable(false);
        builder.show();
    }

    public class PetViewHolder extends RecyclerView.ViewHolder {

        ImageView imgCard;

        TextView tvName;

        TextView tvDesc;

        public PetViewHolder(View itemView) {
            super(itemView);
            imgCard = itemView.findViewById(R.id.imgCard);
            tvName = itemView.findViewById(R.id.tvName);
            tvDesc = itemView.findViewById(R.id.tvDesc);
        }
    }

}

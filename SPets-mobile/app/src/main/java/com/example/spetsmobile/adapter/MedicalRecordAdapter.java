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
import com.example.spetsmobile.activity.MedicalRecordFormActivity;
import com.example.spetsmobile.api.APIClient;
import com.example.spetsmobile.model.response.MedicalRecordResponse;
import com.example.spetsmobile.util.ConstantUtil;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MedicalRecordAdapter extends RecyclerView.Adapter<MedicalRecordAdapter.PetViewHolder> {

    private Context context;
    private List<MedicalRecordResponse> responseList;


    public MedicalRecordAdapter(Context context, List<MedicalRecordResponse> responseList) {
        this.context = context;
        this.responseList = responseList;
    }

    @NonNull
    @Override
    public MedicalRecordAdapter.PetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_medical_record, parent, false);
        return new MedicalRecordAdapter.PetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicalRecordAdapter.PetViewHolder holder, int position) {
        MedicalRecordResponse response = responseList.get(position);

        // Hiển thị thông tin
        holder.tvDiagnosis.setText(response.getDiagnosis());
        holder.tvAppointmentDate.setText(response.getAppointmentDate());
        holder.tvPet.setText(response.getPet().getName());
        holder.tvPrescription.setText(response.getPrescription());
        String imageURL = APIClient.HOST_URL + "/resources/file" + response.getPet().getAvatar();
        Picasso.get().load(imageURL).placeholder(R.drawable.icon_picture).into(holder.imgAvatar);

        holder.imgCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConstantUtil.setMedicalRecordResponse(responseList.get(position));

                Intent intent = new Intent(context, MedicalRecordFormActivity.class);
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

    public void setDataList(List<MedicalRecordResponse> responseList) {
        this.responseList = responseList;
        notifyDataSetChanged();
    }

    public class PetViewHolder extends RecyclerView.ViewHolder {

        ImageView imgAvatar;
        ImageView imgCard;
        TextView tvDiagnosis;
        TextView tvAppointmentDate;
        TextView tvPet;
        TextView tvPrescription;

        public PetViewHolder(View itemView) {
            super(itemView);
            imgAvatar = itemView.findViewById(R.id.imgAvatar);
            imgCard = itemView.findViewById(R.id.imgCard);
            tvDiagnosis = itemView.findViewById(R.id.tvDiagnosis);
            tvAppointmentDate = itemView.findViewById(R.id.tvAppointmentDate);
            tvPet = itemView.findViewById(R.id.tvPet);
            tvPrescription = itemView.findViewById(R.id.tvPrescription);
        }
    }
}

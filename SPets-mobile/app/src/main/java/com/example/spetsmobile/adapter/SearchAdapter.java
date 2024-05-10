package com.example.spetsmobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spetsmobile.R;
import com.example.spetsmobile.model.response.SymptomResponse;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.PetViewHolder> implements Filterable {

    private Context context;
    private List<SymptomResponse> responseList;
    private List<SymptomResponse> responseListFilter;

    public SearchAdapter(Context context, List<SymptomResponse> symptomResponseList) {
        this.context = context;
        this.responseList = symptomResponseList;
        this.responseListFilter = new ArrayList<>(symptomResponseList);;
    }

    @NonNull
    @Override
    public SearchAdapter.PetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search, parent, false);
        return new SearchAdapter.PetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.PetViewHolder holder, int position) {
        SymptomResponse response = responseList.get(position);

        // Hiển thị thông tin
        holder.cbName.setText(response.getName());
        holder.cbName.setOnCheckedChangeListener((buttonView, isChecked) -> {
            response.setChecked(isChecked);
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
    public void setDataList(List<SymptomResponse> responseList) {
        this.responseList = responseList;
        this.responseListFilter = responseList;
        notifyDataSetChanged();
    }

    public List<String> getSelectedItems() {
        List<String> selectedItems = new ArrayList<>();
        for (SymptomResponse model : responseList) {
            if (model.isChecked()) {
                selectedItems.add(String.valueOf(model.getId()));
            }
        }
        return selectedItems;
    }

    @Override
    public Filter getFilter() {
        return dataFilter;
    }

    private Filter dataFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<SymptomResponse> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(responseListFilter);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (SymptomResponse item : responseListFilter) {
                    if (item.getName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            responseList = (List<SymptomResponse>) results.values;
            notifyDataSetChanged();
        }
    };


    public class PetViewHolder extends RecyclerView.ViewHolder {

        ImageView imgCard;

        CheckBox cbName;

        public PetViewHolder(View itemView) {
            super(itemView);
            imgCard = itemView.findViewById(R.id.imgCard);
            cbName = itemView.findViewById(R.id.cbName);
        }
    }

}
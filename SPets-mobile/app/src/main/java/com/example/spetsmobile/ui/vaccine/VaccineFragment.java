package com.example.spetsmobile.ui.vaccine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spetsmobile.R;
import com.example.spetsmobile.adapter.VaccineAdapter;
import com.example.spetsmobile.databinding.FragmentPetBinding;
import com.example.spetsmobile.model.response.VaccineResponse;

import java.util.ArrayList;
import java.util.List;

public class VaccineFragment extends Fragment {

    private FragmentPetBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        VaccineViewModel vaccineViewModel = new ViewModelProvider(this).get(VaccineViewModel.class);

        binding = FragmentPetBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Kết nối RecyclerView
        RecyclerView recyclerView = root.findViewById(R.id.recyclerView);

        // Tạo Adapter để hiển thị danh sách
        VaccineAdapter adapter = new VaccineAdapter(getContext(), new ArrayList<>());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Kết nối LiveData từ ViewModel với Adapter
        vaccineViewModel.getList().observe(getViewLifecycleOwner(), new Observer<List<VaccineResponse>>() {
            @Override
            public void onChanged(List<VaccineResponse> responseList) {
                adapter.setDataList(responseList);
            }
        });

        // Load danh sách thú cưng
        vaccineViewModel.fetchData();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
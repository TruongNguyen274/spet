package com.example.spetsmobile.ui.hospital;

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
import com.example.spetsmobile.adapter.HospitalAdapter;
import com.example.spetsmobile.databinding.FragmentHospitalBinding;
import com.example.spetsmobile.model.response.HospitalResponse;

import java.util.ArrayList;
import java.util.List;

public class HospitalFragment extends Fragment {

    private FragmentHospitalBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HospitalViewModel hospitalViewModel =
                new ViewModelProvider(this).get(HospitalViewModel.class);

        binding = FragmentHospitalBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Kết nối RecyclerView
        RecyclerView recyclerView = root.findViewById(R.id.recyclerView);

        // Tạo Adapter để hiển thị danh sách
        HospitalAdapter adapter = new HospitalAdapter(getContext(), new ArrayList<>());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Kết nối LiveData từ ViewModel với Adapter
        hospitalViewModel.getList().observe(getViewLifecycleOwner(), new Observer<List<HospitalResponse>>() {
            @Override
            public void onChanged(List<HospitalResponse> responseList) {
                adapter.setDataList(responseList);
            }
        });

        // Load danh sách thú cưng
        hospitalViewModel.fetchData();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
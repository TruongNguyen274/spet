package com.example.spetsmobile.ui.pet;

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
import com.example.spetsmobile.adapter.PetAdapter;
import com.example.spetsmobile.databinding.FragmentPetBinding;
import com.example.spetsmobile.model.response.PetResponse;

import java.util.ArrayList;
import java.util.List;

public class PetFragment extends Fragment {

    private FragmentPetBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PetViewModel petViewModel = new ViewModelProvider(this).get(PetViewModel.class);

        binding = FragmentPetBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Kết nối RecyclerView
        RecyclerView recyclerView = root.findViewById(R.id.recyclerView);

        // Tạo Adapter để hiển thị danh sách
        PetAdapter adapter = new PetAdapter(getContext(), new ArrayList<>());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Kết nối LiveData từ ViewModel với Adapter
        petViewModel.getList().observe(getViewLifecycleOwner(), new Observer<List<PetResponse>>() {
            @Override
            public void onChanged(List<PetResponse> petList) {
                adapter.setDataList(petList);
            }
        });

        // Load danh sách thú cưng
        petViewModel.fetchData();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
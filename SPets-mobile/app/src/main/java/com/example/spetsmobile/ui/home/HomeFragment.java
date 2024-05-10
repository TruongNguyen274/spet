package com.example.spetsmobile.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.spetsmobile.R;
import com.example.spetsmobile.activity.SearchActivity;
import com.example.spetsmobile.databinding.FragmentHomeBinding;
import com.example.spetsmobile.util.ConstantUtil;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView tvHello = root.findViewById(R.id.tvHello);
        tvHello.setText("Xin chào, " + ConstantUtil.getAuth().getFullname());

        final CardView cvPet = root.findViewById(R.id.cvPet);
        cvPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.nav_pet);
            }
        });

        final CardView cvVaccine = root.findViewById(R.id.cvVaccine);
        cvVaccine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.nav_vaccine);
            }
        });

        final CardView cvHospital = root.findViewById(R.id.cvHospital);
        cvHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.nav_hospital);
//                Intent intent = new Intent(getActivity(), HospitalActivity.class);
//                getActivity().startActivity(intent);
            }
        });

        final CardView cvSearch = root.findViewById(R.id.cvSearch);
        cvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                getActivity().startActivity(intent);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
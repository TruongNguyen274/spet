package com.example.spetsmobile.ui.hospital;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.spetsmobile.itf.HospitalInterface;
import com.example.spetsmobile.model.response.ApiReponse;
import com.example.spetsmobile.model.response.HospitalResponse;
import com.example.spetsmobile.restapi.HospitalAPI;

import java.util.List;
import java.util.Map;

public class HospitalViewModel extends ViewModel implements HospitalInterface {

    private MutableLiveData<List<HospitalResponse>> listMutableLiveData = new MutableLiveData<>();

    public LiveData<List<HospitalResponse>> getList() {
        return listMutableLiveData;
    }

    public void fetchData() {
        HospitalAPI hospitalAPI = new HospitalAPI(HospitalViewModel.this);
        hospitalAPI.findAllHospital();
    }

    @Override
    public void onSuccess(String type, ApiReponse apiReponse) {
        List<HospitalResponse> responseList = (List<HospitalResponse>) apiReponse.getPayload();
        listMutableLiveData.postValue(responseList);
    }

    @Override
    public void onError(String type, Map<String, String> error) {

    }

}
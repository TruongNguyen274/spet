package com.example.spetsmobile.ui.vaccine;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.spetsmobile.itf.VaccineInterface;
import com.example.spetsmobile.model.response.ApiReponse;
import com.example.spetsmobile.model.response.VaccineResponse;
import com.example.spetsmobile.restapi.VaccineAPI;
import com.example.spetsmobile.util.ConstantUtil;

import java.util.List;

public class VaccineViewModel extends ViewModel implements VaccineInterface {

    private MutableLiveData<List<VaccineResponse>> listMutableLiveData = new MutableLiveData<>();

    public LiveData<List<VaccineResponse>> getList() {
        return listMutableLiveData;
    }

    public void fetchData() {
        VaccineAPI api = new VaccineAPI(VaccineViewModel.this);
        api.findAllPetsByOwner(ConstantUtil.getAuth().getId());
    }

    @Override
    public void onSuccess(String type, ApiReponse apiReponse) {
        List<VaccineResponse> responseList = (List<VaccineResponse>) apiReponse.getPayload();
        listMutableLiveData.postValue(responseList);
    }

    @Override
    public void onError(String type, String error) {

    }

}
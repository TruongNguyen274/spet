package com.example.spetsmobile.ui.pet;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.spetsmobile.itf.PetInterface;
import com.example.spetsmobile.model.response.ApiReponse;
import com.example.spetsmobile.model.response.PetResponse;
import com.example.spetsmobile.restapi.PetAPI;
import com.example.spetsmobile.util.ConstantUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PetViewModel extends ViewModel implements PetInterface {

    private MutableLiveData<List<PetResponse>> listMutableLiveData = new MutableLiveData<>();

    public LiveData<List<PetResponse>> getList() {
        return listMutableLiveData;
    }

    public void fetchData() {
        PetAPI api = new PetAPI(PetViewModel.this);
        api.findAllPetsByOwner(ConstantUtil.getAuth().getId());
    }

    @Override
    public void onSuccess(String type, ApiReponse apiReponse) {
        List<PetResponse> petResponseList = (List<PetResponse>) apiReponse.getPayload();
        listMutableLiveData.postValue(petResponseList);
    }

    @Override
    public void onError(String type, Map<String, String> error) {

    }

}
package com.example.spetsmobile.restapi;

import com.example.spetsmobile.api.APIClient;
import com.example.spetsmobile.api.APIService;
import com.example.spetsmobile.itf.HealthRecordInterface;
import com.example.spetsmobile.model.request.HealthRecordResquest;
import com.example.spetsmobile.model.response.ApiReponse;
import com.example.spetsmobile.model.response.HealthRecordResponse;
import com.example.spetsmobile.util.ConstantUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HealthRecordAPI {

    private HealthRecordInterface healthRecordInterface = null;

    private APIService apiService;

    public HealthRecordAPI(HealthRecordInterface healthRecordInterface) {
        this.healthRecordInterface = healthRecordInterface;
        apiService = APIClient.getAPIService();
    }

    public void findAllHealthRecordByPet(long petId) {
        String token = "Bearer " + ConstantUtil.getAccessToken();
        apiService.findAllHealthRecordByPet(token, petId).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<ApiReponse<List<HealthRecordResponse>>> call, Response<ApiReponse<List<HealthRecordResponse>>> response) {
                ApiReponse apiReponse = response.body();
                if (apiReponse == null) {
                    healthRecordInterface.onError("HEALTH_ERROR", null);
                } else {
                    healthRecordInterface.onSuccess("HEALTH_LIST", apiReponse);
                }
            }

            @Override
            public void onFailure(Call<ApiReponse<List<HealthRecordResponse>>> call, Throwable t) {
                Map<String, String> error = new HashMap<>();
                error.put("ERROR", t.getMessage());

                healthRecordInterface.onError("HEALTH_ERROR", error);
            }
        });
    }
    public void saveHealthRecord(HealthRecordResquest healthRecordResquest) {
        String token = "Bearer " + ConstantUtil.getAccessToken();
        apiService.saveHealthRecord(token, healthRecordResquest).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<ApiReponse<HealthRecordResponse>> call, Response<ApiReponse<HealthRecordResponse>> response) {
                ApiReponse apiReponse = response.body();
                if (apiReponse == null) {
                    healthRecordInterface.onError("HEALTH_SAVE", null);
                } else {
                    healthRecordInterface.onSuccess("HEALTH_SAVE", apiReponse);
                }
            }

            @Override
            public void onFailure(Call<ApiReponse<HealthRecordResponse>> call, Throwable t) {
                Map<String, String> error = new HashMap<>();
                error.put("ERROR", t.getMessage());

                healthRecordInterface.onError("HEALTH_SAVE", error);
            }
        });
    }

}

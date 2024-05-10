package com.example.spetsmobile.restapi;

import com.example.spetsmobile.api.APIClient;
import com.example.spetsmobile.api.APIService;
import com.example.spetsmobile.itf.MedicalRecordInterface;
import com.example.spetsmobile.model.request.MedicalRecordRequest;
import com.example.spetsmobile.model.response.ApiReponse;
import com.example.spetsmobile.model.response.MedicalRecordResponse;
import com.example.spetsmobile.util.ConstantUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MedicalRecordAPI {

    private MedicalRecordInterface medicalRecordInterface = null;

    private APIService apiService;

    public MedicalRecordAPI(MedicalRecordInterface medicalRecordInterface) {
        this.medicalRecordInterface = medicalRecordInterface;
        apiService = APIClient.getAPIService();
    }

    public void findByPet(long petId) {
        String token = "Bearer " + ConstantUtil.getAccessToken();

        apiService.findAllMedicalRecordByPet(token, petId).enqueue(new Callback<>() {

            @Override
            public void onResponse(Call<ApiReponse<List<MedicalRecordResponse>>> call, Response<ApiReponse<List<MedicalRecordResponse>>> response) {
                ApiReponse apiReponse = response.body();
                if (apiReponse == null) {
                    medicalRecordInterface.onError("MEDICAL_RECORD_ERROR", null);
                } else {
                    medicalRecordInterface.onSuccess("MEDICAL_RECORD_PET", apiReponse);
                }
            }

            @Override
            public void onFailure(Call<ApiReponse<List<MedicalRecordResponse>>> call, Throwable t) {
                Map<String, String> error = new HashMap<>();
                error.put("ERROR", t.getMessage());

                medicalRecordInterface.onError("MEDICAL_RECORD_ERROR", error);
            }
        });
    }

    public void findByHospital(long hospitalId) {
        String token = "Bearer " + ConstantUtil.getAccessToken();

        apiService.findAllMedicalRecordByHospital(token, hospitalId).enqueue(new Callback<>() {

            @Override
            public void onResponse(Call<ApiReponse<List<MedicalRecordResponse>>> call, Response<ApiReponse<List<MedicalRecordResponse>>> response) {
                ApiReponse apiReponse = response.body();
                if (apiReponse == null) {
                    medicalRecordInterface.onError("MEDICAL_RECORD_ERROR", null);
                } else {
                    medicalRecordInterface.onSuccess("MEDICAL_RECORD_HOSPITAL", apiReponse);
                }
            }

            @Override
            public void onFailure(Call<ApiReponse<List<MedicalRecordResponse>>> call, Throwable t) {
                Map<String, String> error = new HashMap<>();
                error.put("ERROR", t.getMessage());

                medicalRecordInterface.onError("MEDICAL_RECORD_ERROR", error);
            }
        });
    }

    public void saveMedicalRecord(MedicalRecordRequest medicalRecordRequest) {
        String token = "Bearer " + ConstantUtil.getAccessToken();

        apiService.medicalRecordSave(token, medicalRecordRequest).enqueue(new Callback<>() {

            @Override
            public void onResponse(Call<ApiReponse<MedicalRecordResponse>> call, Response<ApiReponse<MedicalRecordResponse>> response) {
                ApiReponse apiReponse = response.body();
                if (apiReponse == null) {
                    medicalRecordInterface.onError("MEDICAL_RECORD_ERROR", null);
                } else {
                    medicalRecordInterface.onSuccess("MEDICAL_RECORD_SAVE", apiReponse);
                }
            }

            @Override
            public void onFailure(Call<ApiReponse<MedicalRecordResponse>> call, Throwable t) {
                Map<String, String> error = new HashMap<>();
                error.put("ERROR", t.getMessage());

                medicalRecordInterface.onError("MEDICAL_RECORD_ERROR", error);
            }
        });
    }

}

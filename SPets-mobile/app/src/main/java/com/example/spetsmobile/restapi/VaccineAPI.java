package com.example.spetsmobile.restapi;

import com.example.spetsmobile.api.APIClient;
import com.example.spetsmobile.api.APIService;
import com.example.spetsmobile.itf.VaccineInterface;
import com.example.spetsmobile.model.request.VaccineRequest;
import com.example.spetsmobile.model.response.ApiReponse;
import com.example.spetsmobile.model.response.VaccineResponse;
import com.example.spetsmobile.util.ConstantUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VaccineAPI {

    private VaccineInterface vaccineInterface = null;

    private APIService apiService;

    public VaccineAPI(VaccineInterface vaccineInterface) {
        this.vaccineInterface = vaccineInterface;
        apiService = APIClient.getAPIService();
    }

    public void findAllPetsByOwner(long ownerId) {
        String token = "Bearer " + ConstantUtil.getAccessToken();
        apiService.findAllVaccinesByOwner(token, ownerId).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<ApiReponse<List<VaccineResponse>>> call, Response<ApiReponse<List<VaccineResponse>>> response) {
                ApiReponse apiReponse = response.body();
                if (apiReponse == null) {
                    vaccineInterface.onError("VACCINE_ERROR", null);
                } else {
                    vaccineInterface.onSuccess("VACCINE_LIST", apiReponse);
                }
            }

            @Override
            public void onFailure(Call<ApiReponse<List<VaccineResponse>>> call, Throwable t) {
                vaccineInterface.onError("VACCINE_ERROR", t.getMessage());
            }
        });
    }

    public void findAllVaccinesByPet(long petId) {
        String token = "Bearer " + ConstantUtil.getAccessToken();
        apiService.findAllVaccinesByPet(token, petId).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<ApiReponse<List<VaccineResponse>>> call, Response<ApiReponse<List<VaccineResponse>>> response) {
                ApiReponse apiReponse = response.body();
                if (apiReponse == null) {
                    vaccineInterface.onError("VACCINE_ERROR", null);
                } else {
                    vaccineInterface.onSuccess("VACCINE_LIST", apiReponse);
                }
            }

            @Override
            public void onFailure(Call<ApiReponse<List<VaccineResponse>>> call, Throwable t) {
                vaccineInterface.onError("VACCINE_ERROR", t.getMessage());
            }
        });
    }

    public void saveVaccine(VaccineRequest vaccineRequest) {
        String token = "Bearer " + ConstantUtil.getAccessToken();
        apiService.saveVaccine(token, vaccineRequest).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<ApiReponse<VaccineResponse>> call, Response<ApiReponse<VaccineResponse>> response) {
                ApiReponse apiReponse = response.body();
                if (apiReponse == null) {
                    vaccineInterface.onError("VACCINE_ERROR", null);
                } else {
                    vaccineInterface.onSuccess("VACCINE_SAVE", apiReponse);
                }
            }

            @Override
            public void onFailure(Call<ApiReponse<VaccineResponse>> call, Throwable t) {
                vaccineInterface.onError("VACCINE_ERROR", t.getMessage());
            }
        });
    }

}

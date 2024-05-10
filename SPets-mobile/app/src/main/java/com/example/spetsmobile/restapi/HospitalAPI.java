package com.example.spetsmobile.restapi;

import com.example.spetsmobile.api.APIClient;
import com.example.spetsmobile.api.APIService;
import com.example.spetsmobile.itf.HospitalInterface;
import com.example.spetsmobile.model.request.HospitalRequest;
import com.example.spetsmobile.model.response.ApiReponse;
import com.example.spetsmobile.model.response.HospitalResponse;
import com.example.spetsmobile.model.response.StatusEnum;
import com.example.spetsmobile.util.ConstantUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HospitalAPI {

    private HospitalInterface hospitalInterface = null;

    private APIService apiService;

    public HospitalAPI(HospitalInterface hospitalInterface) {
        this.hospitalInterface = hospitalInterface;
        apiService = APIClient.getAPIService();
    }

    public void findAllHospital() {
        String token = "Bearer " + ConstantUtil.getAccessToken();
        apiService.findAllHospital(token).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<ApiReponse<List<HospitalResponse>>> call, Response<ApiReponse<List<HospitalResponse>>> response) {
                ApiReponse apiReponse = response.body();
                if (apiReponse == null) {
                    hospitalInterface.onError("HOSPITAL_ERROR", null);
                } else {
                    hospitalInterface.onSuccess("HOSPITAL_LIST", apiReponse);
                }
            }

            @Override
            public void onFailure(Call<ApiReponse<List<HospitalResponse>>> call, Throwable t) {
                Map<String, String> error = new HashMap<>();
                error.put("ERROR", t.getMessage());

                hospitalInterface.onError("HOSPITAL_ERROR", error);
            }
        });
    }

    public void findByAccount(long accountId) {
        String token = "Bearer " + ConstantUtil.getAccessToken();
        apiService.findByAccount(token, accountId).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<ApiReponse<HospitalResponse>> call, Response<ApiReponse<HospitalResponse>> response) {
                ApiReponse apiReponse = response.body();
                if (apiReponse == null) {
                    hospitalInterface.onError("HOSPITAL_ERROR", null);
                } else {
                    hospitalInterface.onSuccess("HOSPITAL_ACCOUNT", apiReponse);
                }
            }

            @Override
            public void onFailure(Call<ApiReponse<HospitalResponse>> call, Throwable t) {
                Map<String, String> error = new HashMap<>();
                error.put("ERROR", t.getMessage());

                hospitalInterface.onError("HOSPITAL_ERROR", error);
            }
        });
    }

    public void saveHospital(HospitalRequest hospitalRequest) {
        String token = "Bearer " + ConstantUtil.getAccessToken();
        apiService.saveHospital(token, hospitalRequest).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<ApiReponse<HospitalResponse>> call, Response<ApiReponse<HospitalResponse>> response) {
                ApiReponse apiReponse = response.body();
                if (apiReponse == null) {
                    hospitalInterface.onError("HOSPITAL_ERROR", null);
                } else {
                    if (apiReponse.getStatus().equals(StatusEnum.ERROR)) {
                        Map<String, String> error = apiReponse.getError();
                        hospitalInterface.onError("HOSPITAL_ERROR", error);
                    } else {
                        hospitalInterface.onSuccess("HOSPITAL_SAVE", apiReponse);
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiReponse<HospitalResponse>> call, Throwable t) {
                Map<String, String> error = new HashMap<>();
                error.put("ERROR", t.getMessage());

                hospitalInterface.onError("HOSPITAL_ERROR", error);
            }
        });
    }

}

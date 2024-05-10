package com.example.spetsmobile.restapi;

import com.example.spetsmobile.api.APIClient;
import com.example.spetsmobile.api.APIService;
import com.example.spetsmobile.itf.SearchInterface;
import com.example.spetsmobile.model.request.SearchRequest;
import com.example.spetsmobile.model.response.ApiReponse;
import com.example.spetsmobile.model.response.DiseaseResponse;
import com.example.spetsmobile.model.response.SymptomResponse;
import com.example.spetsmobile.util.ConstantUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchAPI {

    private SearchInterface searchInterface = null;

    private APIService apiService;

    public SearchAPI(SearchInterface searchInterface) {
        this.searchInterface = searchInterface;
        apiService = APIClient.getAPIService();
    }

    public void findAllSymptom() {
        String token = "Bearer " + ConstantUtil.getAccessToken();
        apiService.findAllSymptom(token).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<ApiReponse<List<SymptomResponse>>> call, Response<ApiReponse<List<SymptomResponse>>> response) {
                ApiReponse apiReponse = response.body();
                if (apiReponse == null) {
                    searchInterface.onError("SYMPTOM_ERROR", null);
                } else {
                    searchInterface.onSuccess("SYMPTOM_LIST", apiReponse);
                }
            }

            @Override
            public void onFailure(Call<ApiReponse<List<SymptomResponse>>> call, Throwable t) {
                Map<String, String> error = new HashMap<>();
                error.put("ERROR", t.getMessage());

                searchInterface.onError("SYMPTOM_ERROR", error);
            }
        });
    }

    public void search(SearchRequest searchRequest) {
        String token = "Bearer " + ConstantUtil.getAccessToken();
        apiService.search(token, searchRequest).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<ApiReponse<List<DiseaseResponse>>> call, Response<ApiReponse<List<DiseaseResponse>>> response) {
                ApiReponse apiReponse = response.body();
                if (apiReponse == null) {
                    searchInterface.onError("DISEASE_ERROR", null);
                } else {
                    searchInterface.onSuccess("DISEASE_LIST", apiReponse);
                }
            }

            @Override
            public void onFailure(Call<ApiReponse<List<DiseaseResponse>>> call, Throwable t) {
                Map<String, String> error = new HashMap<>();
                error.put("ERROR", t.getMessage());

                searchInterface.onError("DISEASE_ERROR", error);
            }
        });
    }

}

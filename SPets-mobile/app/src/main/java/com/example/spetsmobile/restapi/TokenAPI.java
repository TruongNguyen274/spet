package com.example.spetsmobile.restapi;

import com.example.spetsmobile.api.APIClient;
import com.example.spetsmobile.api.APIService;
import com.example.spetsmobile.model.request.AccountResquest;
import com.example.spetsmobile.model.response.ApiReponse;
import com.example.spetsmobile.model.response.AuthResponse;
import com.example.spetsmobile.util.ConstantUtil;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TokenAPI {

    private APIService apiService;

    public TokenAPI() {
        apiService = APIClient.getAPIService();
    }

    public void updateToken(AccountResquest accountResquest) {
        String token = "bearer " + ConstantUtil.getAccessToken();
        apiService.saveToken(token, accountResquest).enqueue(new Callback<ApiReponse<AuthResponse>>() {
            @Override
            public void onResponse(Call<ApiReponse<AuthResponse>> call, Response<ApiReponse<AuthResponse>> response) {

            }

            @Override
            public void onFailure(Call<ApiReponse<AuthResponse>> call, Throwable t) {

            }
        });
    }

}

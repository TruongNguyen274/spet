package com.example.spetsmobile.restapi;

import android.graphics.Bitmap;

import com.example.spetsmobile.api.APIClient;
import com.example.spetsmobile.api.APIService;
import com.example.spetsmobile.itf.PetInterface;
import com.example.spetsmobile.model.request.PetRequest;
import com.example.spetsmobile.model.response.ApiReponse;
import com.example.spetsmobile.model.response.PetResponse;
import com.example.spetsmobile.model.response.StatusEnum;
import com.example.spetsmobile.util.ConstantUtil;
import com.google.gson.annotations.SerializedName;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PetAPI {

    private PetInterface petInterface = null;

    private APIService apiService;

    public PetAPI(PetInterface petInterface) {
        this.petInterface = petInterface;
        apiService = APIClient.getAPIService();
    }

    public void findAllPetsByOwner(long ownerId) {
        String token = "Bearer " + ConstantUtil.getAccessToken();
        apiService.findAllPetsByOwner(token, ownerId).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<ApiReponse<List<PetResponse>>> call, Response<ApiReponse<List<PetResponse>>> response) {
                ApiReponse apiReponse = response.body();
                if (apiReponse == null) {
                    petInterface.onError("PET_ERROR", null);
                } else {
                    petInterface.onSuccess("PET_LIST", apiReponse);
                }
            }

            @Override
            public void onFailure(Call<ApiReponse<List<PetResponse>>> call, Throwable t) {
                Map<String, String> error = new HashMap<>();
                error.put("ERROR", t.getMessage());

                petInterface.onError("PET_ERROR", error);
            }
        });
    }

    public void petSave(PetRequest request) {
        String token = "Bearer " + ConstantUtil.getAccessToken();

        apiService.petSave(token, request).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<ApiReponse<PetResponse>> call, Response<ApiReponse<PetResponse>> response) {
                ApiReponse apiReponse = response.body();
                if (apiReponse == null) {
                    petInterface.onError("PET_ERROR", null);
                } else {
                    if (apiReponse.getStatus().equals(StatusEnum.ERROR)) {
                        Map<String, String> error = apiReponse.getError();
                        petInterface.onError("PET_ERROR", error);
                    } else {
                        petInterface.onSuccess("PET_SAVE", apiReponse);
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiReponse<PetResponse>> call, Throwable t) {
                Map<String, String> error = new HashMap<>();
                error.put("ERROR", t.getMessage());

                petInterface.onError("PET_ERROR", error);
            }
        });
    }

    public void petSavePhoto(long petId, byte[] byteArray) {
        String token = "Bearer " + ConstantUtil.getAccessToken();

        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), byteArray);
        MultipartBody.Part avatarMulPart = MultipartBody.Part.createFormData("avatarMul", "avatar.png", requestFile);

        apiService.petSavePhoto(token, petId, avatarMulPart).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<ApiReponse<PetResponse>> call, Response<ApiReponse<PetResponse>> response) {
                ApiReponse apiReponse = response.body();
                if (apiReponse == null) {
                    petInterface.onError("PET_ERROR", null);
                } else {
                    if (apiReponse.getStatus().equals(StatusEnum.ERROR)) {
                        Map<String, String> error = apiReponse.getError();
                        petInterface.onError("PET_ERROR", error);
                    } else {
                        petInterface.onSuccess("PET_PHOTO", apiReponse);
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiReponse<PetResponse>> call, Throwable t) {
                Map<String, String> error = new HashMap<>();
                error.put("ERROR", t.getMessage());

                petInterface.onError("PET_ERROR", error);
            }
        });
    }

}

package com.example.spetsmobile.restapi;

import com.example.spetsmobile.api.APIClient;
import com.example.spetsmobile.api.APIService;
import com.example.spetsmobile.itf.MediaInterface;
import com.example.spetsmobile.model.response.ApiReponse;
import com.example.spetsmobile.model.response.MediaResponse;
import com.example.spetsmobile.model.response.PetResponse;
import com.example.spetsmobile.model.response.StatusEnum;
import com.example.spetsmobile.util.ConstantUtil;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MediaAPI {

    private MediaInterface mediaInterface = null;

    private APIService apiService;

    public MediaAPI(MediaInterface mediaInterface) {
        this.mediaInterface = mediaInterface;
        apiService = APIClient.getAPIService();
    }

    public void findAllMediaByPet(long petId) {
        String token = "Bearer " + ConstantUtil.getAccessToken();
        apiService.findAllMediaByPet(token, petId).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<ApiReponse<List<MediaResponse>>> call, Response<ApiReponse<List<MediaResponse>>> response) {
                ApiReponse apiReponse = response.body();
                if (apiReponse == null) {
                    mediaInterface.onError("MEDIA_ERROR", null);
                } else {
                    mediaInterface.onSuccess("MEDIA_LIST", apiReponse);
                }
            }

            @Override
            public void onFailure(Call<ApiReponse<List<MediaResponse>>> call, Throwable t) {
                mediaInterface.onError("MEDIA_ERROR", t.getMessage());
            }
        });
    }

    public void petSaveMedia(long petId, byte[] byteArray) {
        String token = "Bearer " + ConstantUtil.getAccessToken();

        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), byteArray);
        MultipartBody.Part avatarMulPart = MultipartBody.Part.createFormData("avatarMul", "avatar.png", requestFile);

        apiService.petSaveMedia(token, petId, avatarMulPart).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<ApiReponse<PetResponse>> call, Response<ApiReponse<PetResponse>> response) {
                ApiReponse apiReponse = response.body();
                if (apiReponse == null) {
                    mediaInterface.onError("MEDIA_ERROR", null);
                } else {
                    if (apiReponse.getStatus().equals(StatusEnum.ERROR)) {
                        mediaInterface.onError("MEDIA_ERROR", null);
                    } else {
                        mediaInterface.onSuccess("MEDIA_SAVE", apiReponse);
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiReponse<PetResponse>> call, Throwable t) {
                mediaInterface.onError("MEDIA_ERROR", null);
            }
        });
    }

}

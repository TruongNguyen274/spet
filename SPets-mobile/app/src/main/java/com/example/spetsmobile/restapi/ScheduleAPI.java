package com.example.spetsmobile.restapi;

import com.example.spetsmobile.api.APIClient;
import com.example.spetsmobile.api.APIService;
import com.example.spetsmobile.itf.ScheduleInterface;
import com.example.spetsmobile.model.request.PetRequest;
import com.example.spetsmobile.model.request.ScheduleResquest;
import com.example.spetsmobile.model.response.ApiReponse;
import com.example.spetsmobile.model.response.PetResponse;
import com.example.spetsmobile.model.response.ScheduleResponse;
import com.example.spetsmobile.model.response.StatusEnum;
import com.example.spetsmobile.util.ConstantUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleAPI {

    private ScheduleInterface scheduleInterface = null;

    private APIService apiService;

    public ScheduleAPI(ScheduleInterface scheduleInterface) {
        this.scheduleInterface = scheduleInterface;
        apiService = APIClient.getAPIService();
    }

    public void findAllScheduleByPet(long petId) {
        String token = "Bearer " + ConstantUtil.getAccessToken();
        apiService.findAllScheduleByPet(token, petId).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<ApiReponse<List<ScheduleResponse>>> call, Response<ApiReponse<List<ScheduleResponse>>> response) {
                ApiReponse apiReponse = response.body();
                if (apiReponse == null) {
                    scheduleInterface.onError("PET_ERROR", null);
                } else {
                    scheduleInterface.onSuccess("PET_LIST", apiReponse);
                }
            }

            @Override
            public void onFailure(Call<ApiReponse<List<ScheduleResponse>>> call, Throwable t) {
                Map<String, String> error = new HashMap<>();
                error.put("ERROR", t.getMessage());

                scheduleInterface.onError("PET_ERROR", error);
            }
        });
    }

    public void findAllScheduleByAccount(long accountId) {
        String token = "Bearer " + ConstantUtil.getAccessToken();
        apiService.findAllScheduleByAccount(token, accountId).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<ApiReponse<List<ScheduleResponse>>> call, Response<ApiReponse<List<ScheduleResponse>>> response) {
                ApiReponse apiReponse = response.body();
                if (apiReponse == null) {
                    scheduleInterface.onError("PET_ERROR", null);
                } else {
                    scheduleInterface.onSuccess("PET_ACCOUNT", apiReponse);
                }
            }

            @Override
            public void onFailure(Call<ApiReponse<List<ScheduleResponse>>> call, Throwable t) {
                Map<String, String> error = new HashMap<>();
                error.put("ERROR", t.getMessage());

                scheduleInterface.onError("PET_ERROR", error);
            }
        });
    }

    public void scheduleSave(ScheduleResquest request) {
        String token = "Bearer " + ConstantUtil.getAccessToken();

        apiService.scheduleSave(token, request).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<ApiReponse<ScheduleResponse>> call, Response<ApiReponse<ScheduleResponse>> response) {
                ApiReponse apiReponse = response.body();
                if (apiReponse == null) {
                    scheduleInterface.onError("SCHEDULE_ERROR", null);
                } else {
                    if (apiReponse.getStatus().equals(StatusEnum.ERROR)) {
                        Map<String, String> error = apiReponse.getError();
                        scheduleInterface.onError("SCHEDULE_ERROR", error);
                    } else {
                        scheduleInterface.onSuccess("SCHEDULE_SAVE", apiReponse);
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiReponse<ScheduleResponse>> call, Throwable t) {
                Map<String, String> error = new HashMap<>();
                error.put("ERROR", t.getMessage());

                scheduleInterface.onError("SCHEDULE_ERROR", error);
            }
        });
    }

    public void scheduleDelete(long scheduleId) {
        String token = "Bearer " + ConstantUtil.getAccessToken();

        apiService.scheduleDelete(token, scheduleId).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<ApiReponse<ScheduleResponse>> call, Response<ApiReponse<ScheduleResponse>> response) {
                ApiReponse apiReponse = response.body();
                if (apiReponse == null) {
                    scheduleInterface.onError("SCHEDULE_ERROR", null);
                } else {
                    if (apiReponse.getStatus().equals(StatusEnum.ERROR)) {
                        Map<String, String> error = apiReponse.getError();
                        scheduleInterface.onError("SCHEDULE_ERROR", error);
                    } else {
                        scheduleInterface.onSuccess("SCHEDULE_DELETE", apiReponse);
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiReponse<ScheduleResponse>> call, Throwable t) {
                Map<String, String> error = new HashMap<>();
                error.put("ERROR", t.getMessage());

                scheduleInterface.onError("SCHEDULE_ERROR", error);
            }
        });
    }

}

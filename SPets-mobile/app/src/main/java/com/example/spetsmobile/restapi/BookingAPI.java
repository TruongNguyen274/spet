package com.example.spetsmobile.restapi;

import com.example.spetsmobile.api.APIClient;
import com.example.spetsmobile.api.APIService;
import com.example.spetsmobile.itf.BookingInterface;
import com.example.spetsmobile.model.request.BookingResquest;
import com.example.spetsmobile.model.response.ApiReponse;
import com.example.spetsmobile.model.response.BookingResponse;
import com.example.spetsmobile.model.response.PetResponse;
import com.example.spetsmobile.model.response.StatusEnum;
import com.example.spetsmobile.util.ConstantUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingAPI {

    private BookingInterface bookingInterface = null;

    private APIService apiService;

    public BookingAPI(BookingInterface bookingInterface) {
        this.bookingInterface = bookingInterface;
        apiService = APIClient.getAPIService();
    }

    public void findAllBookingByHospital(long hospitalId, String status) {
        String token = "Bearer " + ConstantUtil.getAccessToken();
        apiService.findAllBookingByHospital(token, hospitalId, status).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<ApiReponse<List<BookingResponse>>> call, Response<ApiReponse<List<BookingResponse>>> response) {
                ApiReponse apiReponse = response.body();
                if (apiReponse == null) {
                    bookingInterface.onError("BOOKING_ERROR", null);
                } else {
                    bookingInterface.onSuccess("BOOKING_LIST", apiReponse);
                }
            }

            @Override
            public void onFailure(Call<ApiReponse<List<BookingResponse>>> call, Throwable t) {
                Map<String, String> error = new HashMap<>();
                error.put("ERROR", t.getMessage());

                bookingInterface.onError("BOOKING_ERROR", error);
            }
        });
    }

    public void bookingSave(BookingResquest request) {
        String token = "Bearer " + ConstantUtil.getAccessToken();

        apiService.bookingSave(token, request).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<ApiReponse<BookingResponse>> call, Response<ApiReponse<BookingResponse>> response) {
                ApiReponse apiReponse = response.body();
                if (apiReponse == null) {
                    bookingInterface.onError("BOOKING_ERROR", null);
                } else {
                    if (apiReponse.getStatus().equals(StatusEnum.ERROR)) {
                        Map<String, String> error = apiReponse.getError();
                        bookingInterface.onError("BOOKING_ERROR", error);
                    } else {
                        bookingInterface.onSuccess("BOOKING_SAVE", apiReponse);
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiReponse<BookingResponse>> call, Throwable t) {
                Map<String, String> error = new HashMap<>();
                error.put("ERROR", t.getMessage());

                bookingInterface.onError("BOOKING_ERROR", error);
            }
        });
    }

    public void bookingStatus(BookingResquest request) {
        String token = "Bearer " + ConstantUtil.getAccessToken();

        apiService.bookingStatus(token, request).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<ApiReponse<BookingResponse>> call, Response<ApiReponse<BookingResponse>> response) {
                ApiReponse apiReponse = response.body();
                if (apiReponse == null) {
                    bookingInterface.onError("BOOKING_ERROR", null);
                } else {
                    if (apiReponse.getStatus().equals(StatusEnum.ERROR)) {
                        Map<String, String> error = apiReponse.getError();
                        bookingInterface.onError("BOOKING_ERROR", error);
                    } else {
                        bookingInterface.onSuccess("BOOKING_SHOW", apiReponse);
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiReponse<BookingResponse>> call, Throwable t) {
                Map<String, String> error = new HashMap<>();
                error.put("ERROR", t.getMessage());

                bookingInterface.onError("BOOKING_ERROR", error);
            }
        });
    }

}

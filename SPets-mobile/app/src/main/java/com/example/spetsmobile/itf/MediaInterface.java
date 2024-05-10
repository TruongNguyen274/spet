package com.example.spetsmobile.itf;

import com.example.spetsmobile.model.response.ApiReponse;

public interface MediaInterface {

    void onSuccess(String type, ApiReponse apiReponse);

    void onError(String type, String error);

}

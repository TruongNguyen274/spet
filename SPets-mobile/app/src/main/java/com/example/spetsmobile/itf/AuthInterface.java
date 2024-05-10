package com.example.spetsmobile.itf;

import com.example.spetsmobile.model.response.ApiReponse;

public interface AuthInterface {

    void onSuccess(String type, ApiReponse apiReponse);

    void onError(String type, String error);

}

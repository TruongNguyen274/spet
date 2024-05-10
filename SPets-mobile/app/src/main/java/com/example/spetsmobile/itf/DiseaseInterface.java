package com.example.spetsmobile.itf;

import com.example.spetsmobile.model.response.ApiReponse;

import java.util.Map;

public interface DiseaseInterface {

    void onSuccess(String type, ApiReponse apiReponse);

    void onError(String type, Map<String, String> error);
}

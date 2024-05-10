package com.example.spetsmobile.model.response;

import java.util.HashMap;
import java.util.Map;

public class ApiReponse<T> {

    private StatusEnum status;
    private T payload;
    private Map<String, String> error;
    private Map<String, Object> metadata;

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    public Map<String, String> getError() {
        return error;
    }

    public void setError(Map<String, String> error) {
        this.error = error;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }
}

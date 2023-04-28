package com.example.globallogicchallenge.utils;

import org.json.JSONArray;
import org.json.JSONObject;

public class ApiException extends Exception {
    private static final long serialVersionUID = 1L;
    private final String code;
    private final String description;
    private final Integer statusCode;
    private final Long timestamp;

    public ApiException(String code, String description, Integer statusCode, Long timestamp) {
        this.code = code;
        this.description = description;
        this.statusCode = statusCode;
        this.timestamp = timestamp;
    }

    public String getCode() {
        return this.code;
    }

    public String getDescription() {
        return this.description;
    }

    public Integer getStatusCode() {
        return this.statusCode;
    }

    public Long getTimestamp() {
        return this.timestamp;
    }

    public String toJson() {

        JSONObject json = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        JSONObject errorObject = new JSONObject();
        errorObject.put("timestamp", getTimestamp());
        errorObject.put("codigo", getStatusCode());
        errorObject.put("detail", getDescription());
        jsonArray.put(errorObject);
        json.put("error", jsonArray);

        return json.toString();

    }
}

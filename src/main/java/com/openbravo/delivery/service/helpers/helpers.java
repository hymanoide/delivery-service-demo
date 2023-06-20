package com.openbravo.delivery.service.helpers;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;

public class helpers {
    public static <T> JSONObject getJson(String key, T value) throws JSONException {
        // Create JSON object with response
        JSONObject responseJson = new JSONObject();

        // Can only handle LocalDate or String (for exception's error messages)
        if (value instanceof LocalDate dateValue) {
            responseJson.put(key, dateValue.toString());
        } else if (value instanceof String stringValue) {
            responseJson.put(key, stringValue);
        } else {
            throw new IllegalArgumentException("Invalid value type. Expected LocalDate or String.");
        }

        return responseJson;
    }
}

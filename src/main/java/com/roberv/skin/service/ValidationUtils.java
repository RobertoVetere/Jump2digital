package com.roberv.skin.service;

public class ValidationUtils {
    public static boolean isValidNumericId(String id) {
        return id.matches("\\d+");
    }
}

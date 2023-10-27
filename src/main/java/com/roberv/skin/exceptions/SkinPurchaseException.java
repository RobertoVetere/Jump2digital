package com.roberv.skin.exceptions;

public class SkinPurchaseException extends RuntimeException {

    public SkinPurchaseException(String message) {
        super(message);
    }

    public SkinPurchaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
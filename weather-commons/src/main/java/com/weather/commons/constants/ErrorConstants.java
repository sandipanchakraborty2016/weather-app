package com.weather.commons.constants;

import lombok.Getter;

import java.util.UUID;

@Getter
public enum ErrorConstants {
    BUSINESS_EXCEPTION( "BUSINESS_EXCEPTION", "BUSINESS_EXCEPTION" ),
    SYSTEM_EXCEPTION( "SYSTEM_EXCEPTION",  "BUSINESS_EXCEPTION" ),
    GENERIC_ERROR( "GENERIC_ERROR",  "GENERIC_ERROR" ),
    DATA_NOT_FOUND_EXCEPTION("DATA_NOT_FOUND_EXCEPTION","DATA_NOT_FOUND_EXCEPTION");

    private final String errorName;
    private final java.util.UUID errorCode;
    private final String errorMessage;

    ErrorConstants(String errorName, String errorMessage ) {
        this.errorName = errorName;
        this.errorCode = UUID.randomUUID();
        this.errorMessage = errorMessage;
    }

}

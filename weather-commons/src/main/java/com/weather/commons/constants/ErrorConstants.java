package com.weather.commons.constants;

import lombok.Getter;

import java.util.ResourceBundle;

@Getter
public enum ErrorConstants {
    BUSINESS_EXCEPTION( "BUSINESS_EXCEPTION", "1001", "BUSINESS_EXCEPTION" );

    private String errorName;
    private String errorCode;
    private String errorMessage;

    ErrorConstants(String errorName, String errorCode, String errorMessage ) {
        this.errorName = errorName;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public void setErrorCode( String errorCode ) {
        this.errorCode = errorCode;
    }

    public void setErrorMessage( String errorMessage ) {
        this.errorMessage = errorMessage;
    }

    public void setErrorName( String errorName ) {
        this.errorName = errorName;
    }


    public static String getExceptionMessage( ErrorConstants errorCode ) {
        ResourceBundle bundle = ResourceBundle.getBundle( "com.weather" );
        return bundle.getString( errorCode.getErrorName() );
    }
}

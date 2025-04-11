package com.tuanha.order.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;


public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    UNAUTHENTICATED(1006, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1007, "You do not have permission", HttpStatus.FORBIDDEN),
    ORDER_INFO_NOT_FOUND(1008, "Order info not found", HttpStatus.NOT_FOUND),
    ORDER_NOT_FOUND(1009, "Order not found", HttpStatus.NOT_FOUND),
    PURCHASE_ERROR(1010, "An error occurred while processing the products purchase", HttpStatus.BAD_REQUEST),
    PROFILE_NOT_FOUND(1011, "Profile not found", HttpStatus.BAD_REQUEST),
    INVALID_STATUS_ORDER(1012, "Invalid status order", HttpStatus.BAD_REQUEST),
    ;
    private final int code;
    private final String message;
    private final HttpStatusCode httpStatusCode;

    ErrorCode(int code, String message, HttpStatusCode httpStatusCode) {
        this.code = code;
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatusCode getHttpStatusCode() {
        return httpStatusCode;
    }
}

package com.tuanha.product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION (9999, "Uncategorized exception.", HttpStatus.INTERNAL_SERVER_ERROR),
    UNAUTHENTICATED(1006, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1007, "You do not have permission", HttpStatus.FORBIDDEN),
    CATEGORY_NOT_FOUND (1001, "Category not found.", HttpStatus.NOT_FOUND),
    CATEGORY_EXISTS (1002, "Category existed.", HttpStatus.BAD_REQUEST),
    PRODUCT_NOT_FOUND (1003, "Product not found.", HttpStatus.NOT_FOUND),
    PRODUCT_EXISTS (1004, "Product existed.", HttpStatus.BAD_REQUEST),
    PRODUCT_NAME_NOT_BLANK (1005, "Product name can not be blank.", HttpStatus.BAD_REQUEST),
    PRODUCT_DESCRIPTION_NOT_BLANK (1008, "Product description can not be blank.", HttpStatus.BAD_REQUEST),
    PRODUCT_IMAGE_NOT_BLANK (1009, "Product image can not be blank.", HttpStatus.BAD_REQUEST),
    PRICE_NOT_BLANK (1010, "Price can not be blank.", HttpStatus.BAD_REQUEST),
    STOCK_NOT_BLANK (1011, "Stock can not be blank.", HttpStatus.BAD_REQUEST),
    CATEGORY_ID_NOT_BLANK (1012, "Category can not be blank.", HttpStatus.BAD_REQUEST),
    PRICE_POSITIVE (1013, "Price must be greater than 0.", HttpStatus.BAD_REQUEST),
    STOCK_POSITIVE (1014, "Stock must be greater than 0.", HttpStatus.BAD_REQUEST),
    INVALID_KEY(1015, "Invalid key.", HttpStatus.BAD_REQUEST),
    ;

    private int code;
    private String message;
    private HttpStatusCode httpStatusCode;

    ErrorCode(int code, String message, HttpStatusCode httpStatusCode) {
        this.code = code;
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatusCode getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(HttpStatusCode httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }
}

package com.tuanha.sale.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized exception.", HttpStatus.INTERNAL_SERVER_ERROR),
    UNAUTHENTICATED(1006, "Unauthenticated.", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1007, "You do not have permission", HttpStatus.FORBIDDEN),
    PRODUCT_NOT_FOUND(1008, "Product not found", HttpStatus.NOT_FOUND),
    PRODUCT_VARIANT_NOT_FOUND(1009, "Product variant not found", HttpStatus.NOT_FOUND),
    PROFILE_NOT_FOUND(1010, "Profile not found", HttpStatus.NOT_FOUND),
    SUPPLIER_NOT_FOUND(1011, "Supplier not found", HttpStatus.NOT_FOUND),
    VOUCHER_NOT_FOUND(1012, "Voucher not found", HttpStatus.NOT_FOUND),
    VOUCHER_ALREADY_CLAIMED(1013, "Voucher has already been claimed by this user", HttpStatus.BAD_REQUEST),
    VOUCHER_CLAIM_LIMIT_REACHED(1014, "User has reached the maximum number of claims for this voucher", HttpStatus.BAD_REQUEST),
    VOUCHER_OUT_OF_STOCK(1015, "Voucher is out of stock", HttpStatus.BAD_REQUEST),
    FLASH_SALE_NOT_FOUND(1016, "Flash sale not found", HttpStatus.NOT_FOUND),
    FLASH_SALE_ITEM_NOT_FOUND(1017, "Flash sale item not found", HttpStatus.NOT_FOUND),
    FLASH_SALE_NOT_ACTIVE(1018, "Flash sale is not active", HttpStatus.BAD_REQUEST),
    FLASH_SALE_ITEM_NOT_ENOUGH(1018, "Not enough stock available", HttpStatus.BAD_REQUEST),
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

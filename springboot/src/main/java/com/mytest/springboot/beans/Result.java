package com.mytest.springboot.beans;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by ziheng on 2019-08-11.
 */
@Getter
@Setter
public class Result<D> {

    private boolean success;
    private String code;
    private String message;
    private D data;

    // -------------------------- STATIC METHODS --------------------------
    public static <D> Result<D> wrapSuccessfulResult() {
        Result<D> result = new Result<D>();
        result.code = "200";
        result.success = true;
        return result;
    }

    public static <D> Result<D> wrapSuccessfulResult(D data) {
        Result<D> result = new Result<D>();
        result.code = "200";
        result.data = data;
        result.success = true;
        return result;
    }


    public static <D> Result<D> wrapErrorResult(String errorCode,String message) {
        Result<D> result = new Result<D>();
        result.success = false;
        result.code =errorCode;
        result.message = message;
        return result;
    }

//    public static <D> Result<D> wrapErrorResult(ErrorCode errorCode) {
//        Result<D> result = new Result<D>();
//        result.success = false;
//        result.code =errorCode.getCode();
//        result.message = errorCode.getDescription();
//        return result;
//    }


//    public static <D> Result<D> wrapErrorResult(CustomErrorCode errorCode) {
//      return  wrapErrorResult(errorCode.getCode(),errorCode.getMessage());
//    }

}

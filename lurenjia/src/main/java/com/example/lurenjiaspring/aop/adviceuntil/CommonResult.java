package com.example.lurenjiaspring.aop.mvc;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class CommonResult<T> {
    private String code;
    private String message;
    private T Data;

    public CommonResult() {
    }

    public CommonResult(T data) {
        Data = data;
    }

    /**
     * 若只传入code码 默认传入的数据为null
     * @param rc
     */
    public CommonResult(ResultCode rc) {
        this(rc, null);
    }
    public CommonResult(ResultCode rc, T data) {
        this.code  = rc.getCode();
        this.message = rc.getMsg();
        this.Data = data;
    }


    public static<T> CommonResult<T> errorResult(ResultCode rc,T data){
        CommonResult<T> commonResult = new CommonResult<>();
        commonResult.code = rc.getCode();
        commonResult.message = rc.getMsg();
        commonResult.Data = data == null?(T) "" :data;
        log.error("{}",commonResult);
        return commonResult;
    }
}

package com.melot.talkee.wordpress.enums;

public enum  WordpressTagCodeEnum {

    SUCCESS("00000000","请求处理成功"),
    PARAMETER_MISSING("04080001","参数为空"),
    DATA_ALREDY_EXIST("04080002","数据已存在"),
    DATA_INSERT_FAILED("04080003","数据插入失败"),
    DATA_DEAL_FAILED("04080004","数据处理异常"),
    PARAMETER_ERROR("04080005","参数错误");



    /** 错误码  */
    private String code;
    /** 错误提示  */
    private String codeMessage;

    WordpressTagCodeEnum(String code, String codeMessage) {
        this.code = code;
        this.codeMessage = codeMessage;
    }

    public String getCode() {
        return code;
    }

    public String getCodeMessage() {
        return codeMessage;
    }
}
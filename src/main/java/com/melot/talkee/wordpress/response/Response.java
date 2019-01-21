package com.melot.talkee.wordpress.response;


import com.melot.talkee.wordpress.enums.WordpressTagCodeEnum;

public class Response {


    /**
     * 错误信息
     */
    private String errorMsg;

    /**
     * 返回字段
     */
    private Object data;

    /**
     * 错误码，默认00000000为成功
     */
    private String code = "00000000";

    /**
     * 是否成功
     */
    private boolean success = true;

    /**
     * 分页总数据个数
     */
    private Integer total;

    /**
     * @param wordpressTagCodeEnum 返回码枚举
     * @return Response
     */
    public Response setWordpressTagCodeEnum(WordpressTagCodeEnum wordpressTagCodeEnum) {
        this.code = wordpressTagCodeEnum.getCode();
        this.errorMsg = wordpressTagCodeEnum.getCodeMessage();
        if (wordpressTagCodeEnum.equals(WordpressTagCodeEnum.DATA_DEAL_FAILED)) {
            this.success = false;
        }
        return this;
    }

    public static Response getInstance() {
        return new Response();
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public Response setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
        return this;
    }

    public Object getData() {
        return data;
    }

    public Response setData(Object data) {
        this.data = data;
        return this;
    }

    public String getCode() {
        return code;
    }

    public Response setCode(String code) {
        this.code = code;
        return this;
    }

    public Integer getTotal() {
        return total;
    }

    public Response setTotal(Integer total) {
        this.total = total;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public Response setSuccess(boolean success) {
        this.success = success;
        return this;
    }
}

package com.centrin.ciyun.common.constant;

import java.util.LinkedHashMap;
import java.util.Map;

public final class ReturnCode {
	
	public static Map<Integer, String> ENUMMAP = new LinkedHashMap<Integer, String>();
    public enum EReturnCode{
        OK(10000, "成功"),
        PHONE_IS_WRONG(20001, "手机号不对"),
        NOTE_IS_WRONG(20002, "短信验证码不对"),
        NOTE_UPPER_LIMIT(20003,"获取短信验证码超过上限"),
        PARAM_IS_NULL(20004,"输入参数为空"),
        THIRD_SESSION_KEY(30001,"第三方服务器会话key失效或不对 "),
        DATA_VALIDATE_FAIL(30002,"数据校验失败"),
        DB_EXCEPTION(30003,"数据库异常"),
        SYSTEM_BUSY(30004,"系统异常"),
        DATA_SAVE_FAILED(30005,"数据入库失败"),
        SIGNATURE_IS_ERROR(30006,"签名错误"),
        DATA_NOT_EXISTS(30007, "查询数据不存在");
    	
        EReturnCode(Integer key, String value){
            this.key = key;
            this.value = value;
        }
        public Integer key;
        public String value;
    }
    static {
        for (EReturnCode erc : EReturnCode.values()) {
            ENUMMAP.put(erc.key, erc.value);
        }
    }
	
}

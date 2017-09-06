package com.centrin.ciyun.common.constant;

import java.util.LinkedHashMap;
import java.util.Map;

public final class ReturnCode {
	
	public static Map<Integer, String> ENUMMAP = new LinkedHashMap<Integer, String>();
    public enum EReturnCode{
        OK(0, "成功"),
        PHONE_IS_WRONG(20001, "手机号不对"),
        NOTE_IS_WRONG(20002, "短信验证码不对"),
        NOTE_UPPER_LIMIT(20003,"获取短信验证码超过上限"),
        PARAM_IS_NULL(20004,"请求参数为空"),
        NOTE_IS_INVALID(20005, "短信验证码过期"),
        NOTE_SEND_FAIL(20005, "短信验证码发送失败"),
        
        THIRD_SESSION_KEY(30001,"第三方服务器会话key失效或不对 "),
        DATA_VALIDATE_FAIL(30002,"数据校验失败"),
        DB_EXCEPTION(30003,"数据库异常"),
        SYSTEM_BUSY(30004,"系统异常"),
        DATA_SAVE_FAILED(30005,"数据入库失败"),
        SIGNATURE_IS_ERROR(30006,"签名错误"),
        DATA_NOT_EXISTS(30007, "查询数据不存在"),
        SESSION_INFO_NOT_EXISTS(30008, "会话用户信息不存在"),
        CODE_IS_WRONG(40029,"登录凭证code无效"),
    	CODE_IS_USED(40163,"登录凭证code已被使用");
    	
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

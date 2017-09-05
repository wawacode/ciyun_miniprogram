package com.centrin.ciyun.medrpt.domain.resp;

import com.centrin.ciyun.common.constant.ReturnCode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HttpResponse<T> {
	private  int result = ReturnCode.EReturnCode.OK.key.intValue();
    private String message;
    private T datas;
}

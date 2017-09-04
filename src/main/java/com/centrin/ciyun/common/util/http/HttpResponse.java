package com.centrin.ciyun.common.util.http;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HttpResponse<T,L> {
	private  int result = 0;
    private String message;
    private T datas;
}

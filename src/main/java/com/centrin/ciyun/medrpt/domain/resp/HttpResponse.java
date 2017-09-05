package com.centrin.ciyun.medrpt.domain.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HttpResponse<T> {
	private  int result = 0;
    private String message;
    private T datas;
}

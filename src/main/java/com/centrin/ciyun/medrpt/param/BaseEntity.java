package com.centrin.ciyun.medrpt.param;

import javax.servlet.http.HttpServletRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String thirdSession;//第三方服务器会话key
	
	private HttpServletRequest request;
}

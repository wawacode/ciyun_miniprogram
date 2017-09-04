package com.centrin.ciyun.medrpt.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonBaseInfoParam extends BaseEntity{
	private String nickname;//用户昵称
	private int sex;//用户性别 1：男 2：女 3：未知
	private int age;//用户年龄
	private int hight;//用户身高
}

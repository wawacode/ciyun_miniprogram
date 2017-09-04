package com.centrin.ciyun.service.interfaces.hmo;

import java.util.List;
import com.centrin.ciyun.entity.hmo.HmoCorporation;

public interface HmoCorporationService {

	public String getName(String hmoId);
	
	public HmoCorporation view(String hmoId);
	
	public List<HmoCorporation> list();
}

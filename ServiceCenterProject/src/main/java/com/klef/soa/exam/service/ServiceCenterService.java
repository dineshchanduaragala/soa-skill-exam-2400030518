package com.klef.soa.exam.service;

import java.util.List;

import com.klef.soa.exam.entity.*;

public interface ServiceCenterService 
{
	ServiceCenter addServiceCenter(ServiceCenter sc);
	ServiceCenter updateServiceCenter(ServiceCenter sc);
	String deleteServiceCenter(Long id);
	List<ServiceCenter> displayAllServiceCenters();
	ServiceCenter displayServiceCenterById(Long id);
	List<ServiceCenter> displayByName(String name);
	List<ServiceCenter> displayByType(String type);
}

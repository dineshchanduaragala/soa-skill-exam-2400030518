package com.klef.soa.exam.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.soa.exam.entity.*;
import com.klef.soa.exam.repository.*;

@Service
public class ServiceCenterServiceImpl implements ServiceCenterService
{
	@Autowired
	private ServiceCenterRepository repo;

	@Override
	public ServiceCenter addServiceCenter(ServiceCenter sc) 
	{
		return repo.save(sc);
	}

	@Override
	public List<ServiceCenter> displayAllServiceCenters() 
	{
		return repo.findAll();
	}

	@Override
	public ServiceCenter updateServiceCenter(ServiceCenter sc) 
	{
		Optional<ServiceCenter> optional = repo.findById(sc.getId());
		if (optional.isPresent()) 
		{
			ServiceCenter center = optional.get();
			center.setName(sc.getName());
			center.setType(sc.getType());
			center.setStatus(sc.getStatus());
			center.setLocation(sc.getLocation());
			return repo.save(center);
		} 
		else 
		{
			return null;
		}
	}

	@Override
	public ServiceCenter displayServiceCenterById(Long id) 
	{
		return repo.findById(id).orElse(null);
	}

	@Override
	public String deleteServiceCenter(Long id) 
	{
		boolean status = repo.existsById(id);

		if (status) 
		{
			repo.deleteById(id);
			return "Service Center Deleted Successfully";
		} 
		else 
		{
			return "Service Center ID Not Found";
		}
	}

	@Override
	public List<ServiceCenter> displayByName(String name) 
	{
		return repo.findByName(name);
	}

	@Override
	public List<ServiceCenter> displayByType(String type) 
	{
		return repo.findByType(type);
	}
}
